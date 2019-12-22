package top.androidman.internal

import androidx.annotation.IntDef


/**
 * @author         yanjie
 * @date           2019-12-16 00:03
 * @version        1.0
 */
/////////////////////////颜色方向相关////////////////////
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
//////////////////////////////////////////////////////////

/////////////////////////图片在文字的方向////////////////////
/**
 * 在文字上面
 */
const val TOP = 0x1
/**
 * 在文字下面
 */
const val BOTTOM = 0x2
/**
 * 在文字右边
 */
const val RIGHT = 0x3
/**
 * 在文字下面
 */
const val LEFT = 0x4

@IntDef(TOP, BOTTOM, RIGHT, LEFT)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class IconOrientation
///////////////////////////////////////////////////////

/////////////////////////形状相关///////////////////////
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
///////////////////////////////////////////////////////