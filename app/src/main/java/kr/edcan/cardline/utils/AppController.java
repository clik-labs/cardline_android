package kr.edcan.cardline.utils;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;

/**
 * Created by Junseok Oh on 2017-07-16.
 */

public class AppController extends Application {

    static AppController controller;
    public static AppController getInstance(){
        if(controller == null) controller = new AppController();
        return controller;
    };

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(this);
    }

    public Context getContext(){
        return this.getApplicationContext();
    }
}
