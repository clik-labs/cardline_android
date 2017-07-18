package kr.edcan.cardline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.IOException;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityAuthBinding;
import kr.edcan.cardline.fragment.LoginFragment;
import kr.edcan.cardline.fragment.RegisterFragment;
import kr.edcan.cardline.models.User;
import kr.edcan.cardline.utils.NetworkHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends BaseActivity {

    ActivityAuthBinding binding;
    ViewPager bottomPager;
    BottomPagerAdapter adapter;

    @Override
    protected void setDefault() {
        binding = (ActivityAuthBinding) baseBinding;

        bottomPager = binding.viewPager;
        adapter = new BottomPagerAdapter(getSupportFragmentManager());
        bottomPager.setAdapter(adapter);
        binding.authTabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = (Fragment) adapter.instantiateItem(bottomPager, 1);
        fragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }

    class BottomPagerAdapter extends FragmentStatePagerAdapter {

        public BottomPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return (position == 0) ? new RegisterFragment() : new LoginFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return (position == 0) ? "회원가입" : "로그인";
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
}
}
