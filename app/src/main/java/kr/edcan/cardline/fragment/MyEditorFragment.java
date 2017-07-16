package kr.edcan.cardline.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

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

public class MyEditorFragment extends Fragment {
    private FragmentMyeditorBinding binding;
    private RecyclerView myEditorRecyclerView;
    private GridLayoutManager layoutManager;
    private LinearLayoutManager horizontalPopularLayoutManager;
    private EventHandler eventHandler;
    private ArrayList<Object> dataList = new ArrayList<>();
    private ArrayList<CardNews> popularList = new ArrayList<>();

    private int mPageNumber;
    private String title;


    public static MyEditorFragment create(int pageNumber) {
        MyEditorFragment fragment = new MyEditorFragment();
        Bundle args = new Bundle();
        args.putInt("page", pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt("page");
        title = getArguments().getString("exchange");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_myeditor, container, false);
        initialize();
        loadData();
        setFragment();
        return binding.getRoot();
    }


    private void initialize() {
        eventHandler = new EventHandler(getContext());
        myEditorRecyclerView = binding.myEditorRecyclerView;
        layoutManager = new GridLayoutManager(getContext(), 2);
        horizontalPopularLayoutManager = new LinearLayoutManager(getContext());
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
        new LastAdapter(dataList, BR.item)
                .map(User.class, new ItemType<MyeditorHeaderBinding>(R.layout.myeditor_header) {
                    @Override
                    public void onBind(@NotNull Holder<MyeditorHeaderBinding> viewHolder) {
                        super.onBind(viewHolder);
                    }
                })
                .map(ArrayList.class, new ItemType<MyeditorPopularListBinding>(R.layout.myeditor_popular_list) {
                    @Override
                    public void onBind(@NotNull Holder<MyeditorPopularListBinding> viewHolder) {
                        super.onBind(viewHolder);
                        MyeditorPopularListBinding binding = viewHolder.getBinding();
                        binding.popularRecyclerView.setLayoutManager(horizontalPopularLayoutManager);
                        new LastAdapter(popularList, BR._all)
                                .map(CardNews.class, new ItemType<MyeditorPopularListContentBinding>(R.layout.myeditor_popular_list_content) {

                                    @Override
                                    public void onBind(@NotNull Holder<MyeditorPopularListContentBinding> viewHolder) {
                                        super.onBind(viewHolder);
                                    }
                                })
                                .into(binding.popularRecyclerView);
                    }
                })
                .map(CardNews.class, new ItemType<MainNewsfeedCommonContentBinding>(R.layout.main_newsfeed_common_content) {
                    @Override
                    public void onBind(@NotNull Holder<MainNewsfeedCommonContentBinding> viewHolder) {
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
