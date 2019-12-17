package top.androidman

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import top.androidman.internal.AttributeSetHelper
import top.androidman.internal.DefaultStore
import top.androidman.internal.Plasterer


/**
 * @author         yanjie
 * @date           2019-12-17 17:47
 * @version        1.0
 * @description
 */
class SuperButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        AppCompatTextView(context, attrs, defStyleAttr) {

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