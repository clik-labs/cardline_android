package kr.edcan.cardline.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public interface NetworkAPI {

    @POST("/api/request/login")
    Call<ResponseBody> exampleAPI();
}
