package top.androidman.internal.superview

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils
import top.androidman.internal.*
import top.androidman.internal.Constant.DEFAULT_PRESSED_FOREGROUND_COLOR
import top.androidman.internal.Constant.DEFAULT_UNABLE_FOREGROUND_COLOR
import top.androidman.internal.Constant.VALUE_NULL
import top.androidman.internal.Constant.VALUE_NULL_FLOAT


/**
 * @author         yanjie
 * @date           2019-12-15 15:22
 * @version        1.0
 * 粉刷匠会根据雇主的要求粉刷出美丽的画卷
 * 粉刷匠工作三部曲
 * 1.拿到粉刷匠对象
 * 2.告诉粉刷匠要求
 * 3.让粉刷匠开始工作
 */
open class Plasterer(view: View, valueStore: DefaultStore) {
    /**
     * 粉刷图纸
     */
    private var globalStore: DefaultStore = valueStore
    /**
     * 粉刷对象
     */
    private var paintObject: View = view
    /**
     * 可以点击时混合后的按压时的颜色
     */
    private val compositeNormalBackgroundColorWhenPressed by lazy {
        return@lazy ColorUtils.compositeColors(DEFAULT_PRESSED_FOREGROUND_COLOR, globalStore.backgroundNormalColor)
    }
    /**
     * 可以点击时混合后的按压时的背景开始颜色
     */
    private val compositeBackgroundStartColorWhenPressed by lazy {
        return@lazy ColorUtils.compositeColors(DEFAULT_PRESSED_FOREGROUND_COLOR, globalStore.backgroundStartColor)
    }
    /**
     * 可以点击时混合后的按压时的背景结束颜色
     */
    private val compositeBackgroundEndColorWhenPressed by lazy {
        return@lazy ColorUtils.compositeColors(DEFAULT_PRESSED_FOREGROUND_COLOR, globalStore.backgroundEndColor)
    }
    /**
     * 不可以点击时混合后的按压时的颜色
     */
    private val compositeNormalBackgroundColorWhenUnableClick by lazy {
        return@lazy ColorUtils.compositeColors(DEFAULT_UNABLE_FOREGROUND_COLOR, globalStore.backgroundNormalColor)
    }
    /**
     * 不可以点击时混合后的按压时的背景开始颜色
     */
    private val compositeBackgroundStartColorWhenUnableClick by lazy {
        return@lazy ColorUtils.compositeColors(DEFAULT_UNABLE_FOREGROUND_COLOR, globalStore.backgroundStartColor)
    }
    /**
     * 不可以点击时混合后的按压时的背景结束颜色
     */
    private val compositeBackgroundEndColorWhenUnableClick by lazy {
        return@lazy ColorUtils.compositeColors(DEFAULT_UNABLE_FOREGROUND_COLOR, globalStore.backgroundEndColor)
    }

    init {
        paintObject.setOnTouchListener { _, event ->
            if (globalStore.disableColor != VALUE_NULL || (globalStore.disableColor == VALUE_NULL && !globalStore.clickable)) {
                return@setOnTouchListener true
            }
            when (event.actionMasked) {
                //按下
                MotionEvent.ACTION_DOWN -> {
                    isPressed = true
                    startPaint()
                }
                //抬起或取消
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    isPressed = false
                    startPaint()
                }
            }

            return@setOnTouchListener !view.hasOnClickListeners()
        }
    }

    /**
     * 设置正常状态下颜色
     */
    fun setNormalColor(@ColorInt normalColor: Int): Plasterer {
        globalStore.backgroundNormalColor = normalColor
        return this
    }

    /**
     * 是否开启点击效果,默认关闭
     */
    fun setOpenPressedEffect(open: Boolean): Plasterer {
        globalStore.openPressedEffect = open
        return this
    }

    /**
     * 按压时的背景颜色
     */
    fun setPressedColor(@ColorInt pressedColor: Int): Plasterer {
        globalStore.backgroundPressedColor = pressedColor
        return this
    }

    /**
     * 设置是否可以点击，默认可以
     */
    fun setViewClickable(clickable: Boolean): Plasterer {
        globalStore.clickable = clickable
        return this
    }

    /**
     * 不能点击时的颜色
     */
    fun setDisableColor(@ColorInt disableColor: Int): Plasterer {
        globalStore.disableColor = disableColor
        return this
    }

    /**
     * 设置圆角
     */
    fun setCorners(corner: Float): Plasterer {
        globalStore.corner = dp2px(corner)
        return this
    }

    /**
     * 设置按钮左上角圆角角度，单位为dp
     */
    fun setLeftTopCorner(leftTopCorner: Float): Plasterer {
        globalStore.leftTopCorner = dp2px(leftTopCorner)
        return this
    }

    /**
     * 设置按钮右上角圆角角度，单位为dp
     */
    fun setRightTopCorner(rightTopCorner: Float): Plasterer {
        globalStore.rightTopCorner = dp2px(rightTopCorner)
        return this
    }

    /**
     * 设置按钮右下角圆角角度，单位为dp
     */
    fun setRightBottomCorner(rightBottomCorner: Float): Plasterer {
        globalStore.rightBottomCorner = dp2px(rightBottomCorner)
        return this
    }

    /**
     * 设置按钮左下角圆角角度，单位为dp
     */
    fun setLeftBottomCorner(leftBottomCorner: Float): Plasterer {
        globalStore.leftBottomCorner = dp2px(leftBottomCorner)
        return this
    }

    /**
     * 设置按钮所有圆角角度，单位为dp
     * @param leftTopCorner Float 左上角圆角角度
     * @param rightTopCorner Float 右上角圆角角度
     * @param rightBottomCorner Float 右下角圆角角度
     * @param leftBottomCorner Float 左下角圆角角度
     */
    fun setCorners(leftTopCorner: Float, rightTopCorner: Float, rightBottomCorner: Float, leftBottomCorner: Float): Plasterer {
        globalStore.leftTopCorner = dp2px(leftTopCorner)
        globalStore.leftBottomCorner = dp2px(leftBottomCorner)
        globalStore.rightTopCorner = dp2px(rightTopCorner)
        globalStore.rightBottomCorner = dp2px(rightBottomCorner)
        return this
    }

    /**
     * 设置边框
     * @param borderColor Int 边框颜色
     * @param borderWidth Int 边框宽度
     * @param borderDashWidth Float 边框虚线宽度
     * @param borderDashGap Float 边框虚线间隙宽度
     */
    fun setBorder(@ColorInt borderColor: Int, borderWidth: Int, borderDashWidth: Float, borderDashGap: Float): Plasterer {
        globalStore.borderColor = borderColor
        globalStore.borderWidth = borderWidth
        globalStore.borderDashWidth = borderDashWidth
        globalStore.borderDashGap = borderDashGap
        return this
    }

    /**
     * 设置渐变色
     * @param orientation Int 方向
     * @param startColor Int 开始颜色
     * @param endColor Int 结束颜色
     */
    fun setColors(@ColorOrientation orientation: Int, @ColorInt startColor: Int, @ColorInt endColor: Int): Plasterer {
        globalStore.backgroundColorOrientation = getColorOrientation(orientation)
        globalStore.backgroundStartColor = startColor
        globalStore.backgroundEndColor = endColor
        return this
    }

    /**
     * 设置阴影
     * @param shadowSize Int 阴影宽度
     * @param shadowStartColor Int 阴影开始颜色
     * @param shadowEndColor Int 阴影结束颜色
     */
    fun setShadowColors(shadowSize: Int, @ColorInt shadowStartColor: Int, @ColorInt shadowEndColor: Int): Plasterer {
        globalStore.shadowSize = shadowSize
        globalStore.shadowStartColor = shadowStartColor
        globalStore.shadowEndColor = shadowEndColor
        return this
    }

    /**
     * 设置形状
     * @param shape Int
     */
    fun setShape(@Shape shape: Int): Plasterer {
        globalStore.shape = shape
        return this
    }

    /**
     * 按钮是否按下
     */
    private var isPressed = false

    /**
     * 开始粉刷
     */
    open fun startPaint() {
        //是否有自定义按压效果
        val hasCustomPressedEffect = globalStore.backgroundPressedColor != VALUE_NULL
        //是否有阴影效果
        val hasShadow = if (globalStore.backgroundStartColor != VALUE_NULL && globalStore.backgroundEndColor != VALUE_NULL)
            false
        else {
            globalStore.shadowSize != VALUE_NULL &&
                    globalStore.shadowStartColor != VALUE_NULL &&
                    globalStore.shadowEndColor != VALUE_NULL
        }


        val backGroundDrawable = if (hasShadow) {
            RoundRectDrawableWithShadow(
                    ColorStateList.valueOf(
                            if (isPressed) {
                                if (hasCustomPressedEffect) {
                                    globalStore.backgroundPressedColor
                                } else {
                                    if (globalStore.openPressedEffect) {
                                        compositeNormalBackgroundColorWhenPressed
                                    } else {
                                        globalStore.backgroundNormalColor
                                    }
                                }
                            } else {
                                globalStore.backgroundNormalColor
                            }),
                    globalStore.corner,
                    globalStore.shadowStartColor, globalStore.shadowEndColor,
                    globalStore.shadowSize.toFloat())
        } else {
            generateGradientDrawable(hasCustomPressedEffect)
        }
        //关闭硬件加速
        paintObject.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        paintObject.background = backGroundDrawable

    }

    /**
     * 生成背景
     */
    private fun generateGradientDrawable(hasCustomPressedEffect: Boolean): GradientDrawable {
        val beautifulCanvas = GradientDrawable()
        //设置渐变色，当设置渐变色时需要同时设置起始色和结束色，否则设置不生效
        //渐变色会覆盖掉正常颜色
        if (globalStore.backgroundStartColor != VALUE_NULL && globalStore.backgroundEndColor != VALUE_NULL) {
            val backgroundStartColor =
                    if (isPressed) {
                        if (hasCustomPressedEffect) {
                            globalStore.backgroundPressedColor
                        } else {
                            if (globalStore.openPressedEffect) {
                                compositeBackgroundStartColorWhenPressed
                            } else {
                                globalStore.backgroundStartColor
                            }
                        }
                    } else {
                        if (globalStore.disableColor != VALUE_NULL) {
                            globalStore.disableColor
                        } else {
                            if (globalStore.clickable) {
                                globalStore.backgroundStartColor
                            } else {
                                compositeBackgroundStartColorWhenUnableClick
                            }
                        }
                    }
            val backgroundEndColor =
                    if (isPressed) {
                        if (hasCustomPressedEffect) {
                            globalStore.backgroundPressedColor
                        } else {
                            if (globalStore.openPressedEffect) {
                                compositeBackgroundEndColorWhenPressed
                            } else {
                                globalStore.backgroundEndColor
                            }
                        }
                    } else {
                        if (globalStore.disableColor != VALUE_NULL) {
                            globalStore.disableColor
                        } else {
                            if (globalStore.clickable) {
                                globalStore.backgroundEndColor
                            } else {
                                compositeBackgroundEndColorWhenUnableClick
                            }
                        }
                    }
            beautifulCanvas.orientation = globalStore.backgroundColorOrientation
            beautifulCanvas.colors = intArrayOf(backgroundStartColor, backgroundEndColor)
        } else {
            //设置正常颜色
            val backgroundNormalColor =
                    if (isPressed) {
                        if (hasCustomPressedEffect) {
                            globalStore.backgroundPressedColor
                        } else {
                            if (globalStore.openPressedEffect) {
                                compositeNormalBackgroundColorWhenPressed
                            } else {
                                globalStore.backgroundNormalColor
                            }
                        }
                    } else {
                        if (globalStore.disableColor != VALUE_NULL) {
                            globalStore.disableColor
                        } else {
                            if (globalStore.clickable) {
                                globalStore.backgroundNormalColor
                            } else {
                                compositeNormalBackgroundColorWhenUnableClick
                            }
                        }
                    }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                beautifulCanvas.color = ColorStateList.valueOf(backgroundNormalColor)
            } else {
                beautifulCanvas.setColor(backgroundNormalColor)
            }
        }
        //设置圆角角度 圆角数组 ordered top-left, top-right, bottom-right, bottom-left
        //分别设置每个圆角的角度时会覆盖corner属性，当设置阴影时，此设置不生效
        val cornerRadii = FloatArray(8)
        //top-left
        cornerRadii[0] = if (globalStore.leftTopCorner != VALUE_NULL_FLOAT) globalStore.leftTopCorner else globalStore.corner
        cornerRadii[1] = if (globalStore.leftTopCorner != VALUE_NULL_FLOAT) globalStore.leftTopCorner else globalStore.corner
        //top-right
        cornerRadii[2] = if (globalStore.rightTopCorner != VALUE_NULL_FLOAT) globalStore.rightTopCorner else globalStore.corner
        cornerRadii[3] = if (globalStore.rightTopCorner != VALUE_NULL_FLOAT) globalStore.rightTopCorner else globalStore.corner
        //bottom-right
        cornerRadii[4] = if (globalStore.rightBottomCorner != VALUE_NULL_FLOAT) globalStore.rightBottomCorner else globalStore.corner
        cornerRadii[5] = if (globalStore.rightBottomCorner != VALUE_NULL_FLOAT) globalStore.rightBottomCorner else globalStore.corner
        //bottom-left
        cornerRadii[6] = if (globalStore.leftBottomCorner != VALUE_NULL_FLOAT) globalStore.leftBottomCorner else globalStore.corner
        cornerRadii[7] = if (globalStore.leftBottomCorner != VALUE_NULL_FLOAT) globalStore.leftBottomCorner else globalStore.corner
        beautifulCanvas.cornerRadii = cornerRadii
        //设置边框颜色和边框宽度,边框不变色
        if (globalStore.borderWidth != VALUE_NULL && globalStore.borderColor != VALUE_NULL) {
            beautifulCanvas.setStroke(globalStore.borderWidth, globalStore.borderColor, globalStore.borderDashWidth, globalStore.borderDashGap)
        }
        //设置形状
        beautifulCanvas.shape = if (globalStore.shape == CIRCLE) GradientDrawable.OVAL else GradientDrawable.RECTANGLE

        return beautifulCanvas
    }

    /**
     * dp转换px
     */
    private fun dp2px(dpValue: Float): Float {
        val scale = paintObject.context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}