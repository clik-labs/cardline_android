package kr.edcan.cardline.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityMainBinding;
import kr.edcan.cardline.fragment.CardlineStudioFragment;
import kr.edcan.cardline.fragment.MyEditorFragment;
import kr.edcan.cardline.fragment.NewsFeedFragment;
import kr.edcan.cardline.fragment.SettingsFragment;

public class MainActivity extends BaseActivity {

    /* Activity Base Objects */
    Bundle savedInstanceState;
    ActivityMainBinding binding;
    FragNavController fragNavController;
    ArrayList<Fragment> fragmentList;

    /* Variables */
    private final int INDEX_NEWSFEED = FragNavController.TAB1;
    private final int INDEX_STUDIO = FragNavController.TAB2;
    private final int INDEX_MYEDITORPAGE = FragNavController.TAB3;
    private final int INDEX_SETTINGS = FragNavController.TAB4;

    NewsFeedFragment newsFeedFragment;
    CardlineStudioFragment cardlineStudioFragment;
    MyEditorFragment myEditorFragment;
    SettingsFragment settingsFragment;

    int previousTabIndex = 0;
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
        this.savedInstanceState = savedInstanceState;
    }

    protected void setDefault() {
        binding = (ActivityMainBinding) baseBinding;
        disableToggle();
        initializeFragment();
        initializeBottomBar();
    }


    void initializeFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(NewsFeedFragment.newInstance());
        fragmentList.add(CardlineStudioFragment.newInstance());
        fragmentList.add(MyEditorFragment.newInstance());
        fragmentList.add(SettingsFragment.newInstance());
        fragNavController = FragNavController.newBuilder(
                savedInstanceState,
                getSupportFragmentManager(),
                R.id.mainFragmentContainer)
                .rootFragments(fragmentList)
                .build();
    }

    void initializeBottomBar() {
        binding.mainBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.main_newsfeed:
                        fragNavController.switchTab(INDEX_NEWSFEED);
                        break;
                    case R.id.main_studio:
                        fragNavController.switchTab(INDEX_STUDIO);
                        break;
                    case R.id.main_myeditorpage:
                        fragNavController.switchTab(INDEX_MYEDITORPAGE);
                        break;

                    case R.id.main_settings:
                        fragNavController.switchTab(INDEX_SETTINGS);
                        break;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (fragNavController != null) fragNavController.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
