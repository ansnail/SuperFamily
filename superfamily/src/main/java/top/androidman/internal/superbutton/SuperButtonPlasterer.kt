package top.androidman.internal.superbutton

import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import top.androidman.internal.*
import top.androidman.internal.Constant.VALUE_NULL
import top.androidman.internal.superview.Plasterer


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
     * 图标对象
     */
    var iconView: AppCompatImageView = AppCompatImageView(linearLayout.context)
    /**
     * 文本对象
     */
    var textView: AppCompatTextView = AppCompatTextView(linearLayout.context)


    /**
     * 设置文字
     * @param text CharSequence
     */
    fun setText(text: CharSequence): SuperButtonPlasterer {
        globalStore.text = text
        return this
    }

    /**
     * 设置字体颜色
     */
    fun setTextColor(@ColorInt textColor: Int): SuperButtonPlasterer {
        globalStore.textColor = textColor
        return this
    }

    /**
     * 设置提示文字
     * @param hintText CharSequence
     */
    fun setHintText(hintText: CharSequence): SuperButtonPlasterer {
        globalStore.hintText = hintText
        return this
    }

    /**
     * 设置提示字体颜色
     */
    fun setHintTextColor(@ColorInt hintTextColor: Int): SuperButtonPlasterer {
        globalStore.hintTextColor = hintTextColor
        return this
    }

    /**
     * 设置单行显示
     * @param singleLine Boolean
     */
    fun setSingleLine(singleLine: Boolean): SuperButtonPlasterer {
        globalStore.singleLine = singleLine
        return this
    }

    /**
     * 设置图标宽高
     * @param iconWidth Int
     * @param iconHeight Int
     */
    fun setIconWidthHeight(iconWidth: Int, iconHeight: Int): SuperButtonPlasterer {
        globalStore.iconWidth = iconWidth
        globalStore.iconHeight = iconHeight
        return this
    }

    /**
     * 设置图标资源
     * @param icon Drawable
     */
    fun setIcon(icon: Drawable): SuperButtonPlasterer {
        globalStore.icon = icon
        return this
    }

    /**
     * 图标和文字间的距离
     * @param iconPadding Int
     */
    fun setIconPadding(iconPadding: Int): SuperButtonPlasterer {
        globalStore.iconPadding = iconPadding
        return this
    }

    /**
     * 图标在文字的方向
     * @param orientation Int
     */
    fun setIconOrientation(@IconOrientation orientation: Int): SuperButtonPlasterer {
        globalStore.iconAtTextOrientation = orientation
        return this
    }

    /**
     * 设置最大字符长度
     * @param maxLength Int
     */
    fun setMaxLength(maxLength: Int): SuperButtonPlasterer {
        if (maxLength >= 1) {
            globalStore.maxLength = maxLength
        }
        return this
    }

    override fun startPaint() {
        paintObject.removeAllViews()
        super.startPaint()
        //全部内容居中
        paintObject.gravity = Gravity.CENTER
        //创建文字组件
        textView.filters = arrayOf(InputFilter.LengthFilter(globalStore.maxLength))
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, globalStore.textSize.toFloat())
        textView.setTextColor(globalStore.textColor)
        textView.text = globalStore.text
        textView.hint = globalStore.hintText
        textView.setHintTextColor(globalStore.hintTextColor)
        textView.setSingleLine(globalStore.singleLine)
        val textLayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        //创建图标组件
        iconView.setBackgroundDrawable(globalStore.icon)
        val iconLayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        if (globalStore.iconWidth != VALUE_NULL && globalStore.iconHeight != VALUE_NULL) {
            iconLayoutParams.width = globalStore.iconWidth
            iconLayoutParams.height = globalStore.iconHeight
        } else {
            if (globalStore.iconAuto && globalStore.textSize != 0) {
                iconLayoutParams.width = (globalStore.textSize * 1.2f).toInt()
                iconLayoutParams.height = (globalStore.textSize * 1.2f).toInt()
            }
        }

        val hasIcon = globalStore.icon != null
        val hasText = globalStore.text.isNotEmpty() || globalStore.hintText.isNotEmpty()

        when (globalStore.iconAtTextOrientation) {
            //图标在文字上面
            TOP -> {
                paintObject.orientation = LinearLayout.VERTICAL
                if (hasIcon) {
                    paintObject.addView(iconView, iconLayoutParams)
                }
                if (hasText) {
                    textLayoutParams.topMargin = globalStore.iconPadding
                    paintObject.addView(textView, textLayoutParams)
                }
            }
            //图标在文字下面
            BOTTOM -> {
                paintObject.orientation = LinearLayout.VERTICAL
                if (hasText) {
                    textLayoutParams.bottomMargin = globalStore.iconPadding
                    paintObject.addView(textView, textLayoutParams)
                }
                if (hasIcon) {
                    paintObject.addView(iconView, iconLayoutParams)
                }
            }
            //图标在文字右面
            RIGHT -> {
                paintObject.orientation = LinearLayout.HORIZONTAL
                if (hasText) {
                    textLayoutParams.rightMargin = globalStore.iconPadding
                    paintObject.addView(textView, textLayoutParams)
                }
                if (hasIcon) {
                    paintObject.addView(iconView, iconLayoutParams)
                }
            }
            //图标在文字左面
            LEFT -> {
                paintObject.orientation = LinearLayout.HORIZONTAL
                if (hasIcon) {
                    paintObject.addView(iconView, iconLayoutParams)
                }
                if (hasText) {
                    textLayoutParams.leftMargin = globalStore.iconPadding
                    paintObject.addView(textView, textLayoutParams)
                }
            }
        }

    }

}