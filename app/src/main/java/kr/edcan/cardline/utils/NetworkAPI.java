package kr.edcan.cardline.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public interface NetworkAPI {

    @GET("/auth/facebook/token")
    Call<ResponseBody> loginByFacebook(@Query("access_token") String token);
}
