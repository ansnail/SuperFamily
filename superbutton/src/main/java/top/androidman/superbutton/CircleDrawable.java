package top.androidman.superbutton;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

class CircleDrawable extends Drawable {
    private float mRadius;
    private final Paint mPaint;
    /**
     * 圆心坐标
     */
    private float cx;
    private float cy;

    CircleDrawable(float cx, float cy, float radius, ColorStateList backgroundColor) {
        this.cx = cx;
        this.cy = cy;
        this.mRadius = radius;
        this.mPaint = PaintFactory.creatPaint();
        this.setBackground(backgroundColor);
    }

    private void setBackground(ColorStateList color) {
        ColorStateList background = color == null ? ColorStateList.valueOf(0) : color;
        this.mPaint.setColor(background.getColorForState(this.getState(), background.getDefaultColor()));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        canvas.drawCircle(cx, cy, mRadius, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

}
