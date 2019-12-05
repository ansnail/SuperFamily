package top.androidman

import androidx.annotation.IntDef


/**
 * @author         yanjie
 * @date           2019-12-05 23:35
 * @version        1.0
 */
/**
 * 从上到下
 */
const val TOP_BOTTOM = 0x1
/**
 * 从右上到左下
 */
const val TR_BL = 0x2
/**
 * 从右到左
 */
const val RIGHT_LEFT = 0x3
/**
 * 从右下到左上
 */
const val BR_TL = 0x4
/**
 * 从下到上
 */
const val BOTTOM_TOP = 0x5
/**
 * 从左下到右上
 */
const val BL_TR = 0x6
/**
 * 从左到右
 */
const val LEFT_RIGHT = 0x7
/**
 * 从左上到右下
 */
const val TL_BR = 0x8

@IntDef(TOP_BOTTOM, TR_BL, RIGHT_LEFT, BR_TL, BOTTOM_TOP, BL_TR, LEFT_RIGHT, TL_BR)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class ColorOrientation