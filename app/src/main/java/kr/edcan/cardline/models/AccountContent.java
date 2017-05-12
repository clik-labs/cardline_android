package kr.edcan.cardline.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Junseok Oh on 2017-05-12.
 */

public class AccountContent {
    private int position;
    private String title, content;
    private Drawable icon;

    public AccountContent(int position, String title, String content, Drawable icon) {
        this.position = position;
        this.title = title;
        this.content = content;
        this.icon = icon;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}

