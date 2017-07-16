package kr.edcan.cardline.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import java.net.NetworkInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public class NetworkHelper {
    private final static String url = "http://soylatte.kr";
    private final static int port = 8989;

    private static Retrofit retrofit;

    public static NetworkAPI getNetworkInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url + ":" + port)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(NetworkAPI.class);
    }

    public static boolean returnNetworkState(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
