package top.androidman.superbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
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
     * draw the gradient from the top to the bottom
     */
    private static final int TOP_BOTTOM = 0x1;
    /**
     * draw the gradient from the top-right to the bottom-left
     */
    private static final int TR_BL = 0x2;
    /**
     * draw the gradient from the right to the left
     */
    private static final int RIGHT_LEFT = 0x3;
    /**
     * draw the gradient from the bottom-right to the top-left
     */
    private static final int BR_TL = 0x4;
    /**
     * draw the gradient from the bottom to the top
     */
    private static final int BOTTOM_TOP = 0x5;
    /**
     * draw the gradient from the bottom-left to the top-right
     */
    private static final int BL_TR = 0x6;
    /**
     * draw the gradient from the left to the right
     */
    private static final int LEFT_RIGHT = 0x7;
    /**
     * draw the gradient from the top-left to the bottom-right
     */
    private static final int TL_BR = 0x8;
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
    private int mColorNormal = ResourceId.VALUE_NULL;
    /**
     * 默认背景颜色
     */
    private int mColorPressed = ResourceId.VALUE_NULL;
    /**
     * 图片资源
     */
    private Drawable mDrawableLeft = null;
    private Drawable mDrawableRight = null;
    private Drawable mDrawableTop = null;
    private Drawable mDrawableBottom = null;
    private boolean mDrawableAuto = true;
    /**
     * 图片距离文字距离
     */
    private int mDrawablePadding;
    /**
     * 形状
     */
    private int mShape = RECT;
    /**
     * 当背景是渐进色时，开始颜色
     */
    private int mColorStart = ResourceId.VALUE_NULL;
    /**
     * 当背景是渐进色时，结束颜色
     */
    private int mColorEnd = ResourceId.VALUE_NULL;
    /**
     * 颜色方向
     */
    private int mColorDirection = LEFT_RIGHT;
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
    /**
     * 按钮背景
     */
    private GradientDrawable mButtonBackground;

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
        mButtonBackground = new GradientDrawable();
        //设置渐变色
        if (mColorStart != ResourceId.VALUE_NULL && mColorEnd != ResourceId.VALUE_NULL) {
            if (mColorDirection == TOP_BOTTOM) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
            } else if (mColorDirection == TR_BL) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.TR_BL);
            } else if (mColorDirection == RIGHT_LEFT) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
            } else if (mColorDirection == BR_TL) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.BR_TL);
            } else if (mColorDirection == BOTTOM_TOP) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
            } else if (mColorDirection == BL_TR) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.BL_TR);
            } else if (mColorDirection == LEFT_RIGHT) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            } else if (mColorDirection == TL_BR) {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.TL_BR);
            } else {
                mButtonBackground.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
            }
            mButtonBackground.setColors(new int[]{mColorStart, mColorEnd});
        } else {
            //设置填充颜色
            setButtonBackgroundColor(mColorNormal);
        }
        if (mShape == CIRCLE) {
            mButtonBackground.setShape(GradientDrawable.OVAL);
        } else {
            mButtonBackground.setShape(GradientDrawable.RECTANGLE);
        }
        //ordered top-left, top-right, bottom-right, bottom-left
        mButtonBackground.setCornerRadii(new float[]{
                mCornerTopLeft != ResourceId.VALUE_NULL ? mCornerTopLeft : mCorner, mCornerTopLeft != ResourceId.VALUE_NULL ? mCornerTopLeft : mCorner,
                mCornerTopRight != ResourceId.VALUE_NULL ? mCornerTopRight : mCorner, mCornerTopRight != ResourceId.VALUE_NULL ? mCornerTopRight : mCorner,
                mCornerBottomRight != ResourceId.VALUE_NULL ? mCornerBottomRight : mCorner, mCornerBottomRight != ResourceId.VALUE_NULL ? mCornerBottomRight : mCorner,
                mCornerBottomLeft != ResourceId.VALUE_NULL ? mCornerBottomLeft : mCorner, mCornerBottomLeft != ResourceId.VALUE_NULL ? mCornerBottomLeft : mCorner});

        //设置边框颜色和边框宽度
        mButtonBackground.setStroke(mBorderWidth, mBorderColor);
        //最终设置
        setBackground(mButtonBackground);

        //设置文字
        mTextIconContainer.setText(text);
        //设置文字大小
        mTextIconContainer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        //设置文字颜色
        mTextIconContainer.setTextColor(mTextColor);
        mTextIconContainer.setCompoundDrawablePadding(mDrawablePadding);
        mTextIconContainer.setSingleLine();
        mTextIconContainer.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(mTextIconContainer, layoutParams);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                setButtonBackgroundColor(mColorPressed == ResourceId.VALUE_NULL ? mColorNormal : mColorPressed);
                break;
            case MotionEvent.ACTION_UP:
                setButtonBackgroundColor(mColorNormal);
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 设置正常状态下颜色
     */
    public void setButtonBackgroundColor(@ColorInt int color) {
        mButtonBackground.setColor(ColorStateList.valueOf(color));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mDrawableAuto) {
            mDrawableLeft.setBounds(0, 0, mTextIconContainer.getHeight(), mTextIconContainer.getHeight());
            //设置文字drawable
            mTextIconContainer.setCompoundDrawables(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom);
        } else {
            mTextIconContainer.setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom);
        }
        Log.e("xxx", "width = " + mTextIconContainer.getWidth() + ", height = " + mTextIconContainer.getHeight());
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
                mColorNormal = typedArray.getColor(attr, ResourceId.VALUE_NULL);
            }
            //按压状态颜色
            if (attr == R.styleable.SuperButton_color_pressed) {
                mColorPressed = typedArray.getColor(attr, ResourceId.VALUE_NULL);
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
            if (attr == R.styleable.SuperButton_drawable_auto) {
                mDrawableAuto = typedArray.getBoolean(attr, true);
            }
            //图片距离文字距离
            if (attr == R.styleable.SuperButton_drawable_padding) {
                mDrawablePadding = typedArray.getDimensionPixelSize(attr, ResourceId.VALUE_DEFAULT);
            }
            //形状
            if (attr == R.styleable.SuperButton_shape) {
                mShape = typedArray.getInt(attr, RECT);
            }
            //开始颜色
            if (attr == R.styleable.SuperButton_color_start) {
                mColorStart = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //结束颜色
            if (attr == R.styleable.SuperButton_color_end) {
                mColorEnd = typedArray.getColor(attr, Color.TRANSPARENT);
            }
            //颜色方向
            if (attr == R.styleable.SuperButton_color_direction) {
                mColorDirection = typedArray.getInt(attr, LEFT_RIGHT);
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

}
