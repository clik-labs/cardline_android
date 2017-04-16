package kr.edcan.cardline.views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Junseok Oh on 2017-04-16.
 */

public class AliveFragmentView extends RelativeLayout {

    private Context context;
    private ArrayList<ViewDataBinding> viewList;
    private int currentPage = 0;

    public AliveFragmentView(Context context) {
        super(context);
        this.context = context;
        this.viewList = new ArrayList<>();
    }

    public AliveFragmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.viewList = new ArrayList<>();
    }

    public ViewDataBinding addPage(@LayoutRes int layoutId) {
        ViewDataBinding view = DataBindingUtil.inflate(LayoutInflater.from(getContext()), layoutId, this, true);
        viewList.add(view);
        return view;
    }

    public ArrayList<ViewDataBinding> addPage(@LayoutRes int... layoutId) {
        for (int i = 0; i < layoutId.length; i++) {
            ViewDataBinding view = DataBindingUtil.inflate(LayoutInflater.from(getContext()), layoutId[i], this, true);
            viewList.add(view);
        }
        return viewList;
    }


    public void switchPage(int pageIndex) {
        if (pageIndex > viewList.size())
            throw new IllegalStateException("Cannot switch to page: index overflow");
        removeAllViews();
        addView(viewList.get(pageIndex).getRoot());
        currentPage = pageIndex;
    }

}
