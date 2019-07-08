package top.androidman.superbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
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
    private int mColorNormal;
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
    private int mCornerTopLeft = ResourceId.VALUE_NULL;
    private int mCornerBottomLeft = ResourceId.VALUE_NULL;
    private int mCornerTopRight = ResourceId.VALUE_NULL;
    private int mCornerBottomRight = ResourceId.VALUE_NULL;
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

    public SuperButton(Context context) {
        this(context, null);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, 0);
        setClickable(true);
        setGravity(Gravity.CENTER);
        mTextIconContainer = new TextView(context);
        //解析属性
        parseAttrs(context, attrs);

        //按钮背景
        GradientDrawable buttonBackground = new GradientDrawable();
        buttonBackground.setShape(GradientDrawable.OVAL);
        //设置填充颜色
        buttonBackground.setColor(ColorStateList.valueOf(mColorNormal));
        //ordered top-left, top-right, bottom-right, bottom-left
        buttonBackground.setCornerRadii(new float[]{
                mCornerTopLeft != ResourceId.VALUE_NULL ? mCornerTopLeft : mCorner, mCornerTopLeft != ResourceId.VALUE_NULL ? mCornerTopLeft : mCorner,
                mCornerTopRight != ResourceId.VALUE_NULL ? mCornerTopRight : mCorner, mCornerTopRight != ResourceId.VALUE_NULL ? mCornerTopRight : mCorner,
                mCornerBottomRight != ResourceId.VALUE_NULL ? mCornerBottomRight : mCorner, mCornerBottomRight != ResourceId.VALUE_NULL ? mCornerBottomRight : mCorner,
                mCornerBottomLeft != ResourceId.VALUE_NULL ? mCornerBottomLeft : mCorner, mCornerBottomLeft != ResourceId.VALUE_NULL ? mCornerBottomLeft : mCorner});

        //设置边框颜色和边框宽度
        buttonBackground.setStroke(mBorderWidth, mBorderColor);
        //最终设置
        setBackground(buttonBackground);

        //设置文字
        mTextIconContainer.setText(text);
        //设置文字大小
        mTextIconContainer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        //设置文字颜色
        mTextIconContainer.setTextColor(mTextColor);
        mTextIconContainer.setSingleLine();
        mTextIconContainer.setGravity(Gravity.CENTER);
        addView(mTextIconContainer);
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
    private void parseAttrs(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SuperButton);
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
                mTextSize = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
            }
            //默认背景颜色
            if (attr == R.styleable.SuperButton_color_normal) {
                mColorNormal = typedArray.getColor(attr, Color.TRANSPARENT);
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
                mCircleRadius = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
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
                mCorner = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
            }
            //左上角圆角半径
            if (attr == R.styleable.SuperButton_corner_top_left) {
                mCornerTopLeft = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
            }
            //右上角圆角半径
            if (attr == R.styleable.SuperButton_corner_top_right) {
                mCornerTopRight = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
            }
            //左下角圆角半径
            if (attr == R.styleable.SuperButton_corner_bottom_left) {
                mCornerBottomLeft = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
            }
            //右下角圆角半径
            if (attr == R.styleable.SuperButton_corner_bottom_right) {
                mCornerBottomRight = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_NULL);
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

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//        int width;
//        int height;
//
//        if (widthMode == MeasureSpec.EXACTLY) {
//            width = widthSize;
//            height = heightSize;
//        } else {
//            width = widthSize;
//            height = heightSize;
//        }
//        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
//    }

}
