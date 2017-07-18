package kr.edcan.cardline.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityMainBinding;
import kr.edcan.cardline.fragment.CardlineStudioFragment;
import kr.edcan.cardline.fragment.MyEditorFragment;
import kr.edcan.cardline.fragment.NewsFeedFragment;
import kr.edcan.cardline.fragment.SettingsFragment;
import kr.edcan.cardline.views.ControllableViewPager;

public class MainActivity extends BaseActivity {

    /* Activity Base Objects */
    ActivityMainBinding binding;
    ControllableViewPager mainPager;
    ArrayList<ViewDataBinding> fragmentBinding;
    CardLinePagerAdapter pagerAdapter;

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
        setToolbarTitle(getResources().getString(R.string.newsfeed));
        pagerAdapter = new CardLinePagerAdapter(getSupportFragmentManager());
        mainPager = binding.mainFragmentContainer;
        mainPager.setAdapter(pagerAdapter);
    }

    void initializeBottomBar() {

        binding.mainBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (menu != null) {
                    menu.clear();
                    switch (tabId) {
                        case R.id.main_newsfeed:
                            mainPager.setCurrentItem(0);
                            setToolbarTitle(getResources().getString(R.string.newsfeed));
                            getMenuInflater().inflate(R.menu.menu_newsfeed, menu);
                            break;
                        case R.id.main_studio:
                            mainPager.setCurrentItem(1);
                            setToolbarTitle(getResources().getString(R.string.studio));
                            break;
                        case R.id.main_myeditorpage:
                            mainPager.setCurrentItem(2);
                            setToolbarTitle(getResources().getString(R.string.my_editor_page));
                            getMenuInflater().inflate(R.menu.menu_myeditorpage, menu);
                            break;
                        case R.id.main_settings:
                            mainPager.setCurrentItem(3);
                            setToolbarTitle(getResources().getString(R.string.more_settings));
                            getMenuInflater().inflate(R.menu.menu_settings, menu);
                            break;
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newsfeed, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newsfeed_alert:
                // 뉴스피드 - 알림 내역
                startActivity(new Intent(getApplicationContext(), NotificationActivity.class));
                break;
            case R.id.newsfeed_favlist:
                // 뉴스피드 - 즐겨찾기 목록
                startActivity(new Intent(getApplicationContext(), FavoriteCardlineActivity.class));
                break;
            case R.id.myeditorpage_settings:
                // 내 에디터 페이지 - 설정
                break;
            case R.id.settings_info:
                // 더보기 - 정보
                startActivity(new Intent(getApplicationContext(), InformationActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class CardLinePagerAdapter extends FragmentStatePagerAdapter {

        public CardLinePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return NewsFeedFragment.create(position);
                case 1:
                    return CardlineStudioFragment.create(position);
                case 2:
                    return MyEditorFragment.create(position);
                case 3:
                    return SettingsFragment.create(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

    }
}
