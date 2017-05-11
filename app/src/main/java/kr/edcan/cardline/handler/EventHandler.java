package kr.edcan.cardline.handler;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import kr.edcan.cardline.models.CardNews;

/**
 * Created by Junseok Oh on 2017-05-11.
 */

public class EventHandler {
    private Context context;

    public EventHandler(Context context) {
        this.context = context;
    }
    /**
     * Click Events
     * */

    /*
    * CardNews Content Click Event
    * */
    public void onCardNewsClick(CardNews item){
        Toast.makeText(context, "asdf", Toast.LENGTH_SHORT).show();
        Log.e("Asdf", "asdf");
    }
}
