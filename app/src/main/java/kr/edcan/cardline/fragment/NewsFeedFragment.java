package kr.edcan.cardline.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.FragmentNewsfeedBinding;
import kr.edcan.cardline.databinding.MainNewsfeedCommonContentBinding;
import kr.edcan.cardline.databinding.MainNewsfeedHeaderBinding;
import kr.edcan.cardline.handler.EventHandler;
import kr.edcan.cardline.models.CardNews;
import kr.edcan.cardline.utils.CartaTagThemeHelper;
import kr.edcan.cardline.views.CartaTagView;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class NewsFeedFragment extends Fragment{
    private FragmentNewsfeedBinding binding;
    private MainNewsfeedHeaderBinding headerBinding;
    private ArrayList<Object> arrayList = new ArrayList<>();
    private ArrayList<CartaTagView> menuList = new ArrayList<>();
    private NewsFeedFragment fragment;

    private GridLayoutManager layoutManager;
    private LastAdapter lastAdapter;
    private EventHandler eventHandler;
    private int currentTabPosition = 0;
    private int mPageNumber;
    private String title;

    public static NewsFeedFragment create(int pageNumber) {
        NewsFeedFragment fragment = new NewsFeedFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_newsfeed, container, false);
        initialize();
        loadData();
        setFragment();
        return binding.getRoot();
    }


    private void initialize() {
        eventHandler = new EventHandler(getContext());
        layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position < 2) ? 2 : 1;
            }
        });
        binding.newsFeedRecyclerView.setLayoutManager(layoutManager);


    }

    private void loadData() {
        // dummy datas, will be changed to cardline news data
        arrayList.add("asdf");
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
        arrayList.add(new CardNews("title", "content", "url"));
    }

    private void setFragment() {
        lastAdapter = new LastAdapter(arrayList, BR.item)
                .map(String.class, new ItemType<MainNewsfeedHeaderBinding>(R.layout.main_newsfeed_header) {
                    @Override
                    public void onBind(@NotNull Holder<MainNewsfeedHeaderBinding> viewHolder) {
                        MainNewsfeedHeaderBinding binding = viewHolder.getBinding();
                        Collections.addAll(menuList, binding.newsfeedTodaySuggestion, binding.newsfeedPolitics, binding.newsfeedEntertainment, binding.newsfeedScienceTechnology, binding.newsfeedItTechnology, binding.newsfeedLifeReview, binding.newsfeedMovieMusic, binding.newsfeedAnimal, binding.newsfeedComicAnimation, binding.newsfeedCooking);
                        selectTab(0);
                        for (int i = 0; i < menuList.size(); i++) {
                            final int finalI = i;
                            menuList.get(i).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    selectTab(finalI);
                                }
                            });
                        }
                        super.onBind(viewHolder);
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
                        if (o instanceof String) return R.layout.main_newsfeed_header;
                        return R.layout.main_newsfeed_common_content;
                    }
                })
                .into(binding.newsFeedRecyclerView);
    }

    private void selectTab(int position) {
        if (menuList.size() != 0) {
            for (int i = 0; i < menuList.size(); i++) {
                menuList.get(i).setConfiguration((position == i) ? CartaTagThemeHelper.selectedMainTab : CartaTagThemeHelper.nonSelectedMainTab);
            }
            currentTabPosition = position;
            Log.e("asdf", position + "");

            // Update RecyclerView
        }
    }


}

