package kr.edcan.cardline.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;

import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityMainBinding;
import kr.edcan.cardline.databinding.FragmentCardlinestudioBinding;
import kr.edcan.cardline.databinding.FragmentMyeditorBinding;
import kr.edcan.cardline.databinding.FragmentNewsfeedBinding;
import kr.edcan.cardline.databinding.FragmentSettingsBinding;
import kr.edcan.cardline.fragment.CardlineStudioFragment;
import kr.edcan.cardline.fragment.MyEditorFragment;
import kr.edcan.cardline.fragment.NewsFeedFragment;
import kr.edcan.cardline.fragment.SettingsFragment;
import kr.edcan.cardline.views.AliveFragmentView;

public class MainActivity extends BaseActivity {

    /* Activity Base Objects */
    ActivityMainBinding binding;
    AliveFragmentView aliveFragmentView;
    ArrayList<ViewDataBinding> fragmentBinding;

    NewsFeedFragment newsFeedFragment;
    CardlineStudioFragment cardlineStudioFragment;
    MyEditorFragment myEditorFragment;
    SettingsFragment settingsFragment;

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setDefault() {
        binding = (ActivityMainBinding) baseBinding;
        disableToggle();
        initializeLayout();
        initializeBottomBar();
    }

    private void initializeLayout() {
        aliveFragmentView = binding.mainFragmentContainer;
        fragmentBinding = aliveFragmentView.addPage(R.layout.fragment_newsfeed,
                R.layout.fragment_cardlinestudio,
                R.layout.fragment_myeditor,
                R.layout.fragment_settings);
        newsFeedFragment = new NewsFeedFragment(this, (FragmentNewsfeedBinding) fragmentBinding.get(0));
        cardlineStudioFragment = new CardlineStudioFragment(this,(FragmentCardlinestudioBinding) fragmentBinding.get(1));
        myEditorFragment = new MyEditorFragment(this, (FragmentMyeditorBinding) fragmentBinding.get(2));
        settingsFragment = new SettingsFragment(this, (FragmentSettingsBinding) fragmentBinding.get(3));
    }

    void initializeBottomBar() {
        binding.mainBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.main_newsfeed:
                        aliveFragmentView.switchPage(0);
                        break;
                    case R.id.main_studio:
                        aliveFragmentView.switchPage(1);
                        break;
                    case R.id.main_myeditorpage:
                        aliveFragmentView.switchPage(2);
                        break;
                    case R.id.main_settings:
                        aliveFragmentView.switchPage(3);
                        break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
