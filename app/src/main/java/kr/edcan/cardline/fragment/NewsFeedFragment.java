package kr.edcan.cardline.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.BaseType;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.Type;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.FragmentCardlinestudioBinding;
import kr.edcan.cardline.databinding.FragmentMyeditorBinding;
import kr.edcan.cardline.databinding.FragmentNewsfeedBinding;
import kr.edcan.cardline.databinding.MainNewsfeedHeaderBinding;
import kr.edcan.cardline.utils.CartaTagThemeHelper;
import kr.edcan.cardline.views.CartaTagView;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class NewsFeedFragment {
    private Context context;
    private FragmentNewsfeedBinding binding;
    private MainNewsfeedHeaderBinding headerBinding;
    private ArrayList<Object> arrayList = new ArrayList<>();
    private ArrayList<CartaTagView> menuList = new ArrayList<>();

    private int currentTabPosition = 0;

    public NewsFeedFragment(Context context, FragmentNewsfeedBinding binding) {
        this.context = context;
        this.binding = binding;
        arrayList.add("asdf");
        setFragment();
    }

    private void setFragment() {
        binding.newsFeedRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        LastAdapter.with(arrayList, BR._all)
                .map(String.class, new ItemType<MainNewsfeedHeaderBinding>(R.layout.main_newsfeed_header) {
                    @Override
                    public void onBind(@NotNull MainNewsfeedHeaderBinding binding, @NotNull View view, int position) {
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
                        super.onBind(binding, view, position);
                    }

                })
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        if (o instanceof String) return R.layout.main_newsfeed_header;
                        return -1;
                    }
                })
                .into(binding.newsFeedRecyclerView);
    }

    private void selectTab(int position) {
        if(menuList.size() != 0) {
            for (int i = 0; i < menuList.size(); i++) {
                menuList.get(i).setConfiguration((position == i) ? CartaTagThemeHelper.selectedMainTab : CartaTagThemeHelper.nonSelectedMainTab);
            }
            currentTabPosition = position;

            // Update RecyclerView
        }
    }
}

