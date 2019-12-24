package top.androidman

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import top.androidman.internal.superview.AttributeSetHelper
import top.androidman.internal.superview.DefaultStore
import top.androidman.internal.superview.Plasterer


/**
 * @author         yanjie
 * @date           2019-12-15 16:58
 * @version        1.0
 */
class SuperConstraintLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        ConstraintLayout(context, attrs, defStyleAttr) {
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