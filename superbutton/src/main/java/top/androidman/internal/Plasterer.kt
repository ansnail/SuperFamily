package top.androidman.internal

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import top.androidman.Constant
import top.androidman.RoundRectDrawableWithShadow


/**
 * @author         yanjie
 * @date           2019-12-15 15:22
 * @version        1.0
 * 泥水匠会根据雇主的要求产生美丽的画卷，然后返回给雇主，雇主用这个画卷装饰自己的view即可
 * 泥水匠工作三部曲
 * 1.准备@{top.androidman.internal.Platerer.prepare}
 * 2.设置属性，
 */
class Plasterer(view: View, valueStore: DefaultStore) {
    /**
     * 粉刷图纸
     */
    private var globalStore: DefaultStore = valueStore
    /**
     * 粉刷对象
     */
    private var paintObject: View = view

    /**
     * 设置正常状态下颜色
     */
    fun normalColor(@ColorInt normalColor: Int): Plasterer {
        globalStore.normalColor = normalColor
        return this
    }

    fun invalidate() {
        //是否有阴影效果
        val hasShadow = globalStore.shadowSize != Constant.VALUE_NULL &&
                globalStore.shadowStartColor != Constant.VALUE_NULL &&
                globalStore.shadowEndColor != Constant.VALUE_NULL

        val backGroundDrawable = if (hasShadow) {
            RoundRectDrawableWithShadow(
                    ColorStateList.valueOf(globalStore.normalColor), globalStore.corner,
                    globalStore.shadowStartColor, globalStore.shadowEndColor,
                    globalStore.shadowSize.toFloat(), globalStore.shadowSize.toFloat())
        } else {
            generateGradientDrawable()
        }
        paintObject.background = backGroundDrawable
    }

    /**
     * 生成背景
     */
    private fun generateGradientDrawable(): GradientDrawable {
        val beautifulCanvas = GradientDrawable()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            beautifulCanvas.color = ColorStateList.valueOf(globalStore.normalColor)
        } else {
            beautifulCanvas.setColor(globalStore.normalColor)
        }
        beautifulCanvas.cornerRadii = floatArrayOf(
                globalStore.corner, globalStore.corner,
                globalStore.corner, globalStore.corner,
                globalStore.corner, globalStore.corner,
                globalStore.corner, globalStore.corner)
        return beautifulCanvas
    }

}