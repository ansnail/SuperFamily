package top.androidman

import SuperLineDefaultStore
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import top.androidman.internal.Constant.VALUE_NULL
import top.androidman.internal.HORIZONTAL
import top.androidman.internal.LineOrientation
import top.androidman.internal.VERTICAL
import top.androidman.internal.superline.SuperLineAttributeSetHelper


/**
 * @author         yanjie
 * @date           2019-12-28 00:45
 * @version        1.0
 */
class SuperLine @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr) {


    /**
     * 粉刷图纸
     */
    private val valueStore: SuperLineDefaultStore by lazy {
        val store = SuperLineDefaultStore()
        SuperLineAttributeSetHelper.loadFromAttributeSet(context, attrs, store)
        return@lazy store
    }

    init {
        valueStore
    }

    /**
     * 设置方向
     * @param orientation Int
     */
    fun setOrientation(@LineOrientation orientation: Int) {
        valueStore.lineOrientation = orientation
        invalidate()
    }

    /**
     * 设置线的颜色
     * @param color Int
     */
    fun setLineColor(@ColorInt color: Int) {
        valueStore.lineColor = color
        invalidate()
    }

    /**
     * 设置线的渐变开始颜色
     * @param color Int
     */
    fun setLineStartColor(@ColorInt color: Int) {
        valueStore.lineStartColor = color
        invalidate()
    }

    /**
     * 设置线的渐变结束颜色
     * @param color Int
     */
    fun setLineEndColor(@ColorInt color: Int) {
        valueStore.lineEndColor = color
        invalidate()
    }

    /**
     * 设置间隙的颜色
     * @param color Int
     */
    fun setLineDashGapColor(@ColorInt color: Int) {
        valueStore.lineDashGapColor = color
        invalidate()
    }

    /**
     * 设置虚线宽度
     * @param width Int
     */
    fun setLineDashWidth(width: Int) {
        valueStore.lineDashWidth = width
        invalidate()
    }

    /**
     * 设置虚线间隙宽度
     * @param width Int
     */
    fun setlineDashGapWidth(width: Int) {
        valueStore.lineDashGapWidth = width
        invalidate()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.STROKE
        val orientation = if (valueStore.lineOrientation != VALUE_NULL) {
            valueStore.lineOrientation
        } else {
            if (measuredWidth >= measuredHeight) HORIZONTAL else VERTICAL
        }
        paint.strokeWidth = if (orientation == HORIZONTAL) measuredWidth.toFloat() else measuredHeight.toFloat()

        val startX = if (orientation == HORIZONTAL) 0f else measuredWidth / 2.0f
        val startY = if (orientation == HORIZONTAL) measuredHeight / 2.0f else 0f

        val endX = if (orientation == HORIZONTAL) measuredWidth.toFloat() else measuredWidth / 2.0f
        val endY = if (orientation == HORIZONTAL) measuredHeight / 2.0f else measuredHeight.toFloat()

        val path = Path()
        //底部
        if (valueStore.lineDashGapColor != VALUE_NULL) {
            paint.color = valueStore.lineDashGapColor
            path.reset()
            path.moveTo(startX, startY)
            path.lineTo(endX, endY)
            canvas?.drawPath(path, paint)
        }
        //上部
        if (valueStore.lineStartColor != VALUE_NULL && valueStore.lineEndColor != VALUE_NULL) {
            paint.shader = LinearGradient(startX, startY, endX, endY,
                    valueStore.lineStartColor, valueStore.lineEndColor, Shader.TileMode.CLAMP)
        } else {
            paint.color = valueStore.lineColor
        }
        if (valueStore.lineDashWidth != VALUE_NULL && valueStore.lineDashGapWidth != VALUE_NULL) {
            paint.pathEffect = DashPathEffect(floatArrayOf(valueStore.lineDashWidth.toFloat(),
                    valueStore.lineDashGapWidth.toFloat()), 0F)
        }

        path.reset()
        path.moveTo(startX, startY)
        path.lineTo(endX, endY)
        canvas?.drawPath(path, paint)
    }
}