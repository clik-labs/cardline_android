package kr.edcan.cardline.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import kr.edcan.cardline.R;

/**
 * Created by Conota on 2017-07-19.
 */

public class ShowCardView extends RelativeLayout {
    private static final String TAG = "ShowCardViewCf";
    private Context context;
    private int current_page = 1;
    private int limit_page = 4;
    private ImageView vPrev;
    private ImageView vNext;
    private ImageView vBack;

    public ShowCardView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ShowCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ShowCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void init() {
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        RelativeLayout view = (RelativeLayout) li.inflate(R.layout.item_show_card, this, true);
        vPrev = view.findViewById(R.id.show_btn_prev);
        vPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCheck(false);
            }
        });
        vNext = view.findViewById(R.id.show_btn_next);
        vNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCheck(true);
            }
        });
        if (current_page != limit_page) {
            vNext.setVisibility(VISIBLE);
        }
        vBack = new ImageView(context);
        addView(vBack, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void btnCheck(boolean b) {
        if (b) {
            current_page = current_page + 1;
        } else {
            current_page = current_page - 1;
        }
        if (current_page == limit_page) {
            vNext.setVisibility(GONE);
        } else {
            vNext.setVisibility(VISIBLE);
        }
        if (current_page == 1) {
            vPrev.setVisibility(GONE);
        } else {
            vPrev.setVisibility(VISIBLE);
        }
    }

    public void setLimitPage(int x) {
        this.limit_page = x;
    }


}
