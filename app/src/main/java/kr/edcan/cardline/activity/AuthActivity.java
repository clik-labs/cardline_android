package kr.edcan.cardline.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.IOException;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityAuthBinding;
import kr.edcan.cardline.models.User;
import kr.edcan.cardline.utils.NetworkHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends BaseActivity {

    LoginButton loginButton;
    CallbackManager callbackManager;
    ActivityAuthBinding binding;

    @Override
    protected void setDefault() {
        binding = (ActivityAuthBinding) baseBinding;
        loginButton = binding.loginButton;
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("asdf", loginResult.getAccessToken().getToken());
                binding.token.setText("Token : " + loginResult.getAccessToken().getToken());
                NetworkHelper.getNetworkInstance().loginByFacebook(loginResult.getAccessToken().getToken()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        binding.result.setText("HTTP " + response.code());
                        switch (response.code()) {
                            case 200:
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        binding.result.setText(t.getLocalizedMessage());
                    }
                });
            }

            @Override
            public void onCancel() {
                binding.token.setText("Token : " + "Canceled");

            }

            @Override
            public void onError(FacebookException error) {
                binding.token.setText("Token : " + error.getMessage());

            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_auth;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }
}
