package kr.edcan.cardline.models;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * Created by Junseok Oh on 2017-05-11.
 */

public class ListContent {
    private String title, content;
    private boolean isTitleImage = false;
    private Drawable titleImage;
    private int position;
    public ListContent(int position, String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ListContent(int position, Drawable titleImage, String content) {
        isTitleImage = true;
        this.titleImage = titleImage;
        this.content = content;
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

    public boolean isTitleImage() {
        return isTitleImage;
    }

    public void setTitleImage(boolean titleImage) {
        isTitleImage = titleImage;
    }

    public Drawable getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(Drawable titleImage) {
        this.titleImage = titleImage;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
