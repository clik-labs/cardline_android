package kr.edcan.cardline.models;

import java.util.ArrayList;

/**
 * Created by fluor on 2017-05-10.
 */

public class User {
    private int userType = 0;
    private String email, name, token, profile, profile_img, facebook_id;

    public User() {
    }

    public User(String email, String name, String token, String profile, String profile_img, String facebook_id) {
        this.email = email;
        this.name = name;
        this.token = token;
        this.profile = profile;
        this.profile_img = profile_img;
        this.facebook_id = facebook_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(String facebook_id) {
        this.facebook_id = facebook_id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
