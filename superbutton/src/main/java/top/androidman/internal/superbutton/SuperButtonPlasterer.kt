package top.androidman.internal.superbutton

import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import top.androidman.internal.*
import top.androidman.internal.Constant.VALUE_NULL


/**
 * @author         yanjie
 * @date           2019-12-17 23:50
 * @version        1.0
 */

class SuperButtonPlasterer(linearLayout: LinearLayout, valueStore: SuperButtonDefaultStore) : Plasterer(linearLayout, valueStore) {

    /**
     * 粉刷图纸
     */
    private var globalStore: SuperButtonDefaultStore = valueStore
    /**
     * 粉刷对象
     */
    private var paintObject: LinearLayout = linearLayout


    /**
     * 设置文字
     * @param text CharSequence
     */
    fun setText(text: CharSequence) {
        globalStore.text = text
    }

    /**
     * 设置字体颜色
     */
    fun setTextColor(@ColorInt textColor: Int): Plasterer {
        globalStore.textColor = textColor
        return this
    }

    /**
     * 设置提示文字
     * @param text CharSequence
     */
    fun setHintText(hintText: CharSequence) {
        globalStore.hintText = hintText
    }

    /**
     * 设置提示字体颜色
     */
    fun setHintTextColor(@ColorInt hintTextColor: Int): Plasterer {
        globalStore.hintTextColor = hintTextColor
        return this
    }

    /**
     * 设置单行显示
     * @param singleLine Boolean
     */
    fun setSingleLine(singleLine: Boolean) {
        globalStore.singleLine = singleLine
    }

    /**
     * 设置图标宽高
     * @param iconWidth Int
     * @param iconHeight Int
     */
    fun setIconWidthHeight(iconWidth: Int, iconHeight: Int) {
        globalStore.iconWidth = iconWidth
        globalStore.iconHeight = iconHeight
    }

    /**
     * 设置图标资源
     * @param icon Drawable
     */
    fun setIcon(icon: Drawable) {
        globalStore.icon = icon
    }

    /**
     * 图标和文字间的距离
     * @param iconPadding Int
     */
    fun setIconPadding(iconPadding: Int) {
        globalStore.iconPadding = iconPadding
    }


    /**
     * 图标在文字的方向
     * @param orientation Int
     */
    fun setIconOrientation(@IconOrientation orientation: Int) {
        globalStore.iconAtTextOrientation = orientation
    }

    override fun startPaint() {
        paintObject.removeAllViews()
        super.startPaint()
        //全部内容居中
        paintObject.gravity = Gravity.CENTER

        //创建文字组件
        val textContent = AppCompatTextView(paintObject.context)
        textContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, globalStore.textSize.toFloat())
        textContent.setTextColor(globalStore.textColor)
        textContent.text = globalStore.text
        textContent.hint = globalStore.hintText
        textContent.setHintTextColor(globalStore.hintTextColor)
        textContent.setSingleLine(globalStore.singleLine)
        val textLayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        //创建图标组件
        val iconContent = AppCompatImageView(paintObject.context)
        iconContent.setBackgroundDrawable(globalStore.icon)
        val iconLayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        if (globalStore.iconWidth != VALUE_NULL && globalStore.iconHeight != VALUE_NULL) {
            iconLayoutParams.width = globalStore.iconWidth
            iconLayoutParams.height = globalStore.iconHeight
        }

        val hasIcon = globalStore.icon != null
        val hasText = globalStore.text.isNotEmpty() || globalStore.hintText.isNotEmpty()

        when (globalStore.iconAtTextOrientation) {
            //图标在文字上面
            TOP -> {
                paintObject.orientation = LinearLayout.VERTICAL
                if (hasIcon) {
                    paintObject.addView(iconContent, iconLayoutParams)
                }
                if (hasText) {
                    textLayoutParams.topMargin = globalStore.iconPadding
                    paintObject.addView(textContent, textLayoutParams)
                }
            }
            //图标在文字下面
            BOTTOM -> {
                paintObject.orientation = LinearLayout.VERTICAL
                if (hasText) {
                    textLayoutParams.bottomMargin = globalStore.iconPadding
                    paintObject.addView(textContent, textLayoutParams)
                }
                if (hasIcon) {
                    paintObject.addView(iconContent, iconLayoutParams)
                }
            }
            //图标在文字右面
            RIGHT -> {
                paintObject.orientation = LinearLayout.HORIZONTAL
                if (hasText) {
                    textLayoutParams.rightMargin = globalStore.iconPadding
                    paintObject.addView(textContent, textLayoutParams)
                }
                if (hasIcon) {
                    paintObject.addView(iconContent, iconLayoutParams)
                }
            }
            //图标在文字左面
            LEFT -> {
                paintObject.orientation = LinearLayout.HORIZONTAL
                if (hasIcon) {
                    paintObject.addView(iconContent, iconLayoutParams)
                }
                if (hasText) {
                    textLayoutParams.leftMargin = globalStore.iconPadding
                    paintObject.addView(textContent, textLayoutParams)
                }
            }
        }

    }

}