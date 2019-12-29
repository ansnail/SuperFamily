import android.graphics.Color
import top.androidman.internal.Constant.VALUE_NULL

/**
 * @author         yanjie
 * @date           2019-12-17 23:54
 * @version        1.0
 */
class SuperLineDefaultStore {
    /**
     * 直线方向
     */
    var lineOrientation = VALUE_NULL
    /**
     * 直线颜色
     */
    var lineColor = Color.GRAY
    /**
     * 直线渐变开始颜色
     */
    var lineStartColor = VALUE_NULL
    /**
     * 直线渐变结束颜色
     */
    var lineEndColor = VALUE_NULL
    /**
     * 虚线间隙宽度
     */
    var lineDashGapWidth = VALUE_NULL
    /**
     * 虚线宽度
     */
    var lineDashWidth = VALUE_NULL
    /**
     * 虚线颜色
     */
    var lineDashGapColor = VALUE_NULL
}