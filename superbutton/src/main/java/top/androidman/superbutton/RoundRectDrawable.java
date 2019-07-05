package top.androidman.superbutton;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class RoundRectDrawable extends Drawable {
    private static final double COS_45 = Math.cos(Math.toRadians(45.0D));
    private float mRadius;
    private final Paint mPaint;
    private final RectF mBoundsF;
    private final Rect mBoundsI;
    private float mPadding;
    private boolean mInsetForPadding = false;
    private boolean mInsetForRadius = true;
    private ColorStateList mBackground;
    private PorterDuffColorFilter mTintFilter;
    private ColorStateList mTint;
    private Mode mTintMode;

    RoundRectDrawable(ColorStateList backgroundColor, float radius) {
        this.mTintMode = Mode.SRC_IN;
        this.mRadius = radius;
        this.mPaint = new Paint();
        this.setBackground(backgroundColor);
        this.mBoundsF = new RectF();
        this.mBoundsI = new Rect();
    }

    private void setBackground(ColorStateList color) {
        this.mBackground = color == null ? ColorStateList.valueOf(0) : color;
        this.mPaint.setColor(this.mBackground.getColorForState(this.getState(), this.mBackground.getDefaultColor()));
    }

    void setPadding(float padding, boolean insetForPadding, boolean insetForRadius) {
        if (padding != this.mPadding || this.mInsetForPadding != insetForPadding || this.mInsetForRadius != insetForRadius) {
            this.mPadding = padding;
            this.mInsetForPadding = insetForPadding;
            this.mInsetForRadius = insetForRadius;
            this.updateBounds((Rect)null);
            this.invalidateSelf();
        }
    }

    float getPadding() {
        return this.mPadding;
    }

    @Override
    public void draw(Canvas canvas) {
//        LinearGradient backGradient = new LinearGradient(0, 0, 200, 200, new int[]{Color.RED, Color.GREEN}, null, Shader.TileMode.CLAMP);
        Paint paint = this.mPaint;
        paint.setStyle(Paint.Style.FILL);
//        paint.setShader(backGradient);
        boolean clearColorFilter;
        if (this.mTintFilter != null && paint.getColorFilter() == null) {
            paint.setColorFilter(this.mTintFilter);
            clearColorFilter = true;
        } else {
            clearColorFilter = false;
        }

        canvas.drawRoundRect(this.mBoundsF, this.mRadius, this.mRadius, paint);
        if (clearColorFilter) {
            paint.setColorFilter((ColorFilter)null);
        }

    }

    private void updateBounds(Rect bounds) {
        if (bounds == null) {
            bounds = this.getBounds();
        }

        this.mBoundsF.set((float)bounds.left, (float)bounds.top, (float)bounds.right, (float)bounds.bottom);
        this.mBoundsI.set(bounds);
        if (this.mInsetForPadding) {
            float vInset = calculateVerticalPadding(this.mPadding, this.mRadius, this.mInsetForRadius);
            float hInset = calculateHorizontalPadding(this.mPadding, this.mRadius, this.mInsetForRadius);
            this.mBoundsI.inset((int)Math.ceil((double)hInset), (int)Math.ceil((double)vInset));
            this.mBoundsF.set(this.mBoundsI);
        }

    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        this.updateBounds(bounds);
    }

    @Override
    public void getOutline(Outline outline) {
        outline.setRoundRect(this.mBoundsI, this.mRadius);
    }

    void setRadius(float radius) {
        if (radius != this.mRadius) {
            this.mRadius = radius;
            this.updateBounds((Rect)null);
            this.invalidateSelf();
        }
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

    public float getRadius() {
        return this.mRadius;
    }

    public void setColor(@Nullable ColorStateList color) {
        this.setBackground(color);
        this.invalidateSelf();
    }

    public ColorStateList getColor() {
        return this.mBackground;
    }

    @Override
    public void setTintList(ColorStateList tint) {
        this.mTint = tint;
        this.mTintFilter = this.createTintFilter(this.mTint, this.mTintMode);
        this.invalidateSelf();
    }

    @Override
    public void setTintMode(Mode tintMode) {
        this.mTintMode = tintMode;
        this.mTintFilter = this.createTintFilter(this.mTint, this.mTintMode);
        this.invalidateSelf();
    }

    @Override
    protected boolean onStateChange(int[] stateSet) {
        int newColor = this.mBackground.getColorForState(stateSet, this.mBackground.getDefaultColor());
        boolean colorChanged = newColor != this.mPaint.getColor();
        if (colorChanged) {
            this.mPaint.setColor(newColor);
        }

        if (this.mTint != null && this.mTintMode != null) {
            this.mTintFilter = this.createTintFilter(this.mTint, this.mTintMode);
            return true;
        } else {
            return colorChanged;
        }
    }

    @Override
    public boolean isStateful() {
        return this.mTint != null && this.mTint.isStateful() || this.mBackground != null && this.mBackground.isStateful() || super.isStateful();
    }

    private PorterDuffColorFilter createTintFilter(ColorStateList tint, Mode tintMode) {
        if (tint != null && tintMode != null) {
            int color = tint.getColorForState(this.getState(), 0);
            return new PorterDuffColorFilter(color, tintMode);
        } else {
            return null;
        }
    }

    static float calculateVerticalPadding(float maxShadowSize, float cornerRadius, boolean addPaddingForCorners) {
        return addPaddingForCorners ? (float)((double)(maxShadowSize * 1.5F) + (1.0D - COS_45) * (double)cornerRadius) : maxShadowSize * 1.5F;
    }

    static float calculateHorizontalPadding(float maxShadowSize, float cornerRadius, boolean addPaddingForCorners) {
        return addPaddingForCorners ? (float)((double)maxShadowSize + (1.0D - COS_45) * (double)cornerRadius) : maxShadowSize;
    }
}
