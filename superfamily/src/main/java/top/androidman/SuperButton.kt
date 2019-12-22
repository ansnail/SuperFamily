package top.androidman

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import top.androidman.internal.AttributeSetHelper
import top.androidman.internal.superbutton.SuperButtonAttributeSetHelper
import top.androidman.internal.superbutton.SuperButtonDefaultStore
import top.androidman.internal.superbutton.SuperButtonPlasterer


/**
 * @author         yanjie
 * @date           2019-12-17 17:47
 * @version        1.0
 * @description
 */
class SuperButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {

    /**
     * 粉刷图纸
     */
    private val valueStore: SuperButtonDefaultStore by lazy {
        val store = SuperButtonDefaultStore()
        AttributeSetHelper.loadFromAttributeSet(context, attrs, store)
        SuperButtonAttributeSetHelper.loadFromAttributeSet(context, attrs, store)
        return@lazy store
    }
    /**
     * 粉刷匠
     */
    val plasterer: SuperButtonPlasterer by lazy {
        return@lazy SuperButtonPlasterer(this, valueStore)
    }

    init {
        plasterer.startPaint()

    }

}