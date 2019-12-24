/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.androidman.internal

import android.content.res.ColorStateList
import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Build
import kotlin.math.ceil
import kotlin.math.cos

/**
 * A rounded rectangle drawable which also includes a shadow around.
 * @param backgroundColor 背景颜色
 * @param radius          圆角半径
 * @param shadowSize      阴影尺寸
 * @param maxShadowSize   最大阴影尺寸
 */
class RoundRectDrawableWithShadow(backgroundColor: ColorStateList, radius: Float,
                                  private val mShadowStartColor: Int, private val mShadowEndColor: Int,
                                  shadowSize: Float) : Drawable() {
    /**
     * extra shadow to avoid gaps between card and shadow
     */
    private val mInsetShadow = 0
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
    private val mCornerShadowPaint: Paint
    private val mEdgeShadowPaint: Paint
    private val mCardBounds: RectF
    private var mCornerRadius: Float = 0f
    private var mCornerShadowPath = Path()
    /**
     * actual value set by developer
     */
    private var mRawMaxShadowSize = 0f
    /**
     * multiplied value to account for shadow offset
     */
    private var mShadowSize = 0f
    /**
     * actual value set by developer
     */
    private var mRawShadowSize = 0f
    private var mBackground: ColorStateList? = null
    private var mDirty = true
    private var mAddPaddingForCorners = true
    /**
     * If shadow size is set to a value above max shadow, we print a warning
     */
    private var mPrintedShadowClipWarning = true

    init {
        setBackground(backgroundColor)
        mCornerShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        mCornerShadowPaint.style = Paint.Style.FILL
        mCornerRadius = radius + .5f
        mCardBounds = RectF()
        mEdgeShadowPaint = Paint(mCornerShadowPaint)
        mEdgeShadowPaint.isAntiAlias = false
        setShadowSize(shadowSize, shadowSize)
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mDirty = true
    }

    private fun setShadowSize(shadowSize: Float, maxShadowSize: Float) {
        var shadowSizeValue = shadowSize
        var maxShadowSizeValue = maxShadowSize
        require(shadowSizeValue >= 0f) { "Invalid shadow size $shadowSizeValue. Must be >= 0" }
        require(maxShadowSizeValue >= 0f) { "Invalid max shadow size $maxShadowSizeValue. Must be >= 0" }
        shadowSizeValue = toEven(shadowSizeValue).toFloat()
        maxShadowSizeValue = toEven(maxShadowSizeValue).toFloat()
        if (shadowSizeValue > maxShadowSizeValue) {
            shadowSizeValue = maxShadowSizeValue
            if (!mPrintedShadowClipWarning) {
                mPrintedShadowClipWarning = true
            }
        }
        if (mRawShadowSize - shadowSizeValue == 0f && mRawMaxShadowSize - maxShadowSizeValue == 0f) {
            return
        }
        mRawShadowSize = shadowSizeValue
        mRawMaxShadowSize = maxShadowSizeValue
        mShadowSize = shadowSizeValue * SHADOW_MULTIPLIER + mInsetShadow + .5f
        mDirty = true
        invalidateSelf()
    }

    override fun getPadding(padding: Rect): Boolean {
        val vOffset = ceil(calculateVerticalPadding(mRawMaxShadowSize, mCornerRadius,
                mAddPaddingForCorners).toDouble()).toInt()
        val hOffset = ceil(calculateHorizontalPadding(mRawMaxShadowSize, mCornerRadius,
                mAddPaddingForCorners).toDouble()).toInt()
        padding.set(hOffset, vOffset, hOffset, vOffset)
        return true
    }

    override fun onStateChange(stateSet: IntArray): Boolean {
        val newColor = mBackground!!.getColorForState(stateSet, mBackground!!.defaultColor)
        if (mPaint.color == newColor) {
            return false
        }
        mPaint.color = newColor
        mDirty = true
        invalidateSelf()
        return true
    }

    override fun isStateful(): Boolean {
        return mBackground != null && mBackground!!.isStateful || super.isStateful()
    }

    override fun setColorFilter(cf: ColorFilter?) {
        mPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun draw(canvas: Canvas) {
        if (mDirty) {
            buildComponents(bounds)
            mDirty = false
        }
        canvas.translate(0f, mRawShadowSize / 2)
        drawShadow(canvas)
        canvas.translate(0f, -mRawShadowSize / 2)
        sRoundRectHelper.drawRoundRect(canvas, mCardBounds, mCornerRadius, mPaint)
    }

    private fun drawShadow(canvas: Canvas) {
        val edgeShadowTop = -mCornerRadius - mShadowSize
        val inset = toEven(mCornerRadius + mInsetShadow + mRawShadowSize / 2)

        val drawHorizontalEdges = mCardBounds.width() - 2 * inset > 0
        val drawVerticalEdges = mCardBounds.height() - 2 * inset > 0
        // LT
        var saved = canvas.save()
        canvas.translate(mCardBounds.left + inset, mCardBounds.top + inset)
        canvas.drawPath(mCornerShadowPath, mCornerShadowPaint)
        if (drawHorizontalEdges) {
            canvas.drawRect(0f, edgeShadowTop,
                    mCardBounds.width() - 2 * inset, -mCornerRadius,
                    mEdgeShadowPaint)
        }
        canvas.restoreToCount(saved)
        // RB
        saved = canvas.save()
        canvas.translate(mCardBounds.right - inset, mCardBounds.bottom - inset)
        canvas.rotate(180f)
        canvas.drawPath(mCornerShadowPath, mCornerShadowPaint)
        if (drawHorizontalEdges) {
            canvas.drawRect(0f, edgeShadowTop,
                    mCardBounds.width() - 2 * inset, -mCornerRadius + mShadowSize,
                    mEdgeShadowPaint)
        }
        canvas.restoreToCount(saved)
        // LB
        saved = canvas.save()
        canvas.translate(mCardBounds.left + inset, mCardBounds.bottom - inset)
        canvas.rotate(270f)
        canvas.drawPath(mCornerShadowPath, mCornerShadowPaint)
        if (drawVerticalEdges) {
            canvas.drawRect(0f, edgeShadowTop,
                    mCardBounds.height() - 2 * inset, -mCornerRadius, mEdgeShadowPaint)
        }
        canvas.restoreToCount(saved)
        // RT
        saved = canvas.save()
        canvas.translate(mCardBounds.right - inset, mCardBounds.top + inset)
        canvas.rotate(90f)
        canvas.drawPath(mCornerShadowPath, mCornerShadowPaint)
        if (drawVerticalEdges) {
            canvas.drawRect(0f, edgeShadowTop,
                    mCardBounds.height() - 2 * inset, -mCornerRadius, mEdgeShadowPaint)
        }
        canvas.restoreToCount(saved)
    }

    private fun buildShadowCorners() {
        val innerBounds = RectF(-mCornerRadius, -mCornerRadius, mCornerRadius, mCornerRadius)
        val outerBounds = RectF(innerBounds)
        outerBounds.inset(-mShadowSize, -mShadowSize)
        mCornerShadowPath.reset()
        mCornerShadowPath.fillType = Path.FillType.EVEN_ODD
        mCornerShadowPath.moveTo(-mCornerRadius, 0f)
        mCornerShadowPath.rLineTo(-mShadowSize, 0f)
        // outer arc
        mCornerShadowPath.arcTo(outerBounds, 180f, 90f, false)
        // inner arc
        mCornerShadowPath.arcTo(innerBounds, 270f, -90f, false)
        mCornerShadowPath.close()
        val startRatio = mCornerRadius / (mCornerRadius + mShadowSize)
        mCornerShadowPaint.shader = RadialGradient(0f, 0f, mCornerRadius + mShadowSize,
                intArrayOf(mShadowStartColor, mShadowStartColor, mShadowEndColor),
                floatArrayOf(0f, startRatio, 1f),
                Shader.TileMode.CLAMP)
        // we offset the content shadowSize/2 pixels up to make it more realistic.
        // this is why edge shadow shader has some extra space
        // When drawing bottom edge shadow, we use that extra space.
        mEdgeShadowPaint.shader = LinearGradient(0f, -mCornerRadius + mShadowSize, 0f, -mCornerRadius - mShadowSize,
                intArrayOf(mShadowStartColor, mShadowStartColor, mShadowEndColor), floatArrayOf(0f, .5f, 1f),
                Shader.TileMode.CLAMP)
        mEdgeShadowPaint.isAntiAlias = false
    }

    // Card is offset SHADOW_MULTIPLIER * maxShadowSize to account for the shadow shift.
    // We could have different top-bottom offsets to avoid extra gap above but in that case
    // center aligning Views inside the CardView would be problematic.
    private fun buildComponents(bounds: Rect) {
        val verticalOffset = mRawMaxShadowSize * SHADOW_MULTIPLIER
        mCardBounds.set(bounds.left + mRawMaxShadowSize,
                bounds.top + verticalOffset,
                bounds.right - mRawMaxShadowSize,
                bounds.bottom - verticalOffset)
        buildShadowCorners()
    }

    var cornerRadius: Float = 0.0f
        get() = mCornerRadius
        set(radius) {
            field = radius
            require(field >= 0f) { "Invalid radius $field. Must be >= 0" }
            field += .5f
            if (mCornerRadius - field == 0f) {
                return
            }
            mCornerRadius = field
            mDirty = true
            invalidateSelf()
        }

    fun getMaxShadowAndCornerPadding(into: Rect) {
        getPadding(into)
    }

    var shadowSize: Float
        get() = mRawShadowSize
        set(size) {
            setShadowSize(size, mRawMaxShadowSize)
        }

    var maxShadowSize: Float
        get() = mRawMaxShadowSize
        set(size) {
            setShadowSize(mRawShadowSize, size)
        }

    val minWidth: Float
        get() {
            val content = (2
                    * Math.max(mRawMaxShadowSize, mCornerRadius + mInsetShadow + mRawMaxShadowSize / 2))
            return content + (mRawMaxShadowSize + mInsetShadow) * 2
        }

    val minHeight: Float
        get() {
            val content = 2 * Math.max(mRawMaxShadowSize, mCornerRadius + mInsetShadow
                    + mRawMaxShadowSize * SHADOW_MULTIPLIER / 2)
            return content + (mRawMaxShadowSize * SHADOW_MULTIPLIER + mInsetShadow) * 2
        }

    var color: ColorStateList?
        get() = mBackground
        set(color) {
            setBackground(color)
            invalidateSelf()
        }

    interface RoundRectHelper {
        fun drawRoundRect(canvas: Canvas?, bounds: RectF?, cornerRadius: Float, paint: Paint)
    }

    companion object {
        /**
         * used to calculate content padding
         */
        private val COS_45 = cos(Math.toRadians(45.0))
        private const val SHADOW_MULTIPLIER = 1.5f
        /*
     * This helper is set by CardView implementations.
     * <p>
     * Prior to API 17, canvas.drawRoundRect is expensive; which is why we need this interface
     * to draw efficient rounded rectangles before 17.
     * */
        val sRoundRectHelper: RoundRectHelper by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return@lazy object : RoundRectHelper {
                    override fun drawRoundRect(canvas: Canvas?, bounds: RectF?, cornerRadius: Float, paint: Paint) {
                        canvas!!.drawRoundRect(bounds!!, cornerRadius, cornerRadius, paint)
                    }
                }
            } else {
                return@lazy object : RoundRectHelper {
                    override fun drawRoundRect(canvas: Canvas?, bounds: RectF?, cornerRadius: Float, paint: Paint) {
                        val mCornerRect = RectF()
                        val twoRadius = cornerRadius * 2
                        val innerWidth = bounds!!.width() - twoRadius - 1
                        val innerHeight = bounds.height() - twoRadius - 1
                        if (cornerRadius >= 1f) { // increment corner radius to account for half pixels.
                            val roundedCornerRadius = cornerRadius + .5f
                            mCornerRect[-roundedCornerRadius, -roundedCornerRadius, roundedCornerRadius] = roundedCornerRadius
                            val saved = canvas!!.save()
                            canvas.translate(bounds.left + roundedCornerRadius, bounds.top + roundedCornerRadius)
                            canvas.drawArc(mCornerRect, 180f, 90f, true, paint)
                            canvas.translate(innerWidth, 0f)
                            canvas.rotate(90f)
                            canvas.drawArc(mCornerRect, 180f, 90f, true, paint)
                            canvas.translate(innerHeight, 0f)
                            canvas.rotate(90f)
                            canvas.drawArc(mCornerRect, 180f, 90f, true, paint)
                            canvas.translate(innerWidth, 0f)
                            canvas.rotate(90f)
                            canvas.drawArc(mCornerRect, 180f, 90f, true, paint)
                            canvas.restoreToCount(saved)
                            //draw top and bottom pieces
                            canvas.drawRect(bounds.left + roundedCornerRadius - 1f, bounds.top,
                                    bounds.right - roundedCornerRadius + 1f,
                                    bounds.top + roundedCornerRadius, paint)
                            canvas.drawRect(bounds.left + roundedCornerRadius - 1f,
                                    bounds.bottom - roundedCornerRadius,
                                    bounds.right - roundedCornerRadius + 1f, bounds.bottom, paint)
                        }
                        // center
                        canvas!!.drawRect(bounds.left, bounds.top + cornerRadius,
                                bounds.right, bounds.bottom - cornerRadius, paint)
                    }
                }
            }
        }

        private fun calculateVerticalPadding(maxShadowSize: Float, cornerRadius: Float, addPaddingForCorners: Boolean): Float {
            return if (addPaddingForCorners) {
                (maxShadowSize * SHADOW_MULTIPLIER + (1 - COS_45) * cornerRadius).toFloat()
            } else {
                maxShadowSize * SHADOW_MULTIPLIER
            }
        }

        private fun calculateHorizontalPadding(maxShadowSize: Float, cornerRadius: Float, addPaddingForCorners: Boolean): Float {
            return if (addPaddingForCorners) {
                (maxShadowSize + (1 - COS_45) * cornerRadius).toFloat()
            } else {
                maxShadowSize
            }
        }
    }

    private fun setBackground(color: ColorStateList?) {
        mBackground = color ?: ColorStateList.valueOf(Color.TRANSPARENT)
        mPaint.color = mBackground!!.getColorForState(state, mBackground!!.defaultColor)
    }

    /**
     * Casts the value to an even integer.
     */
    private fun toEven(value: Float): Int {
        val i = (value + .5f).toInt()
        return if (i % 2 == 1) {
            i - 1
        } else i
    }

    fun setAddPaddingForCorners(addPaddingForCorners: Boolean) {
        mAddPaddingForCorners = addPaddingForCorners
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
        mCornerShadowPaint.alpha = alpha
        mEdgeShadowPaint.alpha = alpha
    }


}