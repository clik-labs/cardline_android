package kr.edcan.cardline.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.FragmentCardlinestudioBinding;
import kr.edcan.cardline.databinding.FragmentMyeditorBinding;
import kr.edcan.cardline.databinding.FragmentNewsfeedBinding;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class NewsFeedFragment {
    private Context context;
    private FragmentNewsfeedBinding binding;

    public NewsFeedFragment(Context context, FragmentNewsfeedBinding binding) {
        this.context = context;
        this.binding = binding;
        setFragment();
    }

    private void setFragment() {

    }
}
