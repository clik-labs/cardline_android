package kr.edcan.cardline.views;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;

import kr.edcan.cardline.R;

/**
 * Created by JunseokOh on 2016. 8. 6..
 */
public class CartaTagView extends AppCompatTextView {

    final public static int COLOR_OVERLAY = 0;
    final public static int GRADIENT_OVERLAY = 1;
    boolean shadowEnabled = false;
    boolean fullMode = false;
    boolean textColorEnabled = false;
    boolean gradientEnabled = false;
    int color = Color.BLACK, startColor = Color.BLACK, endColor = Color.WHITE, touchOverlayColor = Color.TRANSPARENT;
    int textColor = Color.WHITE;
    int height, width;
    private Point center;
    private LinearGradient gradientOverlay;
    private RectF bgRect, innerRect;
    private Paint innerPaint, bgPaint, touchOverlayPaint;

    public CartaTagView(Context context) {
        super(context);
    }

    public CartaTagView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getAttrs(attrs);
        center = new Point();
        bgPaint = new Paint();
        innerPaint = new Paint();
        touchOverlayPaint = new Paint();
        bgRect = new RectF();
        innerRect = new RectF();
        setGravity(Gravity.CENTER);
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CartaTagView);
        setTypedArray(array);
    }

    private void setTypedArray(TypedArray array) {

        shadowEnabled = array.getBoolean(R.styleable.CartaTagView_shadowEnabled, false);
        fullMode = array.getBoolean(R.styleable.CartaTagView_fullMode, false);
        color = array.getColor(R.styleable.CartaTagView_themeColor, Color.BLACK);
        textColor = array.getColor(R.styleable.CartaTagView_textThemeColor, Color.BLACK);
        textColorEnabled = array.getBoolean(R.styleable.CartaTagView_textThemeColorEnabled, false);
        gradientEnabled = array.getBoolean(R.styleable.CartaTagView_enableGradient, false);
        startColor = array.getColor(R.styleable.CartaTagView_gradientStartColor, Color.BLACK);
        endColor = array.getColor(R.styleable.CartaTagView_gradientEndColor, Color.WHITE);
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

        if (gradientEnabled) {
            gradientOverlay = new LinearGradient(0, 0, getWidth(), 0,
                    startColor, endColor,
                    Shader.TileMode.CLAMP);
        }
        setLayerType(LAYER_TYPE_SOFTWARE, innerPaint);
        setLayerType(LAYER_TYPE_SOFTWARE, bgPaint);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        setView();
        center.set(width / 2, height / 2);
        int strokeWidth = getResources().getDimensionPixelSize(R.dimen.stroke_width);
        int innerH = height - strokeWidth;
        int innerW = width - strokeWidth;
        int left = center.x - (innerW / 2);
        int top = center.y - (innerH / 2);
        int right = center.x + (innerW / 2);
        int bottom = center.y + (innerH / 2);
        // Background Paint
        bgPaint.setColor(color);
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeWidth(strokeWidth);

        // Inner Paint
        innerPaint.setAntiAlias(true);
        innerPaint.setColor((fullMode) ? color : Color.WHITE);
        innerPaint.setStyle(Paint.Style.FILL);

        // Touch Overlay Paint
        touchOverlayPaint.setColor(touchOverlayColor);
        touchOverlayPaint.setStyle(Paint.Style.FILL);
        touchOverlayPaint.setAntiAlias(true);

        if (gradientEnabled) {
            if (fullMode) innerPaint.setShader(gradientOverlay);
            else bgPaint.setShader(gradientOverlay);
        }

        bgRect.set(0.0f + strokeWidth, 0.0f + strokeWidth, width - strokeWidth, height - strokeWidth);
        innerRect.set(left, top, right, bottom);
        if (!fullMode) canvas.drawRoundRect(bgRect, height / 2, height / 2, bgPaint);
        else canvas.drawRoundRect(bgRect, innerH / 2, innerH / 2, innerPaint);
        canvas.drawRoundRect(bgRect, innerH / 2, innerH / 2, touchOverlayPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                touchOverlayColor = Color.TRANSPARENT;
                requestLayout();
                return true;
            case MotionEvent.ACTION_DOWN:
                touchOverlayColor = Color.parseColor("#32FFFFFF");
                requestLayout();
                return true;
        }
        return false;
    }


    public void setShapeColor(int color) {
        this.color = color;
        setView();
        requestLayout();
    }

    public void setShapeColor(String colorStr) {
        this.color = Color.parseColor(colorStr);
        setView();
        requestLayout();
    }

    public void setShapeGradientColor(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        setView();
        requestLayout();
    }

    public void setShapeGradientColor(String startColorStr, String endColorStr) {
        this.startColor = Color.parseColor(startColorStr);
        this.endColor = Color.parseColor(endColorStr);
        setView();
        requestLayout();
    }

    public void setOverlayStyle(int overlayStyle) {
        this.gradientEnabled = (overlayStyle == GRADIENT_OVERLAY);
        setView();
        requestLayout();
    }

    public void setFullMode(boolean fullMode) {
        this.fullMode = fullMode;
        setView();
        requestLayout();
    }

    public void setTextColorForceFully(int color) {
        this.textColorEnabled = true;
        this.textColor = color;
        setView();
        requestLayout();
    }

    public boolean getFullMode() {
        return this.fullMode;
    }

    public boolean isTextColorEnabled() {
        return textColorEnabled;
    }

    public boolean isGradientEnabled() {
        return gradientEnabled;
    }

    public int getColor() {
        return color;
    }

    public int getStartColor() {
        return startColor;
    }

    public int getEndColor() {
        return endColor;
    }

    public int getTextColor() {
        return textColor;
    }
}