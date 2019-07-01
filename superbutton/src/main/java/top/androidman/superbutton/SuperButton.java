package top.androidman.superbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * @author yanjie
 * @version 1.0
 * @date 2019-07-01 20:08
 * @description 超级按钮
 */
public class SuperButton extends View {
    public SuperButton(Context context) {
        this(context, null);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
            height = heightSize;
        } else {

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
