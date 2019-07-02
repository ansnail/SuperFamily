package top.androidman.superbutton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;


/**
 * @author yanjie
 * @version 1.0
 * @date 2019-07-01 20:08
 * @description 超级按钮
 */
public class SuperButton extends LinearLayout {

    private int colorStart;
    private int colorEnd;
    private int colorPressStart;
    private int colorPressEnd;
    private int colorS;
    private int colorE;
    private String text;
    private int textColor;
    private float textSize;
    private float round;
    private boolean clickEffect;
    private RectF mBackGroundRect;
    private LinearGradient backGradient;

    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint();

    public SuperButton(Context context) {
        this(context, null);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //必须加，否则onTouchEvent只响应ACTION_DOWN
        setClickable(true);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
        //设置防抖动
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);

        mPaintText.setAntiAlias(true);
        mPaintText.setDither(true);
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
            width = widthSize;
            height = heightSize;
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBackGroundRect = new RectF(0, 0, w, h);
        backGradient = new LinearGradient(0, 0, w, 0, new int[]{colorS, colorE}, null, Shader.TileMode.CLAMP);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setShader(backGradient);
        //绘制背景 圆角矩形
        if (mBackGroundRect != null) {
            canvas.drawRoundRect(mBackGroundRect, round, round, mPaint);
        }
        //绘制文字
        mPaintText.setTextSize(textSize);
        mPaintText.setColor(textColor);
        mPaintText.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = mPaintText.getFontMetricsInt();
        float baseline = mBackGroundRect.top + (mBackGroundRect.bottom - mBackGroundRect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText("床前明月光", canvas.getWidth() / 2, baseline, mPaintText);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (clickEffect) {
            //刷新
            invalidate();
            //判断点击操作
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    colorS = colorPressStart;
                    colorE = colorPressEnd;
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    colorS = colorStart;
                    colorE = colorEnd;
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                default:
                    break;
            }
        }

        return super.onTouchEvent(event);
    }


    public void setText(String text) {
        if (!TextUtils.isEmpty(text)) {
            this.text = text;
            invalidate();
        }
    }
}
