package kr.edcan.cardline.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kr.edcan.cardline.BR;
import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.FragmentCardlinestudioBinding;
import kr.edcan.cardline.databinding.FragmentSettingsBinding;
import kr.edcan.cardline.databinding.SettingsContentBinding;
import kr.edcan.cardline.handler.EventHandler;
import kr.edcan.cardline.models.ListContent;
import kr.edcan.cardline.models.User;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class SettingsFragment {
    private Context context;
    private FragmentSettingsBinding binding;
    private ArrayList<Object> listData = new ArrayList<>();
    private EventHandler eventHandler;
    private RecyclerView settingsRecyclerView;
    public SettingsFragment(Context context, FragmentSettingsBinding binding) {
        this.context = context;
        this.binding = binding;
        initialize();
        setFragment();
    }

    private void initialize() {
        eventHandler = new EventHandler(context);
        settingsRecyclerView = binding.settingsRecyclerView;
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        listData.add(new User());
        listData.add(new ListContent(0, context.getResources().getDrawable(R.drawable.title_more_edy), "무엇이든 물어보세요, EDY가 카드라인 사용을 도와드립니다."));
        listData.add(new ListContent(1, "주제 재선정", "오늘의 추천에 표시되는 뉴스들을 주제를 설정합니다."));
        listData.add(new ListContent(2, "탐색 기록", "이제까지 보았던 카드들을 볼 수 있습니다."));
    }

    private void setFragment() {
        LastAdapter.with(listData, BR.commonContent)
                .map(User.class, new ItemType<>(R.layout.settings_account_content))
                .map(ListContent.class, new ItemType<SettingsContentBinding>(R.layout.settings_content) {
                    @Override
                    public void onBind(@NotNull ViewHolder<SettingsContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().setEventHandler(eventHandler);
                    }
                })
                .into(binding.settingsRecyclerView);
    }
}
