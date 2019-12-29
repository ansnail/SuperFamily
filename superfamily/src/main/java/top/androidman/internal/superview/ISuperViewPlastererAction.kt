package top.androidman.internal.superview

import androidx.annotation.ColorInt
import top.androidman.internal.ColorOrientation
import top.androidman.internal.Shape


/**
 * @author         yanjie
 * @date           2019-12-25 23:27
 * @version        1.0
 * 通用粉刷匠行为
 */

interface ISuperViewPlastererAction {

    /**
     * 设置正常状态下颜色
     */
    fun setNormalColor(@ColorInt normalColor: Int)

    /**
     * 是否开启点击效果,默认关闭
     */
    fun setOpenPressedEffect(open: Boolean)

    /**
     * 按压时的背景颜色
     */
    fun setPressedColor(@ColorInt pressedColor: Int)

    /**
     * 设置是否可以点击，默认可以
     */
    fun setViewClickable(clickable: Boolean)

    /**
     * 不能点击时的颜色
     */
    fun setDisableColor(@ColorInt disableColor: Int)

    /**
     * 设置圆角
     */
    fun setCorners(corner: Float)

    /**
     * 设置按钮左上角圆角角度，单位为dp
     */
    fun setLeftTopCorner(leftTopCorner: Float)

    /**
     * 设置按钮右上角圆角角度，单位为dp
     */
    fun setRightTopCorner(rightTopCorner: Float)

    /**
     * 设置按钮右下角圆角角度，单位为dp
     */
    fun setRightBottomCorner(rightBottomCorner: Float)

    /**
     * 设置按钮左下角圆角角度，单位为dp
     */
    fun setLeftBottomCorner(leftBottomCorner: Float)

    /**
     * 设置按钮所有圆角角度，单位为dp
     * @param leftTopCorner Float 左上角圆角角度
     * @param rightTopCorner Float 右上角圆角角度
     * @param rightBottomCorner Float 右下角圆角角度
     * @param leftBottomCorner Float 左下角圆角角度
     */
    fun setCorners(leftTopCorner: Float, rightTopCorner: Float, rightBottomCorner: Float, leftBottomCorner: Float)

    /**
     * 设置边框
     * @param borderColor Int 边框颜色
     * @param borderWidth Int 边框宽度
     * @param borderDashWidth Float 边框虚线宽度
     * @param borderDashGap Float 边框虚线间隙宽度
     */
    fun setBorder(@ColorInt borderColor: Int, borderWidth: Int, borderDashWidth: Float, borderDashGap: Float)

    /**
     * 设置边框颜色
     * @param borderColor Int
     */
    fun setBorderColor(@ColorInt borderColor: Int)

    /**
     * 设置边框宽度，单位为dp
     * @param borderWidth Int 边框宽度
     */
    fun setBorderWidth(borderWidth: Int)

    /**
     * 设置边框虚线
     * @param borderDashWidth Float 边框虚线宽度
     * @param borderDashGap Float 边框虚线间隙宽度
     */
    fun setBorderDash(borderDashWidth: Float, borderDashGap: Float)

    /**
     * 设置渐变色
     * @param orientation Int 方向
     * @param startColor Int 开始颜色
     * @param endColor Int 结束颜色
     */
    fun setColors(@ColorOrientation orientation: Int, @ColorInt startColor: Int, @ColorInt endColor: Int)

    /**
     * 设置阴影
     * @param shadowSize Int 阴影宽度
     * @param shadowStartColor Int 阴影开始颜色
     * @param shadowEndColor Int 阴影结束颜色
     */
    fun setShadowColors(shadowSize: Int, @ColorInt shadowStartColor: Int, @ColorInt shadowEndColor: Int)

    /**
     * 设置形状
     * @param shape Int
     */
    fun setShape(@Shape shape: Int)
}