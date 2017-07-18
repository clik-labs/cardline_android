package kr.edcan.cardline.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
import kr.edcan.cardline.utils.CredentialsManager;

public class AccountActivity extends BaseActivity {

    private RecyclerView accountRecyclerView;
    private EventHandler eventHandler;
    private ArrayList<AccountContent> dataList = new ArrayList<>();
    private boolean isLogin = false, isSyncing = false, isEditorPro = true;
    private ActivityAccountBinding binding;
    private TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {
        setToolbarTitle("계정");
        binding = (ActivityAccountBinding) baseBinding;
        accountRecyclerView = binding.accountRecyclerView;
        email = binding.accountText;
        eventHandler = new EventHandler(this);
        accountRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList.add(new AccountContent(
                0,
                "로그아웃",
                CredentialsManager.getInstance().getActiveUser().second.getEmail() + " 계정으로부터 로그이웃합니다.",
                getResources().getDrawable((isLogin) ? R.drawable.ic_account_facebook_on : R.drawable.ic_account_facebook_off)));
        dataList.add(new AccountContent(
                1,
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

        email.setText(CredentialsManager.getInstance().getActiveUser().second.getEmail());
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
        if (email != null)
            email.setText(CredentialsManager.getInstance().getActiveUser().second.getEmail());

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