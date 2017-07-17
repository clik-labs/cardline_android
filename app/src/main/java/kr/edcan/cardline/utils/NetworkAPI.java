package kr.edcan.cardline.utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.edcan.cardline.models.CardNews;
import kr.edcan.cardline.models.Notification;
import kr.edcan.cardline.models.User;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public interface NetworkAPI {

    @GET("/auth/facebook/token")
    Call<User> loginByFacebook(@Query("access_token") String token);

    @POST("/auth/local/register")
    @FormUrlEncoded
    Call<ResponseBody> registerLocal(
            @Field("email") String email,
            @Field("name") String name,
            @Field("password") String password);

    @POST("/auth/local/authenticate")
    @FormUrlEncoded
    Call<User> authenticateByToken(
            @Field("token") String token);

    @POST("/auth/local/login")
    @FormUrlEncoded
    Call<User> loginByLocal(
            @Field("email") String email,
            @Field("password") String password);

    @POST("/feed/recommend")
    @FormUrlEncoded
    Call<ArrayList<CardNews>> getRecommendedList(
            @Field("token") String token);

    @POST("/feed/type")
    @FormUrlEncoded
    Call<ArrayList<CardNews>> getListByType(
            @Field("type") int type);

    @POST("/feed/search")
    @FormUrlEncoded
    Call<JSONObject> searchByField(
            @Field("Field") String Field);

    @POST("/self/info")
    @FormUrlEncoded
    Call<User> getSelfInfo(
            @Field("token") String token);

    @POST("/self/info/card")
    @FormUrlEncoded
    Call<ArrayList<CardNews>> getSelfCardList(
            @Field("token") String token);

    @POST("/self/info/update")
    @FormUrlEncoded
    Call<User> updateSelfInfo(
            @Field("token") String token,
            @Field("name") String name,
            @Field("profile") String profile
    );

    @POST("/self/info/update/photo")
    @Multipart
    Call<RequestBody> updateSelfInfoPhoto(
            @Part("photo\"; filename=\"image.jpg\"") RequestBody file,
            @Part("token") RequestBody token);

    @POST("/self/info/update/like")
    @FormUrlEncoded
    Call<User> updateSelfLikeArray(
            @Field("token") String token,
            @Field("likes") List<Integer> likeList
    );

    @POST("/self/notification")
    @FormUrlEncoded
    Call<ArrayList<Notification>> getNotificationList(
            @Field("token") String token
    );

    @POST("/self/favorite")
    @FormUrlEncoded
    Call<ArrayList<CardNews>> getFavoriteCardList(
            @Field("token") String token
    );

    @POST("/self/history")
    @FormUrlEncoded
    Call<ArrayList<CardNews>> getCardViewHistory(
            @Field("token") String token
    );

    @POST("/card/info")
    @FormUrlEncoded
    Call<CardNews> getCardInfo(
            @Field("card_token") String cardToken,
            @Field("token") String token
    );

    @POST("/card/info/comment")
    @FormUrlEncoded
    Call<ArrayList<Object>> getCardComment(
            @Field("card_token") String cardToken
    );

    @POST("/card/like")
    @FormUrlEncoded
    Call<ResponseBody> likeCard(
            @Field("card_token") String cardToken,
            @Field("token") String token
    );

    @POST("/card/dislike")
    @FormUrlEncoded
    Call<ResponseBody> disLikeCard(
            @Field("card_token") String cardToken,
            @Field("token") String token
    );

    @POST("/card/post")
    @FormUrlEncoded
    Call<CardNews> postCardPost(
            // TODO Must Fill Field
    );

    @POST("/card/post/uploadImage")
    @Multipart
    Call<ResponseBody> uploadPostImage(
            // TODO Must Fill Field
    );


    @POST("/card/post/edit")
    @FormUrlEncoded
    Call<ResponseBody> editPost(
            // TODO Must Fill Field
    );


    @POST("/user/info")
    @FormUrlEncoded
    Call<User> getUserInfo(
            @Field("email") String email
    );

    @POST("/firebase/update")
    @FormUrlEncoded
    Call<ResponseBody> updateFirebaseToken(
            @Field("token") String token,
            @Field("firebase_token") String firebase_token
    );

}
