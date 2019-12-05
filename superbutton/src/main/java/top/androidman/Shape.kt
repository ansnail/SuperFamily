package top.androidman

import androidx.annotation.IntDef


/**
 * @author         yanjie
 * @date           2019-12-05 23:22
 * @version        1.0
 */

/**
 * 圆形
 */
const val CIRCLE = 0x1
/**
 * 矩形
 */
const val RECT = 0x2

@IntDef(CIRCLE, RECT)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class Shape