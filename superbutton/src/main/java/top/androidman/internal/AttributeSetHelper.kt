package top.androidman.internal

import android.content.Context
import android.util.AttributeSet
import top.androidman.Constant
import top.androidman.R


/**
 * @author         yanjie
 * @date           2019-12-15 14:47
 * @version        1.0
 */
object AttributeSetHelper {

    /**
     * 解析属性
     */
    fun loadFromAttributeSet(context: Context, attrs: AttributeSet?): DefaultStore {
        /**
         * 默认数据
         */
        val defaultStore = DefaultStore()
        if (attrs == null) {
            return defaultStore
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperConstraintLayout)
        val length = typedArray.indexCount
        for (i in 0 until length) {
            val attr = typedArray.getIndex(i)
            //默认背景颜色
            if (attr == R.styleable.SuperConstraintLayout_normalColor) {
                defaultStore.normalColor = typedArray.getColor(attr, Constant.VALUE_NULL)
            }
            //所有角圆角半径
            if (attr == R.styleable.SuperConstraintLayout_corner) {
                defaultStore.corner = typedArray.getDimensionPixelSize(attr, Constant.VALUE_DEFAULT).toFloat()
            }
            //阴影开始颜色
            if (attr == R.styleable.SuperConstraintLayout_shadow_start_color) {
                defaultStore.shadowStartColor = typedArray.getColor(attr, Constant.VALUE_NULL)
            }
            //阴影结束颜色
            if (attr == R.styleable.SuperConstraintLayout_shadow_end_color) {
                defaultStore.shadowEndColor = typedArray.getColor(attr, Constant.VALUE_NULL)
            }
            //阴影大小
            if (attr == R.styleable.SuperConstraintLayout_shadow_size) {
                defaultStore.shadowSize = typedArray.getDimensionPixelSize(attr, Constant.VALUE_NULL)
            }
        }
        typedArray.recycle()
        return defaultStore
    }


}