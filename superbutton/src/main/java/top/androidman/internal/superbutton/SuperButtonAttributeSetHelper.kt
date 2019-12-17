package top.androidman.internal.superbutton

import android.content.Context
import android.util.AttributeSet
import top.androidman.R


/**
 * @author         yanjie
 * @date           2019-12-15 14:47
 * @version        1.0
 */
object SuperButtonAttributeSetHelper {

    /**
     * 解析独有属性
     */
    fun loadFromAttributeSet(context: Context, attrs: AttributeSet?, defaultStore: SuperButtonDefaultStore = SuperButtonDefaultStore()): SuperButtonDefaultStore {
        if (attrs == null) {
            return defaultStore
        }

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperButton)
        val length = typedArray.indexCount
        for (i in 0 until length) {
            val attr = typedArray.getIndex(i)
            //默认背景颜色
            if (attr == R.styleable.SuperButton_text) {
                defaultStore.text = typedArray.getText(attr)
            }
        }
        typedArray.recycle()
        return defaultStore
    }


}