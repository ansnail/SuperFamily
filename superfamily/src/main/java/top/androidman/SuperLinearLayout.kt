package top.androidman

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import top.androidman.internal.AttributeSetHelper
import top.androidman.internal.DefaultStore
import top.androidman.internal.Plasterer


/**
 * @author         yanjie
 * @date           2019-12-16 00:45
 * @version        1.0
 */
class SuperLinearLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr) {
    /**
     * 粉刷图纸
     */
    private val valueStore: DefaultStore by lazy {
        return@lazy AttributeSetHelper.loadFromAttributeSet(context, attrs)
    }
    /**
     * 粉刷匠
     */
    val plasterer: Plasterer by lazy {
        return@lazy Plasterer(this, valueStore)
    }

    init {
        plasterer.startPaint()
    }
}