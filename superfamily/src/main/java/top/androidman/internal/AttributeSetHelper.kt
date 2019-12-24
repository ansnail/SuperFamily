package top.androidman.internal

import android.content.Context
import android.util.AttributeSet
import top.androidman.R
import top.androidman.internal.Constant.VALUE_DEFAULT_INT
import top.androidman.internal.Constant.VALUE_NULL


/**
 * @author         yanjie
 * @date           2019-12-15 14:47
 * @version        1.0
 */
object AttributeSetHelper {

    /**
     * 解析属性
     */
    fun loadFromAttributeSet(context: Context, attrs: AttributeSet?, defaultStore: DefaultStore = DefaultStore()): DefaultStore {
        /**
         * 默认数据
         */
        if (attrs == null) {
            return defaultStore
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperView)
        val length = typedArray.indexCount
        for (i in 0 until length) {
            val attr = typedArray.getIndex(i)
            //默认背景颜色
            if (attr == R.styleable.SuperView_background_normalColor) {
                defaultStore.backgroundNormalColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //所有角圆角半径
            if (attr == R.styleable.SuperView_corner) {
                defaultStore.corner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //左上角圆角半径
            if (attr == R.styleable.SuperView_corner_leftTop) {
                defaultStore.leftTopCorner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //右上角圆角半径
            if (attr == R.styleable.SuperView_corner_rightTop) {
                defaultStore.rightTopCorner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //左下角圆角半径
            if (attr == R.styleable.SuperView_corner_leftBottom) {
                defaultStore.leftBottomCorner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //右下角圆角半径
            if (attr == R.styleable.SuperView_corner_rightBottom) {
                defaultStore.rightBottomCorner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }

            //阴影开始颜色
            if (attr == R.styleable.SuperView_shadow_startColor) {
                defaultStore.shadowStartColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //阴影结束颜色
            if (attr == R.styleable.SuperView_shadow_endColor) {
                defaultStore.shadowEndColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //阴影大小
            if (attr == R.styleable.SuperView_shadow_size) {
                defaultStore.shadowSize = typedArray.getDimensionPixelSize(attr, VALUE_NULL)
            }

            //边框宽度
            if (attr == R.styleable.SuperView_border_width) {
                defaultStore.borderWidth = typedArray.getDimensionPixelSize(attr, VALUE_NULL)
            }
            //边框虚线宽度
            if (attr == R.styleable.SuperView_border_dashWidth) {
                defaultStore.borderDashWidth = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //边框虚线间隙值
            if (attr == R.styleable.SuperView_border_dashGap) {
                defaultStore.borderDashGap = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //边框颜色
            if (attr == R.styleable.SuperView_border_color) {
                defaultStore.borderColor = typedArray.getColor(attr, VALUE_NULL)
            }

            //形状
            if (attr == R.styleable.SuperView_shape) {
                defaultStore.shape = typedArray.getInt(attr, 1)
            }

            //背景开始颜色
            if (attr == R.styleable.SuperView_background_startColor) {
                defaultStore.backgroundStartColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //背景结束颜色
            if (attr == R.styleable.SuperView_background_endColor) {
                defaultStore.backgroundEndColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //背景颜色方向
            if (attr == R.styleable.SuperView_background_colorOrientation) {
                defaultStore.backgroundColorOrientation = getColorOrientation(typedArray.getInt(attr, LEFT_RIGHT))
            }

            //是否开启点击效果,默认关闭
            if (attr == R.styleable.SuperView_open_pressed_effect) {
                defaultStore.openPressedEffect = typedArray.getBoolean(attr, false)
            }
            //按压时的背景颜色
            if (attr == R.styleable.SuperView_background_pressedColor) {
                defaultStore.backgroundPressedColor = typedArray.getColor(attr, VALUE_NULL)
            }

            //按钮是否可以点击
            if (attr == R.styleable.SuperView_clickable) {
                defaultStore.clickable = typedArray.getBoolean(attr, true)
            }
            //按钮不可点击时的颜色
            if (attr == R.styleable.SuperView_disableColor) {
                defaultStore.disableColor = typedArray.getColor(attr, VALUE_NULL)
            }

            //////////////////////////////////////////属性兼容//////////////////////////////////////////
            //默认背景颜色
            if (attr == R.styleable.SuperView_color_normal) {
                defaultStore.backgroundNormalColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //按压时的背景颜色
            if (attr == R.styleable.SuperView_color_pressed) {
                defaultStore.backgroundPressedColor = typedArray.getColor(attr, VALUE_NULL)
            }

            //背景开始颜色
            if (attr == R.styleable.SuperView_color_start) {
                defaultStore.backgroundStartColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //背景结束颜色
            if (attr == R.styleable.SuperView_color_end) {
                defaultStore.backgroundEndColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //背景颜色方向
            if (attr == R.styleable.SuperView_color_direction) {
                defaultStore.backgroundColorOrientation = getColorOrientation(typedArray.getInt(attr, LEFT_RIGHT))
            }

            //左上角圆角半径
            if (attr == R.styleable.SuperView_corner_left_top) {
                defaultStore.leftTopCorner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //右上角圆角半径
            if (attr == R.styleable.SuperView_corner_right_top) {
                defaultStore.rightTopCorner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //左下角圆角半径
            if (attr == R.styleable.SuperView_corner_left_bottom) {
                defaultStore.leftBottomCorner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }
            //右下角圆角半径
            if (attr == R.styleable.SuperView_corner_right_bottom) {
                defaultStore.rightBottomCorner = typedArray.getDimensionPixelSize(attr, VALUE_DEFAULT_INT).toFloat()
            }

            //阴影开始颜色
            if (attr == R.styleable.SuperView_color_shadow_start) {
                defaultStore.shadowStartColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //阴影结束颜色
            if (attr == R.styleable.SuperView_color_shadow_end) {
                defaultStore.shadowEndColor = typedArray.getColor(attr, VALUE_NULL)
            }

            //按钮是否可以点击
            if (attr == R.styleable.SuperView_button_click_able) {
                defaultStore.clickable = typedArray.getBoolean(attr, true)
            }

            //////////////////////////////////////////属性兼容//////////////////////////////////////////


        }
        typedArray.recycle()
        return defaultStore
    }


}