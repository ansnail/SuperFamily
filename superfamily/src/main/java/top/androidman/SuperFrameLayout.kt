package top.androidman

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import top.androidman.internal.superview.AttributeSetHelper
import top.androidman.internal.superview.DefaultStore
import top.androidman.internal.superview.ISuperViewPlastererAction
import top.androidman.internal.superview.Plasterer


/**
 * @author         yanjie
 * @date           2019-12-17 00:09
 * @version        1.0
 */
class SuperFrameLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr) ,ISuperViewPlastererAction{
    /**
     * 粉刷图纸
     */
    private val valueStore: DefaultStore by lazy {
        return@lazy AttributeSetHelper.loadFromAttributeSet(context, attrs)
    }
    /**
     * 粉刷匠
     */
    private val plasterer: Plasterer by lazy {
        return@lazy Plasterer(this, valueStore)
    }

    init {
        plasterer.startPaint()
    }

    /**
     * 设置正常状态下颜色
     */
    override fun setNormalColor(normalColor: Int) {
        plasterer.setNormalColor(normalColor).startPaint()
    }

    /**
     * 是否开启点击效果,默认关闭
     */
    override fun setOpenPressedEffect(open: Boolean) {
        plasterer.setOpenPressedEffect(open).startPaint()
    }

    /**
     * 按压时的背景颜色
     */
    override fun setPressedColor(pressedColor: Int) {
        plasterer.setPressedColor(pressedColor).startPaint()
    }

    /**
     * 设置是否可以点击，默认可以
     */
    override fun setViewClickable(clickable: Boolean) {
        plasterer.setViewClickable(clickable).startPaint()
    }

    /**
     * 不能点击时的颜色
     */
    override fun setDisableColor(disableColor: Int) {
        plasterer.setDisableColor(disableColor).startPaint()
    }

    /**
     * 设置圆角
     */
    override fun setCorners(corner: Float) {
        plasterer.setCorners(corner).startPaint()
    }

    /**
     * 设置按钮所有圆角角度，单位为dp
     * @param leftTopCorner Float 左上角圆角角度
     * @param rightTopCorner Float 右上角圆角角度
     * @param rightBottomCorner Float 右下角圆角角度
     * @param leftBottomCorner Float 左下角圆角角度
     */
    override fun setCorners(leftTopCorner: Float, rightTopCorner: Float, rightBottomCorner: Float, leftBottomCorner: Float) {
        plasterer.setCorners(leftTopCorner, rightTopCorner, rightBottomCorner, leftBottomCorner).startPaint()
    }

    /**
     * 设置按钮左上角圆角角度，单位为dp
     */
    override fun setLeftTopCorner(leftTopCorner: Float) {
        plasterer.setLeftTopCorner(leftTopCorner).startPaint()
    }

    /**
     * 设置按钮右上角圆角角度，单位为dp
     */
    override fun setRightTopCorner(rightTopCorner: Float) {
        plasterer.setRightTopCorner(rightTopCorner).startPaint()
    }

    /**
     * 设置按钮右下角圆角角度，单位为dp
     */
    override fun setRightBottomCorner(rightBottomCorner: Float) {
        plasterer.setRightBottomCorner(rightBottomCorner).startPaint()
    }

    /**
     * 设置按钮左下角圆角角度，单位为dp
     */
    override fun setLeftBottomCorner(leftBottomCorner: Float) {
        plasterer.setLeftBottomCorner(leftBottomCorner).startPaint()
    }

    /**
     * 设置边框
     * @param borderColor Int 边框颜色
     * @param borderWidth Int 边框宽度
     * @param borderDashWidth Float 边框虚线宽度
     * @param borderDashGap Float 边框虚线间隙宽度
     */
    override fun setBorder(borderColor: Int, borderWidth: Int, borderDashWidth: Float, borderDashGap: Float) {
        plasterer.setBorder(borderColor, borderWidth, borderDashWidth, borderDashGap).startPaint()
    }

    /**
     * 设置背景渐变色
     * @param orientation Int 方向
     * @param startColor Int 开始颜色
     * @param endColor Int 结束颜色
     */
    override fun setColors(orientation: Int, startColor: Int, endColor: Int) {
        plasterer.setColors(orientation, startColor, endColor).startPaint()
    }

    /**
     * 设置阴影
     * @param shadowSize Int 阴影宽度
     * @param shadowStartColor Int 阴影开始颜色
     * @param shadowEndColor Int 阴影结束颜色
     */
    override fun setShadowColors(shadowSize: Int, shadowStartColor: Int, shadowEndColor: Int) {
        plasterer.setShadowColors(shadowSize, shadowStartColor, shadowEndColor).startPaint()
    }

    /**
     * 设置形状
     * @param shape Int
     */
    override fun setShape(shape: Int) {
        plasterer.setShape(shape).startPaint()
    }
}