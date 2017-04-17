package kr.edcan.cardline.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import kr.edcan.cardline.R;

/**
 * Created by JunseokOh on 2016. 8. 6..
 */
public class CartaTagView extends TextView {
    boolean fullMode = false;
    boolean textColorEnabled = false;
    boolean shadowEnabled = false;
    int color = Color.BLACK;
    int textColor = Color.WHITE;
    int shadowColor = Color.BLUE;
    int height, width;
    private Point center;
    private RectF bgRect, innerRect;
    private Paint innerPaint, bgPaint;

    public CartaTagView(Context context) {
        super(context);
    }

    public CartaTagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
        center = new Point();
        bgPaint = new Paint();
        innerPaint = new Paint();
        bgRect = new RectF();
        innerRect = new RectF();
        setGravity(Gravity.CENTER);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CartaTagView);
        setTypedArray(array);
    }

    private void setTypedArray(TypedArray array) {
        fullMode = array.getBoolean(R.styleable.CartaTagView_fullMode, false);
        color = array.getColor(R.styleable.CartaTagView_themeColor, Color.BLACK);
        textColor = array.getColor(R.styleable.CartaTagView_textThemeColor, Color.BLACK);
        textColorEnabled = array.getBoolean(R.styleable.CartaTagView_textThemeColorEnabled, false);
        shadowColor = array.getColor(R.styleable.CartaTagView_shadowColor, Color.BLUE);
        shadowEnabled = array.getBoolean(R.styleable.CartaTagView_shadowEnabled, false);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
    }

    public void setView() {
        if (!textColorEnabled) setTextColor((fullMode) ? Color.WHITE : color);
        else setTextColor(textColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setView();
        center.set(width / 2, height / 2);
        int strokeWidth = getResources().getDimensionPixelSize(R.dimen.stroke_width);
        int shadowWidth = (shadowEnabled) ? getResources().getDimensionPixelSize(R.dimen.shadow_width) : 0;
        int innerH = height - strokeWidth - shadowWidth;
        int innerW = width - strokeWidth - shadowWidth;
        int left = center.x - (innerW / 2);
        int top = center.y - (innerH / 2);
        int right = center.x + (innerW / 2);
        int bottom = center.y + (innerH / 2);
        bgPaint.setColor(color);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeWidth(strokeWidth);
        if (shadowEnabled)
            bgPaint.setShadowLayer(shadowWidth / (float) 2, 0.0f, 10.0f, Color.parseColor("#CC000BFF"));
        innerPaint.setAntiAlias(true);
        innerPaint.setColor((fullMode) ? color : Color.WHITE);
        innerPaint.setStyle(Paint.Style.FILL);
        setLayerType(LAYER_TYPE_SOFTWARE, innerPaint);
        setLayerType(LAYER_TYPE_SOFTWARE, bgPaint);
        bgRect.set(0.0f + strokeWidth + shadowWidth, 0.0f + strokeWidth + shadowWidth, width - strokeWidth - shadowWidth, height - strokeWidth - shadowWidth);
        innerRect.set(left, top, right, bottom);
        canvas.drawRoundRect(bgRect, height / 2, height / 2, bgPaint);
        canvas.drawRoundRect(bgRect, innerH / 2, innerH / 2, innerPaint);
        super.onDraw(canvas);
    }

    public void setShapeStyle(boolean fullMode, int color) {
        this.color = color;
        this.fullMode = fullMode;
        setView();
        requestLayout();
    }


    public void setFullMode(boolean fullMode) {
        this.fullMode = fullMode;
        setView();
        requestLayout();
    }

    public void setShapeStyle(boolean fullMode, String colorStr) {
        this.color = Color.parseColor(colorStr);
        setView();
        requestLayout();
    }

    public void setTextColorForceFully(int color) {
        this.textColorEnabled = true;
        this.textColor = color;
        setView();
        requestLayout();
    }

    public void setShadowEnabled(boolean shadowEnabled) {
        this.shadowEnabled = shadowEnabled;
        setView();
        requestLayout();
    }

    public void setShadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        setView();
        requestLayout();
    }

    public boolean getFullMode() {
        return this.fullMode;
    }
}