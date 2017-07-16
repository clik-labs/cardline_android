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

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class CardlineStudioFragment extends Fragment{
    private int mPageNumber;
    private String title;
    private FragmentCardlinestudioBinding binding;


    public static CardlineStudioFragment create(int pageNumber) {
        CardlineStudioFragment fragment = new CardlineStudioFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt("page");
        title = getArguments().getString("exchange");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cardlinestudio, container, false);
        return binding.getRoot();
    }
}
