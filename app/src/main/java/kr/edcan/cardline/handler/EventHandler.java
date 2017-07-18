package kr.edcan.cardline.handler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.login.LoginManager;

import kr.edcan.cardline.R;
import kr.edcan.cardline.activity.AccountActivity;
import kr.edcan.cardline.activity.AuthActivity;
import kr.edcan.cardline.activity.EDYActivity;
import kr.edcan.cardline.activity.HistoryActivity;
import kr.edcan.cardline.models.CardNews;
import kr.edcan.cardline.utils.CredentialsManager;

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
     */

    /*
    * CardNews Content Click Event
    * */
    public void onCardNewsClick(CardNews item) {
        Toast.makeText(context, "asdf", Toast.LENGTH_SHORT).show();
        Log.e("Asdf", "asdf");
    }

    /*
    * Setting Content Click Event
    * */

    public void onSettingsListClick(int position) {
        switch (position) {
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

    public void onEDPassIdClick() {
        context.startActivity(new Intent(context, AccountActivity.class));
    }

    /*
    * Account Content Click Event
    * */

    public void onAccountListClick(int position) {
        switch (position) {
            case 0:
                new MaterialDialog.Builder(context)
                        .title("로그아웃")
                        .content("계정에서 로그아웃합니다. 계속하시겠습니까?")
                        .positiveText("확인")
                        .negativeText("취소")
                        .positiveColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                CredentialsManager.getInstance().removeAllData();
                                LoginManager.getInstance().logOut();
                                context.startActivity(new Intent(context, AuthActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }
                        })
                        .show();
                break;
            // Facebook Login Connect, Disconnect
            case 1:
                // Synchorize
                break;
            case 2:
                // Editor Pro
                break;
        }
    }
}
