package kr.edcan.cardline.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.AccountContentBinding;
import kr.edcan.cardline.databinding.ActivityAccountBinding;
import kr.edcan.cardline.handler.EventHandler;
import kr.edcan.cardline.models.AccountContent;

public class AccountActivity extends BaseActivity {

    private RecyclerView accountRecyclerView;
    private EventHandler eventHandler;
    private ArrayList<AccountContent> dataList = new ArrayList<>();
    private boolean isLogin = false, isSyncing = false, isEditorPro = false;
    private ActivityAccountBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {
        setToolbarTitle("계정");
        binding = (ActivityAccountBinding) baseBinding;
        accountRecyclerView = binding.accountRecyclerView;
        eventHandler = new EventHandler(this);
        accountRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList.add(new AccountContent(
                0,
                "Facebook 계정 연결하기",
                "계정이 연결되어 있으면 앞으로 Facebook 인증으로 로그인 할 수 있습니다.",
                getResources().getDrawable((isLogin) ? R.drawable.ic_account_facebook_on : R.drawable.ic_account_facebook_off)));
        dataList.add(new AccountContent(
                1,
                "동기화 켜기",
                "동기화 하여 모든 기기에서 작업을 이어해 보세요.",
                getResources().getDrawable((isSyncing) ? R.drawable.ic_account_sync_on : R.drawable.ic_account_sync_off)));
        dataList.add(new AccountContent(
                2,
                "Editor Pro 신청",
                "기업, 공인을 위한 인증 마크, 전용 서비스를 제공받을 수 있습니다.",
                getResources().getDrawable((isEditorPro) ? R.drawable.ic_account_editorpro_on : R.drawable.ic_account_editorpro_off)));
        new LastAdapter(dataList, BR.content)
                .map(AccountContent.class, new ItemType<AccountContentBinding>(R.layout.account_content) {
                    @Override
                    public void onBind(@NotNull Holder<AccountContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().setEventHandler(eventHandler);
                    }
                })
                .into(accountRecyclerView);

        binding.accountText.setText("Here Goes Your Email");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_account, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
        } // 로그아웃
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /* TODO Sync Account Data */
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_account;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}