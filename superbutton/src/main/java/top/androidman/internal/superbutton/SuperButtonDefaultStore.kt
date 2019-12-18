package top.androidman.internal.superbutton

import android.graphics.Color
import android.graphics.drawable.Drawable
import top.androidman.internal.Constant
import top.androidman.internal.DefaultStore
import top.androidman.internal.LEFT


/**
 * @author         yanjie
 * @date           2019-12-17 23:54
 * @version        1.0
 */
class SuperButtonDefaultStore : DefaultStore() {
    /**
     * 文字内容
     */
    var text: CharSequence = ""
    /**
     * 文字颜色
     */
    var textColor = Color.GRAY
    /**
     * 文字大小
     */
    var textSize = 18

    /**
     * 提示文字内容
     */
    var hintText: CharSequence = ""
    /**
     * 提示文字内容颜色
     */
    var hintTextColor = Color.TRANSPARENT

    /**
     * 文字是否单行
     */
    var singleLine = true

    /**
     * 图片资源
     */
    var icon: Drawable? = null
    /**
     * icon在文字的方向
     */
    var iconAtTextOrientation = LEFT
    /**
     * icon距离文字距离
     */
    var iconPadding = 0
    /**
     * icon宽度
     */
    var iconWidth = Constant.VALUE_NULL
    /**
     * icon高度
     */
    var iconHeight = Constant.VALUE_NULL

}