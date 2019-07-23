package top.androidman;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;

/**
 * @author yanjie
 * @version 1.0
 * @date 2019-07-23 19:49
 * @description 用于适配不同版本
 */
public class RoundRectHelperUtil {

    public static void help() {
        @SuppressWarnings("WeakerAccess") /* synthetic access */
        final RectF mCornerRect = new RectF();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            RoundRectDrawableWithShadow.sRoundRectHelper =
                    new RoundRectDrawableWithShadow.RoundRectHelper() {
                        @Override
                        public void drawRoundRect(Canvas canvas, RectF bounds, float cornerRadius, Paint paint) {
                            canvas.drawRoundRect(bounds, cornerRadius, cornerRadius, paint);
                        }
                    };
        } else {
            RoundRectDrawableWithShadow.sRoundRectHelper =
                    new RoundRectDrawableWithShadow.RoundRectHelper() {
                        @Override
                        public void drawRoundRect(Canvas canvas, RectF bounds, float cornerRadius, Paint paint) {
                            final float twoRadius = cornerRadius * 2;
                            final float innerWidth = bounds.width() - twoRadius - 1;
                            final float innerHeight = bounds.height() - twoRadius - 1;
                            if (cornerRadius >= 1f) {
                                // increment corner radius to account for half pixels.
                                float roundedCornerRadius = cornerRadius + .5f;
                                mCornerRect.set(-roundedCornerRadius, -roundedCornerRadius, roundedCornerRadius, roundedCornerRadius);
                                int saved = canvas.save();
                                canvas.translate(bounds.left + roundedCornerRadius, bounds.top + roundedCornerRadius);
                                canvas.drawArc(mCornerRect, 180, 90, true, paint);
                                canvas.translate(innerWidth, 0);
                                canvas.rotate(90);
                                canvas.drawArc(mCornerRect, 180, 90, true, paint);
                                canvas.translate(innerHeight, 0);
                                canvas.rotate(90);
                                canvas.drawArc(mCornerRect, 180, 90, true, paint);
                                canvas.translate(innerWidth, 0);
                                canvas.rotate(90);
                                canvas.drawArc(mCornerRect, 180, 90, true, paint);
                                canvas.restoreToCount(saved);
                                //draw top and bottom pieces
                                canvas.drawRect(bounds.left + roundedCornerRadius - 1f, bounds.top,
                                        bounds.right - roundedCornerRadius + 1f,
                                        bounds.top + roundedCornerRadius, paint);

                                canvas.drawRect(bounds.left + roundedCornerRadius - 1f,
                                        bounds.bottom - roundedCornerRadius,
                                        bounds.right - roundedCornerRadius + 1f, bounds.bottom, paint);
                            }
                            // center
                            canvas.drawRect(bounds.left, bounds.top + cornerRadius,
                                    bounds.right, bounds.bottom - cornerRadius, paint);
                        }
                    };
        }
    }

}
