package kr.edcan.cardline.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import kr.edcan.cardline.R;
import kr.edcan.cardline.activity.MainActivity;
import kr.edcan.cardline.databinding.FragmentLoginBinding;
import kr.edcan.cardline.models.User;
import kr.edcan.cardline.utils.CredentialsManager;
import kr.edcan.cardline.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-07-18.
 */

public class LoginFragment extends Fragment {
    LoginButton loginButton;
    CallbackManager callbackManager;
    FragmentLoginBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        setDefault();
        return binding.getRoot();
    }

    private void setDefault() {
        loginButton = binding.facebookOriginButton;
        binding.continueWithFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginButton.performClick();
            }
        });

        String permissions[] = new String[]{"email", "user_about_me"};
        loginButton.setReadPermissions(permissions);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult loginResult) {
                NetworkHelper.getNetworkInstance().loginByFacebook(loginResult.getAccessToken().getToken()).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        switch (response.code()) {
                            case 200:
                                CredentialsManager.getInstance().saveFacebookCredential(loginResult.getAccessToken().getToken());
                                CredentialsManager.getInstance().saveUserInfo(response.body(), 0);
                                Log.e("asdf", response.body().getProfile_img());
                                Toast.makeText(getContext(), response.body().getName() + " 님 환영합니다!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getContext(), MainActivity.class));
                                getActivity().finish();
                                break;
                            default:
                                Toast.makeText(getContext(), "Login Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e("asdf", error.getLocalizedMessage());

            }
        });
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.emailInput.getText().toString().trim().equals("") || binding.passwordInput.getText().toString().trim().equals(""))
                    Toast.makeText(getContext(), "빈칸 없이 입력해주세요", Toast.LENGTH_SHORT).show();
                else {
                    NetworkHelper.getNetworkInstance().loginByLocal(
                            binding.emailInput.getText().toString().trim(),
                            binding.passwordInput.getText().toString().trim()
                    ).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            switch (response.code()) {
                                case 200:
                                    CredentialsManager.getInstance().saveUserInfo(response.body(), 1);
                                    Toast.makeText(getContext(), response.body().getName() + " 님 환영합니다!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getContext(), MainActivity.class));
                                    getActivity().finish();
                                    break;
                                case 401:
                                    Toast.makeText(getContext(), "아이디 혹은 비밀번호가 잘못되었습니다!", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("asdf", t.getLocalizedMessage());
                        }
                    });
                }
            }
        });
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
