package top.androidman.internal.superline

import SuperLineDefaultStore
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import top.androidman.R
import top.androidman.internal.Constant.VALUE_NULL
import top.androidman.internal.HORIZONTAL


/**
 * @author         yanjie
 * @date           2019-12-15 14:47
 * @version        1.0
 */
object SuperLineAttributeSetHelper {

    /**
     * 解析独有属性
     */
    fun loadFromAttributeSet(context: Context, attrs: AttributeSet?, defaultStore: SuperLineDefaultStore = SuperLineDefaultStore()): SuperLineDefaultStore {
        if (attrs == null) {
            return defaultStore
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperLine)
        val length = typedArray.indexCount
        for (i in 0 until length) {
            val attr = typedArray.getIndex(i)
            //直线方向
            if (attr == R.styleable.SuperLine_orientation) {
                defaultStore.lineOrientation = typedArray.getInt(attr, HORIZONTAL)
            }
            //直线颜色
            if (attr == R.styleable.SuperLine_line_color) {
                defaultStore.lineColor = typedArray.getColor(attr, Color.GRAY)
            }
            //直线渐变开始颜色
            if (attr == R.styleable.SuperLine_line_startColor) {
                defaultStore.lineStartColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //直线渐变结束颜色
            if (attr == R.styleable.SuperLine_line_endColor) {
                defaultStore.lineEndColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //虚线宽度
            if (attr == R.styleable.SuperLine_line_dashWidth) {
                defaultStore.lineDashWidth = typedArray.getDimensionPixelSize(attr, VALUE_NULL)
            }
            //虚线间隙宽度
            if (attr == R.styleable.SuperLine_line_dashGapWidth) {
                defaultStore.lineDashGapWidth = typedArray.getDimensionPixelSize(attr, VALUE_NULL)
            }
            //虚线间隙颜色
            if (attr == R.styleable.SuperLine_line_dashGapColor) {
                defaultStore.lineDashGapColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //虚线间隙渐变开始颜色
            if (attr == R.styleable.SuperLine_line_dashGap_startColor) {
                defaultStore.lineDashGapStartColor = typedArray.getColor(attr, VALUE_NULL)
            }
            //虚线间隙渐变结束颜色
            if (attr == R.styleable.SuperLine_line_dashGap_endColor) {
                defaultStore.lineDashGapEndColor = typedArray.getColor(attr, VALUE_NULL)
            }
        }
        typedArray.recycle()
        return defaultStore
    }


}