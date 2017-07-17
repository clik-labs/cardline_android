package kr.edcan.cardline.utils;

import java.util.ArrayList;

import kr.edcan.cardline.models.CardNews;
import kr.edcan.cardline.models.User;
import okhttp3.Response;
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
    Call<User> loginByFacebook(@Query("access_token") String token);

    @POST("/auth/local/register")
    Call<ResponseBody> registerLocal(
            @Query("email") String email,
            @Query("name") String name,
            @Query("password") String password);

    @POST("/auth/local/authenticate")
    Call<User> authenticateByToken(
            @Query("token") String token);

    @POST("/auth/local/login")
    Call<User> loginByLocal(
            @Query("email") String email,
            @Query("password") String password);

    @POST("/feed/recommend")
    Call<ArrayList<CardNews>> getRecommendedList(
            @Query("email") String email,
            @Query("password") String password);




}
