package kr.edcan.cardline.handler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import kr.edcan.cardline.activity.AccountActivity;
import kr.edcan.cardline.activity.EDYActivity;
import kr.edcan.cardline.activity.HistoryActivity;
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

    /*
    * Setting Content Click Event
    * */

    public void onSettingsListClick(int position){
        switch (position){
            case 0:
                context.startActivity(new Intent(context, EDYActivity.class));
                break;
            case 1:
                // TODO 튜토리얼 완성 후 startActivity()
                break;
            case 2:
                context.startActivity(new Intent(context, HistoryActivity.class));
                break;
        }
    }

    /*
    * EDPass ID Content Click Event
    * */

    public void onEDPassIdClick(){
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    /*
    * Account Content Click Event
    * */

    public void onAccountListClick(int position){
        switch (position){
            case 0:
                // Facebook Login Connect, Disconnect
                break;
            case 1:
                // Synchorize
                break;
            case 2:
                // Editor Pro
                break;
        }
    }
}
