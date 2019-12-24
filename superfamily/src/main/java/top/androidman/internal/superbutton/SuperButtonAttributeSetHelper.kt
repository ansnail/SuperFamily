package top.androidman.internal.superbutton

import android.content.Context
import android.util.AttributeSet
import top.androidman.R
import top.androidman.internal.BOTTOM
import top.androidman.internal.Constant.DEFAULT_HINT_TEXT_COLOR
import top.androidman.internal.Constant.DEFAULT_TEXT_COLOR
import top.androidman.internal.Constant.DEFAULT_TEXT_SIZE
import top.androidman.internal.Constant.VALUE_DEFAULT_INT
import top.androidman.internal.Constant.VALUE_NULL
import top.androidman.internal.LEFT
import top.androidman.internal.RIGHT
import top.androidman.internal.TOP


/**
 * @author         yanjie
 * @date           2019-12-15 14:47
 * @version        1.0
 */
object SuperButtonAttributeSetHelper {

    /**
     * 解析独有属性
     */
    fun loadFromAttributeSet(context: Context, attrs: AttributeSet?, defaultStore: SuperButtonDefaultStore = SuperButtonDefaultStore()): SuperButtonDefaultStore {
        if (attrs == null) {
            return defaultStore
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperButton)
        val length = typedArray.indexCount
        for (i in 0 until length) {
            val attr = typedArray.getIndex(i)
            //文字内容
            if (attr == R.styleable.SuperButton_text) {
                defaultStore.text = typedArray.getText(attr)
            }
            //文字颜色
            if (attr == R.styleable.SuperButton_textColor) {
                defaultStore.textColor = typedArray.getColor(attr, DEFAULT_TEXT_COLOR)
            }
            //提示文字内容
            if (attr == R.styleable.SuperButton_hintText) {
                defaultStore.hintText = typedArray.getText(attr)
            }
            //提示文字颜色
            if (attr == R.styleable.SuperButton_hintTextColor) {
                defaultStore.hintTextColor = typedArray.getColor(attr, DEFAULT_HINT_TEXT_COLOR)
            }
            //文字大小
            if (attr == R.styleable.SuperButton_textSize) {
                defaultStore.textSize = typedArray.getDimensionPixelSize(attr, DEFAULT_TEXT_SIZE)
            }
            //文字是否单行
            if (attr == R.styleable.SuperButton_singleLine) {
                defaultStore.singleLine = typedArray.getBoolean(attr, true)
            }

            //图片资源
            if (attr == R.styleable.SuperButton_icon) {
                defaultStore.icon = typedArray.getDrawable(attr)
            }
            //图标距离文字距离
            if (attr == R.styleable.SuperButton_iconPadding) {
                defaultStore.iconPadding = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT)
            }
            //图标的宽度
            if (attr == R.styleable.SuperButton_iconWidth) {
                defaultStore.iconWidth = typedArray.getDimensionPixelSize(attr, VALUE_NULL)
            }
            //图标的高度
            if (attr == R.styleable.SuperButton_iconHeight) {
                defaultStore.iconHeight = typedArray.getDimensionPixelSize(attr, VALUE_NULL)
            }
            //自动适应文字的大小
            if (attr == R.styleable.SuperButton_iconAuto) {
                defaultStore.iconAuto = typedArray.getBoolean(attr, false)
            }
            //图标在文字的那个方向
            if (attr == R.styleable.SuperButton_iconOrientation) {
                defaultStore.iconAtTextOrientation = typedArray.getInt(attr, LEFT)
            }
            //字符最大长度
            if (attr == R.styleable.SuperButton_maxLength) {
                defaultStore.maxLength = typedArray.getInt(attr, VALUE_NULL)
            }

            //////////////////////////////////////////属性兼容//////////////////////////////////////////
            //图片在文字左边
            if (attr == R.styleable.SuperButton_drawable_left) {
                defaultStore.icon = typedArray.getDrawable(attr)
                defaultStore.iconAtTextOrientation = LEFT
            }
            //图片在文字右边
            if (attr == R.styleable.SuperButton_drawable_right) {
                defaultStore.icon = typedArray.getDrawable(attr)
                defaultStore.iconAtTextOrientation = RIGHT
            }
            //图片在文字上边
            if (attr == R.styleable.SuperButton_drawable_top) {
                defaultStore.icon = typedArray.getDrawable(attr)
                defaultStore.iconAtTextOrientation = TOP
            }
            //图片在文字下边
            if (attr == R.styleable.SuperButton_drawable_bottom) {
                defaultStore.icon = typedArray.getDrawable(attr)
                defaultStore.iconAtTextOrientation = BOTTOM
            }
            //图标距离文字距离
            if (attr == R.styleable.SuperButton_drawable_padding) {
                defaultStore.iconPadding = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT)
            }
            //自动适应文字的大小
            if (attr == R.styleable.SuperButton_drawable_auto) {
                defaultStore.iconAuto = typedArray.getBoolean(attr, false)
            }

            //图片在中间
            if (attr == R.styleable.SuperButton_drawable_middle) {
                defaultStore.icon = typedArray.getDrawable(attr)
            }
            //图标的宽度
            if (attr == R.styleable.SuperButton_drawable_middle_width) {
                defaultStore.iconWidth = typedArray.getDimensionPixelSize(attr, VALUE_NULL)
            }
            //图标的高度
            if (attr == R.styleable.SuperButton_drawable_middle_height) {
                defaultStore.iconHeight = typedArray.getDimensionPixelSize(attr, VALUE_NULL)
            }

            //////////////////////////////////////////属性兼容//////////////////////////////////////////
        }
        typedArray.recycle()
        return defaultStore
    }


}