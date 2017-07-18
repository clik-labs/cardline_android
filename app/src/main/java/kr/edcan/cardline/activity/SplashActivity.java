package kr.edcan.cardline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import kr.edcan.cardline.R;
import kr.edcan.cardline.models.User;
import kr.edcan.cardline.utils.CredentialsManager;
import kr.edcan.cardline.utils.NetworkHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    CredentialsManager manager = CredentialsManager.getInstance();
    @Override
    protected void setDefault() {
        if(manager.getActiveUser().first){
            NetworkHelper.getNetworkInstance().authenticateByToken(manager.getActiveUser().second.getToken()).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    switch (response.code()){
                        case 200:
                            break;
                        default:
                            Toast.makeText(SplashActivity.this, "세션이 만료되었습니다. 다시 로그인해주세요.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }
}
