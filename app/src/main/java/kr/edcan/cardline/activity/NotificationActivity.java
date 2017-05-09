package kr.edcan.cardline.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.Type;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityNotificationBinding;
import kr.edcan.cardline.databinding.NotificationContentBinding;
import kr.edcan.cardline.models.Notification;

public class NotificationActivity extends BaseActivity {

    private ActivityNotificationBinding binding;
    private RecyclerView notificationRecyclerView;
    private ArrayList<Notification> notifications = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {
        setToolbarTitle("알림");
        binding = (ActivityNotificationBinding) baseBinding;
        notificationRecyclerView = binding.notificationRecyclerview;
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notifications.add(new Notification("aasdf", "aasdf", "aasdf"));
        notifications.add(new Notification("aasdf", "aasdf", "aasdf"));
        LastAdapter.with(notifications, BR._all)
                .map(Notification.class, new Type<NotificationContentBinding>(R.layout.notification_content)
                        .onClick(new Function1<Type.Params<? extends NotificationContentBinding>, Unit>() {
                            @Override
                            public Unit invoke(Type.Params<? extends NotificationContentBinding> params) {
                                /*
                                * Set Title, Content, Image, Alert
                                * */
                                return null;
                            }
                        })
                        .onBind(new Function1<Type.Params<? extends NotificationContentBinding>, Unit>() {
                            @Override
                            public Unit invoke(Type.Params<? extends NotificationContentBinding> params) {
                                NotificationContentBinding binding = params.getBinding();
                                /*
                                * Set Title, Content, Image, Alert
                                * */
                                return null;
                            }
                        }))
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        return R.layout.notification_content;
                    }
                })
                .into(notificationRecyclerView);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_notification;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
