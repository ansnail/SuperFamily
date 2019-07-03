package top.androidman.superbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;


/**
 * @author yanjie
 * @version 1.0
 * @date 2019-07-01 20:08
 * @description 超级按钮
 */
public class SuperButton extends LinearLayout {
    /**
     * id 默认值
     */
    private static final int ID_NULL = -1;
    /**
     * 文字内容
     */
    private CharSequence text = "";
    /**
     * 文字资源id
     */
    private int mTextId;

    private int colorStart;
    private int colorEnd;
    private int colorPressStart;
    private int colorPressEnd;
    private int colorS;
    private int colorE;
    private int textColor;
    private float textSize;
    private float round;
    private boolean clickEffect;
    private RectF mBackGroundRect;
    private LinearGradient backGradient;

    private Paint mPaintBackground = new Paint();
    private Paint mPaintText = new Paint();

    public SuperButton(Context context) {
        this(context, null);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttrs(context, attrs);
        setClickable(true);
        //背景画笔
        mPaintBackground.setAntiAlias(true);
        mPaintBackground.setDither(true);
        mPaintBackground.setStyle(Paint.Style.FILL);
        //文字画笔
        mPaintText.setAntiAlias(true);
        mPaintText.setDither(true);

        ColorStateList backgroundColor = ColorStateList.valueOf(Color.BLUE);
        RoundRectDrawable background = new RoundRectDrawable(backgroundColor, 20);
        setBackground(background);
    }

    /**
     * 解析属性
     */
    private void parseAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperButton);
        int length = typedArray.getIndexCount();
        for (int i = 0; i < length; i++) {
            int attr = typedArray.getIndex(i);
            //文字内容
            if (attr == R.styleable.SuperButton_text){
                mTextId = typedArray.getResourceId(attr, ID_NULL);
                text = typedArray.getText(attr);
            }
            //文字颜色
            if (attr == R.styleable.SuperButton_text_color){
                mTextId = typedArray.getResourceId(attr, ID_NULL);
                text = typedArray.getText(attr);
            }

        }


        typedArray.recycle();
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
        setMeasuredDimension(width, height);
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
        LinearGradient backGradient = new LinearGradient(0, 0, 200, 200, new int[]{Color.RED, Color.GREEN}, null, Shader.TileMode.CLAMP);
        mPaintBackground.setShader(backGradient);
        //绘制背景 圆角矩形
        if (mBackGroundRect != null) {
            canvas.drawRoundRect(mBackGroundRect, round, round, mPaintBackground);
        }
        //绘制文字
        mPaintText.setTextSize(22);
        mPaintText.setColor(Color.WHITE);
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
