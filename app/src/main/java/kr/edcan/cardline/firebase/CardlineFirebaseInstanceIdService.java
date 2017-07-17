package kr.edcan.cardline.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import kr.edcan.cardline.utils.CredentialsManager;
import kr.edcan.cardline.utils.NetworkHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Junseok Oh on 2017-07-17.
 */

public class CardlineFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        final String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        if (CredentialsManager.getInstance().getActiveUser().first) {
            NetworkHelper.getNetworkInstance().updateFirebaseToken(
                    CredentialsManager.getInstance().getActiveUser().second.getToken(),
                    refreshedToken
            ).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    switch (response.code()) {
                        case 200:
                            Log.e("asdf", "firebase token updated");
                            break;
                        case 403:
                            Log.e("asdf", "Update Error");
                            break;
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("asdf", t.getLocalizedMessage());
                }
            });
        }
    }
}
