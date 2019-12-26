package top.androidman

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import top.androidman.internal.superbutton.ISuperButtonPlastererAction
import top.androidman.internal.superbutton.SuperButtonAttributeSetHelper
import top.androidman.internal.superbutton.SuperButtonDefaultStore
import top.androidman.internal.superbutton.SuperButtonPlasterer
import top.androidman.internal.superview.AttributeSetHelper
import top.androidman.internal.superview.ISuperViewPlastererAction


/**
 * @author         yanjie
 * @date           2019-12-17 17:47
 * @version        1.0
 * @description
 */
class SuperButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr), ISuperViewPlastererAction, ISuperButtonPlastererAction {

    /**
     * 粉刷图纸
     */
    private val valueStore: SuperButtonDefaultStore by lazy {
        val store = SuperButtonDefaultStore()
        AttributeSetHelper.loadFromAttributeSet(context, attrs, store)
        SuperButtonAttributeSetHelper.loadFromAttributeSet(context, attrs, store)
        return@lazy store
    }
    /**
     * 粉刷匠
     */
    private val plasterer: SuperButtonPlasterer by lazy {
        return@lazy SuperButtonPlasterer(this, valueStore)
    }

    init {
        plasterer.startPaint()

    }

    /**
     * 获取文字组件
     * @return AppCompatTextView
     */
    fun getTextView(): AppCompatTextView {
        return plasterer.textView
    }

    /**
     * 获取图标的view
     * @return AppCompatImageView
     */
    fun getIconView(): AppCompatImageView {
        return plasterer.iconView
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

    /**
     * 设置文字
     * @param text CharSequence
     */
    override fun setText(text: CharSequence?) {
        plasterer.setText(text).startPaint()
    }

    /**
     * 设置字体颜色
     */
    override fun setTextColor(textColor: Int) {
        plasterer.setTextColor(textColor).startPaint()
    }

    /**
     * 设置提示文字
     * @param hintText CharSequence
     */
    override fun setHintText(hintText: CharSequence?) {
        plasterer.setHintText(hintText).startPaint()
    }

    /**
     * 设置提示字体颜色
     */
    override fun setHintTextColor(hintTextColor: Int) {
        plasterer.setHintTextColor(hintTextColor).startPaint()
    }

    /**
     * 设置单行显示
     * @param singleLine Boolean
     */
    override fun setSingleLine(singleLine: Boolean) {
        plasterer.setSingleLine(singleLine).startPaint()
    }

    /**
     * 设置图标宽高
     * @param iconWidth Int
     * @param iconHeight Int
     */
    override fun setIconWidthHeight(iconWidth: Int, iconHeight: Int) {
        plasterer.setIconWidthHeight(iconWidth, iconHeight).startPaint()
    }

    /**
     * 设置图标资源
     * @param icon Drawable
     */
    override fun setIcon(icon: Drawable) {
        plasterer.setIcon(icon).startPaint()
    }

    /**
     * 图标和文字间的距离
     * @param iconPadding Int
     */
    override fun setIconPadding(iconPadding: Int) {
        plasterer.setIconPadding(iconPadding).startPaint()
    }

    /**
     * 图标在文字的方向
     * @param orientation Int
     */
    override fun setIconOrientation(orientation: Int) {
        plasterer.setIconOrientation(orientation).startPaint()
    }

    /**
     * 设置最大字符长度
     * @param maxLength Int
     */
    override fun setMaxLength(maxLength: Int) {
        plasterer.setMaxLength(maxLength).startPaint()
    }


}