package top.androidman.internal.superbutton

import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import top.androidman.internal.Plasterer


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


    override fun startPaint() {
        super.startPaint()
        //1.全部内容居中
        paintObject.gravity = Gravity.CENTER
        //2.创建文字组件
        val textContent = AppCompatTextView(paintObject.context)
        //3.设置文字大小
        textContent.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50f)
        //4.设置文字颜色
        textContent.setTextColor(Color.RED)
        //5.设置文字内容
        textContent.text = globalStore.text

        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        paintObject.addView(textContent, layoutParams)

    }

}