package kr.edcan.cardline.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.Type;
import com.github.nitrico.lastadapter.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.FragmentMyeditorBinding;
import kr.edcan.cardline.databinding.MainNewsfeedCommonContentBinding;
import kr.edcan.cardline.databinding.MyeditorHeaderBinding;
import kr.edcan.cardline.databinding.MyeditorPopularListBinding;
import kr.edcan.cardline.databinding.MyeditorPopularListContentBinding;
import kr.edcan.cardline.handler.EventHandler;
import kr.edcan.cardline.models.CardNews;
import kr.edcan.cardline.models.User;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class MyEditorFragment {
    private Context context;
    private FragmentMyeditorBinding binding;
    private RecyclerView myEditorRecyclerView;
    private GridLayoutManager layoutManager;
    private LinearLayoutManager horizontalPopularLayoutManager;
    private EventHandler eventHandler;
    private ArrayList<Object> dataList = new ArrayList<>();
    private ArrayList<CardNews> popularList = new ArrayList<>();

    public MyEditorFragment(Context context, FragmentMyeditorBinding binding) {
        this.context = context;
        this.binding = binding;
        initialize();
        loadData();
        setFragment();
    }

    private void initialize() {
        eventHandler = new EventHandler(context);
        myEditorRecyclerView = binding.myEditorRecyclerView;
        layoutManager = new GridLayoutManager(context, 2);
        horizontalPopularLayoutManager = new LinearLayoutManager(context);
        horizontalPopularLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position < 3) ? 2 : 1;
            }
        });
    }

    private void loadData() {
        dataList.add(new User());
        dataList.add(new ArrayList<CardNews>());
        dataList.add(new CardNews("asdf", "Asdf", "asdf"));
        dataList.add(new CardNews("asdf", "Asdf", "asdf"));
        dataList.add(new CardNews("asdf", "Asdf", "asdf"));
        dataList.add(new CardNews("asdf", "Asdf", "asdf"));
        dataList.add(new CardNews("asdf", "Asdf", "asdf"));
        dataList.add(new CardNews("asdf", "Asdf", "asdf"));
        dataList.add(new CardNews("asdf", "Asdf", "asdf"));

        popularList.add(new CardNews("asdf", "asdf", "asdf"));
        popularList.add(new CardNews("asdf", "asdf", "asdf"));
        popularList.add(new CardNews("asdf", "asdf", "asdf"));
        popularList.add(new CardNews("asdf", "asdf", "asdf"));
        popularList.add(new CardNews("asdf", "asdf", "asdf"));
    }

    private void setFragment() {
        myEditorRecyclerView.setLayoutManager(layoutManager);
        LastAdapter.with(dataList, BR.item)
                .map(User.class, new ItemType<MyeditorHeaderBinding>(R.layout.myeditor_header) {
                    @Override
                    public void onBind(@NotNull ViewHolder<MyeditorHeaderBinding> viewHolder) {
                        super.onBind(viewHolder);
                    }
                })
                .map(ArrayList.class, new ItemType<MyeditorPopularListBinding>(R.layout.myeditor_popular_list) {
                    @Override
                    public void onBind(@NotNull ViewHolder<MyeditorPopularListBinding> viewHolder) {
                        super.onBind(viewHolder);
                        MyeditorPopularListBinding binding = viewHolder.getBinding();
                        binding.popularRecyclerView.setLayoutManager(horizontalPopularLayoutManager);
                        LastAdapter.with(popularList, BR._all)
                                .map(CardNews.class, new ItemType<MyeditorPopularListContentBinding>(R.layout.myeditor_popular_list_content) {

                                    @Override
                                    public void onBind(@NotNull ViewHolder<MyeditorPopularListContentBinding> viewHolder) {
                                        super.onBind(viewHolder);
                                    }
                                })
                                .into(binding.popularRecyclerView);
                    }
                })
                .map(CardNews.class, new ItemType<MainNewsfeedCommonContentBinding>(R.layout.main_newsfeed_common_content) {
                    @Override
                    public void onBind(@NotNull ViewHolder<MainNewsfeedCommonContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().setEventHandler(eventHandler);
                    }
                })
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        if (o instanceof User) return R.layout.myeditor_header;
                        else if (o instanceof ArrayList) return R.layout.myeditor_popular_list;
                        else return R.layout.main_newsfeed_common_content;
                    }
                })
                .into(myEditorRecyclerView);
    }
}
