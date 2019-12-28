package top.androidman

import SuperLineDefaultStore
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import top.androidman.internal.Constant.VALUE_NULL
import top.androidman.internal.superline.SuperLineAttributeSetHelper


/**
 * @author         yanjie
 * @date           2019-12-26 00:45
 * @version        1.0
 */
class SuperLine @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        View(context, attrs, defStyleAttr) {

    private val paint: Paint by lazy {
        return@lazy Paint(Paint.ANTI_ALIAS_FLAG)
    }

    /**
     * 粉刷图纸
     */
    private val valueStore: SuperLineDefaultStore by lazy {
        val store = SuperLineDefaultStore()
        SuperLineAttributeSetHelper.loadFromAttributeSet(context, attrs, store)
        return@lazy store
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        //先准备花底部颜色画笔
        if (valueStore.lineDashGapStartColor != VALUE_NULL && valueStore.lineDashGapEndColor != VALUE_NULL) {
            paint.shader = LinearGradient(0f, measuredHeight / 2.0f,
                    measuredWidth.toFloat(), measuredHeight / 2.0f,
                    valueStore.lineDashGapStartColor, valueStore.lineDashGapEndColor, Shader.TileMode.CLAMP)
        } else if (valueStore.lineDashGapColor != VALUE_NULL) {
            paint.color = valueStore.lineDashGapColor
        } else {
            paint.color = Color.TRANSPARENT
        }
        paint.strokeWidth = measuredHeight.toFloat()
        //画底部
        canvas?.drawLine(0f, measuredHeight / 2.0f, measuredWidth.toFloat(), measuredHeight / 2.0f, paint)
        if (valueStore.lineStartColor != VALUE_NULL && valueStore.lineEndColor != VALUE_NULL) {
            paint.shader = LinearGradient(0f, measuredHeight / 2.0f,
                    measuredWidth.toFloat(), measuredHeight / 2.0f,
                    valueStore.lineStartColor, valueStore.lineEndColor, Shader.TileMode.CLAMP)
        } else {
            paint.color = valueStore.lineColor
        }
        paint.strokeWidth = measuredHeight.toFloat()
        if (valueStore.lineDashWidth != VALUE_NULL && valueStore.lineDashGapWidth != VALUE_NULL) {
            paint.pathEffect = DashPathEffect(floatArrayOf(valueStore.lineDashWidth.toFloat(),
                    valueStore.lineDashGapWidth.toFloat()), 0F)
        }
        //画上层
        canvas?.drawLine(0f, measuredHeight / 2.0f, measuredWidth.toFloat(), measuredHeight / 2.0f, paint)
    }
}