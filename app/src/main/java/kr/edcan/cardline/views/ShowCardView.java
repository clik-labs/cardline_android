package kr.edcan.cardline.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Conota on 2017-07-19.
 */

public class ShowCardView extends LinearLayout {
    public ShowCardView(Context context) {
        super(context);
    }

    public ShowCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
