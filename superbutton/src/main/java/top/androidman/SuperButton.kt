package top.androidman

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.ColorUtils

/**
 * @author yanjie
 * @version 1.0
 * @date 2019-07-01 20:08
 * @description 超级按钮
 */
class SuperButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    /**
     * 文字内容
     */
    private var text: CharSequence = ""
    /**
     * 文字颜色
     */
    private var mTextColor = Color.GRAY
    /**
     * 文字大小
     */
    private var mTextSize = 0f
    /**
     * 文字是否单行显示，默认单行
     */
    private var mSingleLine = true
    /**
     * 默认背景颜色
     */
    private var mColorNormal = Color.TRANSPARENT
    /**
     * 点击时按钮颜色
     */
    private var mColorPressed = Constant.VALUE_NULL
    /**
     * 图片资源
     */
    private var mDrawableLeft: Drawable? = null
    private var mDrawableRight: Drawable? = null
    private var mDrawableTop: Drawable? = null
    private var mDrawableBottom: Drawable? = null
    private var mDrawableCenter: Drawable? = null
    private var mDrawableAuto = true
    /**
     * 图片中间时的宽高
     */
    private var mDrawableCenterWidth = Constant.VALUE_NULL
    private var mDrawableCenterHeight = Constant.VALUE_NULL
    /**
     * 图片距离文字距离
     */
    private var mDrawablePadding = 0
    /**
     * 形状
     */
    @Shape
    private var mShape = RECT
    /**
     * 当背景是渐进色时，开始颜色
     */
    private var mColorStart = Constant.VALUE_NULL
    /**
     * 当背景是渐进色时，结束颜色
     */
    private var mColorEnd = Constant.VALUE_NULL
    /**
     * 颜色方向
     */
    private var mColorDirection = LEFT_RIGHT
    /**
     * 所有角圆角半径
     */
    private var mCorner = 0f
    /**
     * 四个角角度半径
     */
    private var mCornerLeftTop = Constant.VALUE_NULL
    private var mCornerLeftBottom = Constant.VALUE_NULL
    private var mCornerRightTop = Constant.VALUE_NULL
    private var mCornerRightBottom = Constant.VALUE_NULL
    /**
     * 圆角数组
     * ordered top-left, top-right, bottom-right, bottom-left
     */
    private var mCornerRadii = floatArrayOf(mCorner, mCorner, mCorner, mCorner,
            mCorner, mCorner, mCorner, mCorner)
    /**
     * 边框颜色
     */
    private var mBorderColor = Constant.VALUE_NULL
    /**
     * 边框宽度
     */
    private var mBorderWidth = Constant.VALUE_NULL
    /**
     * 文字和图标容器
     */
    private var mTextIconContainer: AppCompatTextView = AppCompatTextView(context)
    /**
     * 按钮背景
     */
    private var mButtonBackground: GradientDrawable = GradientDrawable()
    /**
     * 按钮是否可以点击
     */
    private var mButtonClickable = true
    /**
     * 阴影开始颜色
     */
    private var mShadowStartColor = Constant.VALUE_NULL
    /**
     * 阴影结束颜色
     */
    private var mShadowEndColor = Constant.VALUE_NULL
    /**
     * 阴影大小
     */
    private var mShadowSize = Constant.VALUE_NULL
    /**
     * 关闭默认点击效果
     */
    private var mCloseDefaultPressed = false
    private var mColorDefaultPressed = Constant.VALUE_NULL
    /**
     * 点击时效果混合前景颜色
     */
    private val mCompositeForegroundColor by lazy {
        if (mColorDefaultPressed != Constant.VALUE_NULL){
            return@lazy mColorDefaultPressed
        }
        return@lazy if (mCloseDefaultPressed) 0x00000000 else 0x26000000
    }

    companion object {
        /**
         * 默认字体大小
         */
        private const val DEFAULT_TEXT_SIZE = 18.0f
    }

    /**
     * 初始化按钮
     */
    init {
        //可以点击
        isClickable = true
        //居中
        gravity = Gravity.CENTER
        //解析属性
        parseAttrs(context, attrs)
        //设置渐变色或正常填充色
        setColors(mColorDirection, mColorStart, mColorEnd)
        //设值按钮形状
        setShape(mShape)
        //设置圆角角度
        mCornerRadii = floatArrayOf(mCorner, mCorner, mCorner, mCorner, mCorner, mCorner, mCorner, mCorner)
        if (mCornerLeftTop != Constant.VALUE_NULL) {
            setLeftTopCorner(mCornerLeftTop.toFloat())
        }
        if (mCornerRightTop != Constant.VALUE_NULL) {
            setRightTopCorner(mCornerRightTop.toFloat())
        }
        if (mCornerRightBottom != Constant.VALUE_NULL) {
            setRightBottomCorner(mCornerRightBottom.toFloat())
        }
        if (mCornerLeftBottom != Constant.VALUE_NULL) {
            setLeftBottomCorner(mCornerLeftBottom.toFloat())
        }
        mButtonBackground.cornerRadii = mCornerRadii
        //设置边框颜色和边框宽度
        mButtonBackground.setStroke(mBorderWidth, mBorderColor)
        //设置背景
        background = mButtonBackground

        //设置文字
        mTextIconContainer.text = text
        //设置文字大小
        mTextIconContainer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize)
        //设置文字颜色
        mTextIconContainer.setTextColor(mTextColor)
        //图片距离文字距离
        mTextIconContainer.compoundDrawablePadding = mDrawablePadding
        //是否单行
        if (mSingleLine) {
            mTextIconContainer.setSingleLine()
        }
        mTextIconContainer.gravity = Gravity.CENTER
        if (mDrawableAuto) {
            //设置图标
            val iconSize = (mTextSize * 1.2f).toInt()
            setBounds(mDrawableLeft, iconSize)
            setBounds(mDrawableTop, iconSize)
            setBounds(mDrawableRight, iconSize)
            setBounds(mDrawableBottom, iconSize)
            //设置文字drawable
            mTextIconContainer.setCompoundDrawables(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom)
        } else {
            mTextIconContainer.setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom)
        }
        val layoutParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        if (mDrawableCenter != null) {
            val imageView = ImageView(context)
            if (mDrawableCenterWidth == Constant.VALUE_NULL || mDrawableCenterHeight == Constant.VALUE_NULL) {
                layoutParams.width = 40
                layoutParams.height = 40
            } else {
                layoutParams.width = mDrawableCenterWidth
                layoutParams.height = mDrawableCenterHeight
            }
            imageView.setImageDrawable(mDrawableCenter)
            addView(imageView, layoutParams)
        } else {
            addView(mTextIconContainer, layoutParams)
        }

        //当设置的有阴影效果时
        if (mShadowSize != Constant.VALUE_NULL && mShadowStartColor != Constant.VALUE_NULL && mShadowEndColor != Constant.VALUE_NULL) {
            setShadow(mShadowStartColor, mShadowEndColor, mShadowSize)
        }
    }

    /**
     * 在自动缩放模式下设置drawable边框
     */
    private fun setBounds(drawable: Drawable?, size: Int) {
        if (drawable == null) {
            return
        }
        drawable.setBounds(0, 0, size, size)
    }

    /**
     * 设置不可点击颜色，此时按钮点击无反应
     */
    @Deprecated("")
    fun setUnableColor(@ColorInt color: Int) {
        mColorNormal = color
        setButtonBackgroundColor(color)
        setButtonClickable(false)
    }

    /**
     * 设置按钮是否可以点击
     */
    fun setButtonClickable(buttonClickable: Boolean) {
        mButtonClickable = buttonClickable
    }

    /**
     * 设置按钮颜色以及是否可以点击
     * 不影响color_pressed的值
     *
     * @param color           设置按钮的color_normal值
     * @param buttonClickable 设置按钮是否可点击
     */
    fun setButtonClickable(@ColorInt color: Int, buttonClickable: Boolean) {
        mColorNormal = color
        setButtonBackgroundColor(color)
        setButtonClickable(buttonClickable)
    }

    /**
     * 修改文字
     */
    fun setText(text: CharSequence?) {
        if (text == null) {
            return
        }
        mTextIconContainer.text = text
    }

    /**
     * 修改文字颜色
     */
    fun setTextColor(@ColorInt textColor: Int) {
        mTextIconContainer.setTextColor(textColor)
    }

    /**
     * 修改文字大小
     * 注意：文字单位是SP或DP，不是PX
     */
    fun setTextSize(textSize: Float) {
        mTextIconContainer.textSize = textSize
    }

    /**
     * 修改按钮默认背景颜色
     */
    fun setColorNormal(@ColorInt colorNormal: Int) {
        mColorNormal = colorNormal
        setButtonBackgroundColor(colorNormal)
    }

    /**
     * 设置点击时的颜色
     */
    fun setColorPressed(@ColorInt colorPressed: Int) {
        mColorPressed = colorPressed
    }

    /**
     * 设置按钮圆角角度,四个角都设置为这个角度，单位为dp
     */
    fun setCorner(corner: Float) {
        val thisCorner = dp2px(corner)
        mButtonBackground.cornerRadii = floatArrayOf(
                thisCorner, thisCorner,
                thisCorner, thisCorner,
                thisCorner, thisCorner,
                thisCorner, thisCorner)
        invalidateBg()
    }

    /**
     * 设置按钮左上角圆角角度，单位为dp
     */
    fun setLeftTopCorner(leftTopCorner: Float) {
        val thisCorner = dp2px(leftTopCorner)
        mCornerRadii[0] = thisCorner
        mCornerRadii[1] = thisCorner
        invalidateBg()
    }

    /**
     * 设置按钮右上角圆角角度，单位为dp
     */
    fun setRightTopCorner(rightTopCorner: Float) {
        val thisCorner = dp2px(rightTopCorner)
        mCornerRadii[2] = thisCorner
        mCornerRadii[3] = thisCorner
        invalidateBg()
    }

    /**
     * 设置按钮右下角圆角角度，单位为dp
     */
    fun setRightBottomCorner(rightBottomCorner: Float) {
        val thisCorner = dp2px(rightBottomCorner)
        mCornerRadii[4] = thisCorner
        mCornerRadii[5] = thisCorner
        invalidateBg()
    }

    /**
     * 设置按钮左下角圆角角度，单位为dp
     */
    fun setLeftBottomCorner(leftBottomCorner: Float) {
        val thisCorner = dp2px(leftBottomCorner)
        mCornerRadii[6] = thisCorner
        mCornerRadii[7] = thisCorner
        invalidateBg()
    }

    /**
     * 设置形状
     */
    fun setShape(@Shape shape: Int) {
        mButtonBackground.shape = if (shape == CIRCLE) GradientDrawable.OVAL else GradientDrawable.RECTANGLE
        invalidateBg()
    }

    /**
     * 设置渐变色
     */
    fun setColors(@ColorOrientation orientation: Int, startColor: Int, endColor: Int) {
        //设置渐变色
        if (startColor != Constant.VALUE_NULL && endColor != Constant.VALUE_NULL) {
            when (orientation) {
                TOP_BOTTOM -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.TOP_BOTTOM
                }
                TR_BL -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.TR_BL
                }
                RIGHT_LEFT -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.RIGHT_LEFT
                }
                BR_TL -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.BR_TL
                }
                BOTTOM_TOP -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.BOTTOM_TOP
                }
                BL_TR -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.BL_TR
                }
                LEFT_RIGHT -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.LEFT_RIGHT
                }
                TL_BR -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.TL_BR
                }
                else -> {
                    mButtonBackground.orientation = GradientDrawable.Orientation.LEFT_RIGHT
                }
            }
            mButtonBackground.colors = intArrayOf(startColor, endColor)
        } else {
            //设置填充颜色
            setButtonBackgroundColor(mColorNormal)
        }
        invalidateBg()
    }

    /**
     * 设置边框
     */
    fun setBorder(borderWidth: Int, @ColorInt borderColor: Int) {
        if (borderWidth <= Constant.VALUE_DEFAULT || borderColor <= Constant.VALUE_DEFAULT) {
            return
        }
        mBorderWidth = borderWidth
        mBorderColor = borderColor
        mButtonBackground.setStroke(mBorderWidth, mBorderColor)
        invalidateBg()
    }

    /**
     * 设置阴影效果
     */
    fun setShadow(@ColorInt startColor: Int, @ColorInt endColor: Int, shadowWidth: Int) {
        mShadowStartColor = startColor
        mShadowEndColor = endColor
        mShadowSize = shadowWidth

        val shadowBackground = RoundRectDrawableWithShadow(
                ColorStateList.valueOf(mColorNormal), mCorner,
                startColor, endColor,
                shadowWidth.toFloat(), shadowWidth.toFloat())
        background = shadowBackground
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!mButtonClickable) {
            return true
        }
        when (event.actionMasked) {
            //按下
            MotionEvent.ACTION_DOWN -> {
                //假如有阴影
                if (mShadowSize != Constant.VALUE_NULL &&
                        mShadowStartColor != Constant.VALUE_NULL &&
                        mShadowEndColor != Constant.VALUE_NULL) {
                    val normalCompositeColor = ColorUtils.compositeColors(mCompositeForegroundColor, mColorNormal)
                    val shadowBackground = RoundRectDrawableWithShadow(
                            ColorStateList.valueOf(normalCompositeColor), mCorner,
                            mShadowStartColor, mShadowEndColor,
                            mShadowSize.toFloat(), mShadowSize.toFloat())
                    background = shadowBackground
                } else {
                    //假如有渐变色效果
                    if (mColorStart != Constant.VALUE_NULL && mColorEnd != Constant.VALUE_NULL) {
                        val startCompositeColor = ColorUtils.compositeColors(mCompositeForegroundColor, mColorStart)
                        val endCompositeColor = ColorUtils.compositeColors(mCompositeForegroundColor, mColorEnd)
                        mButtonBackground.colors = intArrayOf(startCompositeColor, endCompositeColor)
                        background = mButtonBackground
                    } else {
                        val compositeColor = ColorUtils.compositeColors(mCompositeForegroundColor, mColorNormal)
                        setButtonBackgroundColor(if (mColorPressed == Constant.VALUE_NULL) compositeColor else mColorPressed)
                    }
                }
            }
            //抬起
            MotionEvent.ACTION_UP -> {
                //假如有阴影
                if (mShadowSize != Constant.VALUE_NULL &&
                        mShadowStartColor != Constant.VALUE_NULL &&
                        mShadowEndColor != Constant.VALUE_NULL) {
                    val shadowBackground = RoundRectDrawableWithShadow(
                            ColorStateList.valueOf(mColorNormal), mCorner,
                            mShadowStartColor, mShadowEndColor,
                            mShadowSize.toFloat(), mShadowSize.toFloat())
                    background = shadowBackground
                } else {
                    if (mColorStart != Constant.VALUE_NULL && mColorEnd != Constant.VALUE_NULL) {
                        mButtonBackground.colors = intArrayOf(mColorStart, mColorEnd)
                        background = mButtonBackground
                    } else {
                        setButtonBackgroundColor(mColorNormal)
                    }
                }
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 设置正常状态下颜色
     */
    private fun setButtonBackgroundColor(@ColorInt color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mButtonBackground.color = ColorStateList.valueOf(color)
        } else {
            mButtonBackground.setColor(color)
        }
        invalidateBg()
    }

    /**
     * 重绘背景
     */
    private fun invalidateBg() {
        mButtonBackground.cornerRadii = mCornerRadii
        background = mButtonBackground
    }

    /**
     * dp转换px
     */
    private fun dp2px(dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }

    /**
     * 属性解析
     */
    private fun parseAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperButton)
        val length = typedArray.indexCount
        for (i in 0 until length) {
            val attr = typedArray.getIndex(i)
            //是否关闭默认点击效果
            if (attr == R.styleable.SuperButton_close_default_pressed) {
                mCloseDefaultPressed = typedArray.getBoolean(attr, false)
            }
            //设置默认点击效果前景色，即和原颜色混合效果的颜色
            if (attr == R.styleable.SuperButton_color_default_pressed) {
                mColorDefaultPressed = typedArray.getColor(attr, Constant.VALUE_NULL)
            }
            //文字内容
            if (attr == R.styleable.SuperButton_text) {
                text = typedArray.getText(attr)
            }
            //文字颜色
            if (attr == R.styleable.SuperButton_textColor) {
                mTextColor = typedArray.getColor(attr, Color.GRAY)
            }
            //文字大小
            if (attr == R.styleable.SuperButton_textSize) {
                mTextSize = typedArray.getDimension(attr, DEFAULT_TEXT_SIZE)
            }
            //默认背景颜色
            if (attr == R.styleable.SuperButton_color_normal) {
                mColorNormal = typedArray.getColor(attr, Constant.VALUE_NULL)
            }
            //按压状态颜色
            if (attr == R.styleable.SuperButton_color_pressed) {
                mColorPressed = typedArray.getColor(attr, Constant.VALUE_NULL)
            }
            //图片在文字左边
            if (attr == R.styleable.SuperButton_drawable_left) {
                mDrawableLeft = typedArray.getDrawable(attr)
            }
            //图片在文字右边
            if (attr == R.styleable.SuperButton_drawable_right) {
                mDrawableRight = typedArray.getDrawable(attr)
            }
            //图片在文字上边
            if (attr == R.styleable.SuperButton_drawable_top) {
                mDrawableTop = typedArray.getDrawable(attr)
            }
            //图片在文字下边
            if (attr == R.styleable.SuperButton_drawable_bottom) {
                mDrawableBottom = typedArray.getDrawable(attr)
            }
            //图片在中间
            if (attr == R.styleable.SuperButton_drawable_center) {
                mDrawableCenter = typedArray.getDrawable(attr)
            }
            //图片的宽度
            if (attr == R.styleable.SuperButton_drawable_center_width) {
                mDrawableCenterWidth = typedArray.getDimensionPixelSize(attr, Constant.VALUE_DEFAULT)
            }
            //图片的高度
            if (attr == R.styleable.SuperButton_drawable_center_height) {
                mDrawableCenterHeight = typedArray.getDimensionPixelSize(attr, Constant.VALUE_DEFAULT)
            }
            //自动适应文字的大小
            if (attr == R.styleable.SuperButton_drawable_auto) {
                mDrawableAuto = typedArray.getBoolean(attr, true)
            }
            //文字是否单行
            if (attr == R.styleable.SuperButton_singleLine) {
                mSingleLine = typedArray.getBoolean(attr, true)
            }
            //图片距离文字距离
            if (attr == R.styleable.SuperButton_drawable_padding) {
                mDrawablePadding = typedArray.getDimensionPixelSize(attr, Constant.VALUE_DEFAULT)
            }
            //形状
            if (attr == R.styleable.SuperButton_shape) {
                mShape = typedArray.getInt(attr, RECT)
            }
            //开始颜色
            if (attr == R.styleable.SuperButton_color_start) {
                mColorStart = typedArray.getColor(attr, Color.TRANSPARENT)
            }
            //结束颜色
            if (attr == R.styleable.SuperButton_color_end) {
                mColorEnd = typedArray.getColor(attr, Color.TRANSPARENT)
            }
            //颜色方向
            if (attr == R.styleable.SuperButton_color_direction) {
                mColorDirection = typedArray.getInt(attr, LEFT_RIGHT)
            }
            //所有角圆角半径
            if (attr == R.styleable.SuperButton_corner) {
                mCorner = typedArray.getDimensionPixelSize(attr, Constant.VALUE_DEFAULT).toFloat()
            }
            //左上角圆角半径
            if (attr == R.styleable.SuperButton_corner_left_top) {
                mCornerLeftTop = typedArray.getDimensionPixelSize(attr, Constant.VALUE_NULL)
            }
            //右上角圆角半径
            if (attr == R.styleable.SuperButton_corner_right_top) {
                mCornerRightTop = typedArray.getDimensionPixelSize(attr, Constant.VALUE_NULL)
            }
            //左下角圆角半径
            if (attr == R.styleable.SuperButton_corner_left_bottom) {
                mCornerLeftBottom = typedArray.getDimensionPixelSize(attr, Constant.VALUE_NULL)
            }
            //右下角圆角半径
            if (attr == R.styleable.SuperButton_corner_right_bottom) {
                mCornerRightBottom = typedArray.getDimensionPixelSize(attr, Constant.VALUE_NULL)
            }
            //边框宽度
            if (attr == R.styleable.SuperButton_border_width) {
                mBorderWidth = typedArray.getDimensionPixelSize(attr, 0)
            }
            //边框颜色
            if (attr == R.styleable.SuperButton_border_color) {
                mBorderColor = typedArray.getColor(attr, Color.TRANSPARENT)
            }
            //按钮是否可以点击
            if (attr == R.styleable.SuperButton_button_clickable) {
                mButtonClickable = typedArray.getBoolean(attr, true)
            }
            //阴影开始颜色
            if (attr == R.styleable.SuperButton_color_shadow_start) {
                mShadowStartColor = typedArray.getColor(attr, Constant.VALUE_NULL)
            }
            //阴影结束颜色
            if (attr == R.styleable.SuperButton_color_shadow_end) {
                mShadowEndColor = typedArray.getColor(attr, Constant.VALUE_NULL)
            }
            //阴影大小
            if (attr == R.styleable.SuperButton_shadow_size) {
                mShadowSize = typedArray.getDimensionPixelSize(attr, Constant.VALUE_NULL)
            }
        }
        typedArray.recycle()
    }
}