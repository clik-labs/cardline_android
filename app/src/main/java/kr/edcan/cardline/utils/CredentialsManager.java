package kr.edcan.cardline.utils;

/**
 * Created by Junseok Oh on 2017-07-18.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.gson.Gson;

import kr.edcan.cardline.models.User;

public class CredentialsManager {

    /* Login Type
    * 0 Facebook
    * 1: Native
    * */

    /* Data Keys */
    private static String USER_SCHEMA = "user_schema";
    private static String HAS_ACTIVE_USER = "hasactive";
    private static String LOGIN_TYPE = "login_type";
    private static String FACEBOOK_TOKEN = "facebook_token";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    static CredentialsManager manager;

    public static CredentialsManager getInstance() {
        if (manager == null) manager = new CredentialsManager();
        return manager;
    }

    private CredentialsManager() {
        context = AppController.getContext();
        preferences = context.getSharedPreferences("Cardline", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void save(String key, String data) {
        editor.putString(key, data);
        editor.apply();
    }

    public void saveFacebookCredential(String facebookToken) {
        editor.putString(FACEBOOK_TOKEN, facebookToken);
        editor.apply();
    }

    public void saveUserInfo(User user, int loginType) {
        editor.putInt(LOGIN_TYPE, loginType);
        editor.putString(USER_SCHEMA, new Gson().toJson(user));
        editor.putBoolean(HAS_ACTIVE_USER, true);
        editor.apply();
    }

    public Pair<Boolean, User> getActiveUser() {
        if (preferences.getBoolean(HAS_ACTIVE_USER, false)) {
            int userType = preferences.getInt(LOGIN_TYPE, -1);
            User user = new Gson().fromJson(preferences.getString(USER_SCHEMA, ""), User.class);
            user.setUserType(userType);
            return Pair.create(true, user);
        } else return Pair.create(false, null);
    }

    public String getFacebookUserCredential() {
        if (preferences.getBoolean(HAS_ACTIVE_USER, false) && preferences.getInt(LOGIN_TYPE, -1) == 0) {
            return preferences.getString(FACEBOOK_TOKEN, "");
        } else return "";
    }

    public void removeAllData() {
        editor.clear();
        editor.apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public boolean isFirst() {
        return preferences.getBoolean("IS_FIRST", true);
    }

    public void notFirst() {
        editor.putBoolean("IS_FIRST", false);
        editor.apply();
    }

    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

}