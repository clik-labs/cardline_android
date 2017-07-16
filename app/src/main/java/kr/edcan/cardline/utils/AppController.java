package kr.edcan.cardline.utils;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Junseok Oh on 2017-07-16.
 */

public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
    }
}
