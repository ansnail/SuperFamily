package top.androidman.internal

import android.graphics.Color

object Constant {
    /**
     * 按下默认前景色
     */
    const val DEFAULT_PRESSED_FOREGROUND_COLOR = 0x1A000000
    /**
     * 默认不可点击前景色
     */
    const val DEFAULT_UNABLE_FOREGROUND_COLOR = 0xB3FFFFFF.toInt()
    /**
     * 默认文字大小
     */
    const val DEFAULT_TEXT_SIZE = 54
    /**
     * 默认文字颜色
     */
    const val DEFAULT_TEXT_COLOR = Color.GRAY
    /**
     * 默认文字提示颜色
     */
    const val DEFAULT_HINT_TEXT_COLOR = 0xFF333333.toInt()
    /**
     * value空值
     */
    const val VALUE_NULL = Int.MAX_VALUE
    /**
     * value空值
     */
    const val VALUE_NULL_FLOAT = -1.0f
    /**
     * value默认值(Int)
     */
    const val VALUE_DEFAULT_INT = Int.MAX_VALUE
    /**
     * value默认值(Float)
     */
    const val VALUE_DEFAULT_FLOAT = .0f
    /**
     * value默认值
     */
    const val VALUE_DEFAULT = VALUE_DEFAULT_INT

}