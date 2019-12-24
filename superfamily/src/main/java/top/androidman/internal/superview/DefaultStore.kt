package top.androidman.internal.superview

import android.graphics.drawable.GradientDrawable
import top.androidman.internal.Constant.VALUE_DEFAULT_FLOAT
import top.androidman.internal.Constant.VALUE_DEFAULT_INT
import top.androidman.internal.Constant.VALUE_NULL
import top.androidman.internal.Constant.VALUE_NULL_FLOAT
import top.androidman.internal.RECT


/**
 * @author         yanjie
 * @date           2019-12-15 14:50
 * @version        1.0
 */

open class DefaultStore {
    /**
     * 正常颜色
     */
    var backgroundNormalColor = VALUE_NULL

    /**
     * 圆角半径
     */
    var corner = VALUE_DEFAULT_FLOAT
    /**
     * 左上
     */
    var leftTopCorner = VALUE_NULL_FLOAT
    /**
     * 左下
     */
    var leftBottomCorner = VALUE_NULL_FLOAT
    /**
     * 右上
     */
    var rightTopCorner = VALUE_NULL_FLOAT
    /**
     * 右下
     */
    var rightBottomCorner = VALUE_NULL_FLOAT

    /**
     * 阴影起始颜色
     */
    var shadowStartColor = VALUE_NULL
    /**
     * 阴影结束颜色
     */
    var shadowEndColor = VALUE_NULL
    /**
     * 阴影大小
     */
    var shadowSize = VALUE_NULL

    /**
     * 边框宽度
     */
    var borderWidth = VALUE_NULL
    /**
     * 边框颜色
     */
    var borderColor = VALUE_NULL
    /**
     * 边框虚线间隙值
     */
    var borderDashGap = VALUE_DEFAULT_FLOAT
    /**
     * 边框虚线宽度
     */
    var borderDashWidth = VALUE_DEFAULT_FLOAT

    /**
     * 形状
     */
    var shape = RECT

    /**
     * 渐变起始颜色
     */
    var backgroundStartColor = VALUE_DEFAULT_INT
    /**
     * 渐变结束颜色
     */
    var backgroundEndColor = VALUE_DEFAULT_INT
    /**
     * 颜色方向
     */
    var backgroundColorOrientation: GradientDrawable.Orientation = GradientDrawable.Orientation.LEFT_RIGHT

    /**
     * 是否开启点击效果,默认关闭
     */
    var openPressedEffect = false
    /**
     * 按压时的背景颜色
     */
    var backgroundPressedColor = VALUE_NULL
    /**
     * 按钮是否可以点击,默认可以
     */
    var clickable = true
    /**
     * 不能点击时的颜色
     */
    var disableColor = VALUE_NULL
}