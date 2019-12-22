package top.androidman.internal

import android.graphics.drawable.GradientDrawable


/**
 * @author         yanjie
 * @date           2019-12-16 00:59
 * @version        1.0
 */

/**
 * 根据方向获取真实的值
 */
fun getColorOrientation(@ColorOrientation orientation: Int): GradientDrawable.Orientation {
    when (orientation) {
        TOP_BOTTOM -> {
            return GradientDrawable.Orientation.TOP_BOTTOM
        }
        TR_BL -> {
            return GradientDrawable.Orientation.TR_BL
        }
        RIGHT_LEFT -> {
            return GradientDrawable.Orientation.RIGHT_LEFT
        }
        BR_TL -> {
            return GradientDrawable.Orientation.BR_TL
        }
        BOTTOM_TOP -> {
            return GradientDrawable.Orientation.BOTTOM_TOP
        }
        BL_TR -> {
            return GradientDrawable.Orientation.BL_TR
        }
        LEFT_RIGHT -> {
            return GradientDrawable.Orientation.LEFT_RIGHT
        }
        TL_BR -> {
            return GradientDrawable.Orientation.TL_BR
        }
        else -> {
            return GradientDrawable.Orientation.LEFT_RIGHT
        }
    }
}