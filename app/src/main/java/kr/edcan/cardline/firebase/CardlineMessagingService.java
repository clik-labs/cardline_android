package kr.edcan.cardline.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Junseok Oh on 2017-07-17.
 */

public class CardlineMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("asdf", remoteMessage.getData().get("Title"));
    }
}
