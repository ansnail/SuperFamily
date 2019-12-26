package top.androidman.internal.superbutton

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import top.androidman.internal.IconOrientation


/**
 * @author         yanjie
 * @date           2019-12-25 23:40
 * @version        1.0
 * SuperButton的粉刷匠行为
 */
interface ISuperButtonPlastererAction {
    /**
     * 设置文字
     * @param text CharSequence
     */
    fun setText(text: CharSequence?)

    /**
     * 设置字体颜色
     */
    fun setTextColor(@ColorInt textColor: Int)

    /**
     * 设置提示文字
     * @param hintText CharSequence
     */
    fun setHintText(hintText: CharSequence?)

    /**
     * 设置提示字体颜色
     */
    fun setHintTextColor(@ColorInt hintTextColor: Int)

    /**
     * 设置单行显示
     * @param singleLine Boolean
     */
    fun setSingleLine(singleLine: Boolean)

    /**
     * 设置图标宽高
     * @param iconWidth Int
     * @param iconHeight Int
     */
    fun setIconWidthHeight(iconWidth: Int, iconHeight: Int)

    /**
     * 设置图标资源
     * @param icon Drawable
     */
    fun setIcon(icon: Drawable)

    /**
     * 图标和文字间的距离
     * @param iconPadding Int
     */
    fun setIconPadding(iconPadding: Int)

    /**
     * 图标在文字的方向
     * @param orientation Int
     */
    fun setIconOrientation(@IconOrientation orientation: Int)

    /**
     * 设置最大字符长度
     * @param maxLength Int
     */
    fun setMaxLength(maxLength: Int)

}