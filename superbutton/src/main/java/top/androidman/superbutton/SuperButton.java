package top.androidman.superbutton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * @author yanjie
 * @version 1.0
 * @date 2019-07-01 20:08
 * @description 超级按钮
 */
public class SuperButton extends LinearLayout {
    /**
     * 形状
     */
    private static final int CIRCLE = 0x1;
    private static final int RECT = 0x2;
    /**
     * 颜色方向
     */
    private static final int LEFT_TO_RIGHT = 0x1;
    private static final int RIGHT_TO_LEFT = 0x2;
    private static final int TOP_TO_BOTTOM = 0x3;
    private static final int BOTTOM_TO_TOP = 0x4;
    /**
     * 文字内容
     */
    private CharSequence text;
    /**
     * 文字颜色
     */
    private int mTextColor;
    /**
     * 文字大小
     */
    private int mTextSize;
    /**
     * 默认背景颜色
     */
    private Drawable mColorNormal;
    /**
     * 默认背景颜色
     */
    private int mColorPressed;
    /**
     * 图片资源
     */
    private Drawable mDrawableLeft = null;
    private Drawable mDrawableRight = null;
    private Drawable mDrawableTop = null;
    private Drawable mDrawableBottom = null;
    /**
     * 形状
     */
    private int mShape = RECT;
    /**
     * 形状
     */
    private int mCircleRadius;
    /**
     * 当背景是渐进色时，开始颜色
     */
    private int mColorStart;
    /**
     * 当背景是渐进色时，中间颜色
     */
    private int mColorMiddle;
    /**
     * 当背景是渐进色时，结束颜色
     */
    private int mColorEnd;
    /**
     * 颜色方向
     */
    private int mColorDirection;
    /**
     * 所有角圆角半径
     */
    private int mCorner;
    /**
     * 四个角角度半径
     */
    private int mCornerLeftTop;
    private int mCornerLeftBottom;
    private int mCornerRightTop;
    private int mCornerRightBottom;
    /**
     * 边框颜色
     */
    private int mBorderColor;
    /**
     * 边框宽度
     */
    private int mBorderWidth;

    /**
     * 文字和图标容器
     */
    private TextView mTextIconContainer;


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
    /**
     * 背景画笔
     */
    private Paint mBackgroundPaint = PaintFactory.creatPaint();
    private Paint mTextPaint = PaintFactory.creatPaint();

    public SuperButton(Context context) {
        this(context, null);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //自有属性
        setGravity(Gravity.CENTER);
        setClickable(true);
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(Color.GRAY);
        //解析属性
        parseAttrs(context, attrs, defStyleAttr, defStyleRes);
        //设置文字和icon相关属性
        setBackgroundColor(Color.WHITE);
        mTextIconContainer = new TextView(getContext());
        mTextIconContainer.setText(text);
        mTextIconContainer.setTextSize(mTextSize);
        mTextIconContainer.setTextColor(Color.GREEN);
//        mTextIconContainer.setBackgroundColor(Color.RED);
        removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mTextIconContainer, layoutParams);
//        ColorStateList backgroundColor = ColorStateList.valueOf(Color.BLUE);
//        RoundRectDrawable background = new RoundRectDrawable(backgroundColor, 20);
    }

    /**
     * 属性绘制排序
     */
    private void attrsSort() {
        //形状
        if (mShape == RECT) {

        }

    }

    /**
     * 属性解析
     */
    private void parseAttrs(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperButton, defStyleAttr, defStyleRes);
        int length = typedArray.getIndexCount();
        for (int i = 0; i < length; i++) {
            int attr = typedArray.getIndex(i);
            //文字内容
            if (attr == R.styleable.SuperButton_android_text) {
                text = typedArray.getText(attr);
            }
            //文字颜色
            if (attr == R.styleable.SuperButton_android_textColor) {
                mTextColor = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //文字大小
            if (attr == R.styleable.SuperButton_android_textSize) {
                mTextSize = typedArray.getDimensionPixelSize(attr, 0);
            }
            //默认背景颜色
            if (attr == R.styleable.SuperButton_color_normal) {
                mColorNormal = typedArray.getDrawable(attr);
            }
            //按压状态颜色
            if (attr == R.styleable.SuperButton_color_pressed) {
                mColorPressed = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //图片在文字左边
            if (attr == R.styleable.SuperButton_drawable_left) {
                mDrawableLeft = typedArray.getDrawable(attr);
            }
            //图片在文字右边
            if (attr == R.styleable.SuperButton_drawable_right) {
                mDrawableRight = typedArray.getDrawable(attr);
            }
            //图片在文字上边
            if (attr == R.styleable.SuperButton_drawable_top) {
                mDrawableTop = typedArray.getDrawable(attr);
            }
            //图片在文字下边
            if (attr == R.styleable.SuperButton_drawable_bottom) {
                mDrawableBottom = typedArray.getDrawable(attr);
            }
            //形状
            if (attr == R.styleable.SuperButton_drawable_bottom) {
                mShape = typedArray.getInt(attr, RECT);
            }
            //圆形半径
            if (attr == R.styleable.SuperButton_circle_radius) {
                mCircleRadius = typedArray.getDimensionPixelSize(attr, 0);
            }
            //开始颜色
            if (attr == R.styleable.SuperButton_color_start) {
                mColorStart = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //中间颜色
            if (attr == R.styleable.SuperButton_color_middle) {
                mColorMiddle = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //结束颜色
            if (attr == R.styleable.SuperButton_color_end) {
                mColorEnd = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //形状
            if (attr == R.styleable.SuperButton_color_direction) {
                mColorDirection = typedArray.getInt(attr, LEFT_TO_RIGHT);
            }
            //所有角圆角半径
            if (attr == R.styleable.SuperButton_corner) {
                mCorner = typedArray.getDimensionPixelSize(attr, 0);
            }
            //左上角圆角半径
            if (attr == R.styleable.SuperButton_corner_left_top) {
                mCornerLeftTop = typedArray.getDimensionPixelSize(attr, 0);
            }
            //右上角圆角半径
            if (attr == R.styleable.SuperButton_corner_right_top) {
                mCornerRightTop = typedArray.getDimensionPixelSize(attr, 0);
            }
            //左下角圆角半径
            if (attr == R.styleable.SuperButton_corner_left_bottom) {
                mCornerLeftBottom = typedArray.getDimensionPixelSize(attr, 0);
            }
            //右下角圆角半径
            if (attr == R.styleable.SuperButton_corner_right_bottom) {
                mCornerRightBottom = typedArray.getDimensionPixelSize(attr, 0);
            }
            //边框宽度
            if (attr == R.styleable.SuperButton_border_width) {
                mBorderWidth = typedArray.getDimensionPixelSize(attr, 0);
            }
            //边框颜色
            if (attr == R.styleable.SuperButton_border_color) {
                mBorderColor = typedArray.getColor(attr, Color.TRANSPARENT);
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
        backGradient = new LinearGradient(0, 0, w, 0, new int[]{Color.GREEN, Color.RED}, null, Shader.TileMode.CLAMP);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mBackgroundPaint.setShader(backGradient);
//        //绘制背景 圆角矩形
//        if (mBackGroundRect != null) {
            canvas.drawRoundRect(mBackGroundRect, 20, 20, mBackgroundPaint);
//        }
//        //绘制文字
        mTextPaint.setTextSize(22);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        float baseline = mBackGroundRect.top + (mBackGroundRect.bottom - mBackGroundRect.top - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        canvas.drawText("床前明月光", canvas.getWidth() / 2, baseline, mTextPaint);


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
