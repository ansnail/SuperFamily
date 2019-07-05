package top.androidman.superbutton;

import android.graphics.Paint;

/**
 * @author yanjie
 * @version 1.0
 * @date 2019-07-05 11:27
 * @description 画笔工厂
 */
public class PaintFactory {

    /**
     * Paint的flag标志
     * ------------------------------------------
     * Paint.ANTI_ALIAS_FLAG ：抗锯齿标志
     * Paint.FILTER_BITMAP_FLAG : 使位图过滤的位掩码标志
     * Paint.DITHER_FLAG : 使位图进行有利的抖动的位掩码标志
     * Paint.UNDERLINE_TEXT_FLAG : 下划线
     * Paint.STRIKE_THRU_TEXT_FLAG : 中划线
     * Paint.FAKE_BOLD_TEXT_FLAG : 加粗
     * Paint.LINEAR_TEXT_FLAG : 使文本平滑线性扩展的油漆标志
     * Paint.SUBPIXEL_TEXT_FLAG : 使文本的亚像素定位的绘图标志
     * Paint.EMBEDDED_BITMAP_TEXT_FLAG : 绘制文本时允许使用位图字体的绘图标志
     * ------------------------------------------
     * Paint.Style属性
     * Paint.Style.FILL设置只绘制图形内容
     * Paint.Style.STROKE设置只绘制图形的边
     * Paint.Style.FILL_AND_STROKE设置都绘制
     * ------------------------------------------
     */
    public static Paint creatPaint(){
        Paint paint = new Paint();
        //防锯齿
        paint.setAntiAlias(true);
        //防抖动
        paint.setDither(true);
        return paint;
    }

}
