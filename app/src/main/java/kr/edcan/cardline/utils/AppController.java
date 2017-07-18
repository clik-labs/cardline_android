package kr.edcan.cardline.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;

/**
 * Created by Junseok Oh on 2017-07-16.
 */

public class AppController extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        FacebookSdk.sdkInitialize(this);
    }

    public static Context getContext() {
        return mContext;
    }

}
