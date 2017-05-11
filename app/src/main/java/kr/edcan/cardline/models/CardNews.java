package kr.edcan.cardline.models;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Junseok Oh on 2017-05-08.
 */

public class CardNews {
    private String title, content, imageUrl;
    private Context context;

    public CardNews(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
