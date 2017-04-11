package kr.edcan.cardline.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.FragmentCardlinestudioBinding;
import kr.edcan.cardline.databinding.FragmentSettingsBinding;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;
    static SettingsFragment instance;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, true);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();
        if(instance == null) instance = new SettingsFragment();
        instance.setArguments(args);
        return instance;
    }
}
