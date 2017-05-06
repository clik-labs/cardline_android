package kr.edcan.cardline.views;

import android.graphics.Color;
import android.support.annotation.ColorRes;

/**
 * Created by fluor on 2017-05-07.
 */

public class CartaTagConfiguration {
    private boolean textColorEnabled = false, isFullMode = false, isGradient = false;
    private int themeColor = Color.BLACK, gradientStartColor = Color.BLACK, gradientEndColor = Color.WHITE, textColor = Color.BLACK;
    private int paddingLeft = 0, paddingRight = 0, paddingTop = 0, paddingBottom = 0;

    public CartaTagConfiguration() {
    }

    public CartaTagConfiguration setTextColorEnabled(boolean textColorEnabled) {
        this.textColorEnabled = textColorEnabled;
        return this;
    }

    public CartaTagConfiguration setFullMode(boolean isFullMode) {
        this.isFullMode = isFullMode;
        return this;
    }

    public CartaTagConfiguration setGradientMode(boolean isGradient) {
        this.isGradient = isGradient;
        return this;
    }

    public CartaTagConfiguration setThemeColor(@ColorRes int themeColor) {
        this.themeColor = themeColor;
        return this;
    }

    public CartaTagConfiguration setGradientStartColor(@ColorRes int startColor) {
        this.gradientStartColor = startColor;
        return this;
    }

    public CartaTagConfiguration setGradientEndColor(@ColorRes int endColor) {
        this.gradientEndColor = endColor;
        return this;
    }

    public CartaTagConfiguration setGradientColor(@ColorRes int startColor, @ColorRes int endColor) {
        this.gradientStartColor = startColor;
        this.gradientEndColor = endColor;
        return this;
    }

    public CartaTagConfiguration setTextColor(@ColorRes int textColor) {
        this.textColor = textColor;
        return this;
    }

    public CartaTagConfiguration setPadding(int left, int top, int right, int bottom) {
        this.paddingLeft = left;
        this.paddingTop = top;
        this.paddingRight = right;
        this.paddingBottom = bottom;
        return this;
    }


    public boolean isTextColorEnabled() {
        return textColorEnabled;
    }

    public boolean isFullMode() {
        return isFullMode;
    }

    public boolean isGradient() {
        return isGradient;
    }

    public int getThemeColor() {
        return themeColor;
    }

    public int getGradientStartColor() {
        return gradientStartColor;
    }

    public int getGradientEndColor() {
        return gradientEndColor;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public int getPaddingBottom() {
        return paddingBottom;
    }
}
