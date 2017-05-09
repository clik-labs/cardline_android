package kr.edcan.cardline.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.Type;

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
    private ArrayList<Object> dataList = new ArrayList<>();
    private ArrayList<CardNews> popularList = new ArrayList<>();

    public MyEditorFragment(Context context, FragmentMyeditorBinding binding) {
        this.context = context;
        this.binding = binding;
        setFragment();
    }

    private void setFragment() {
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
        myEditorRecyclerView.setLayoutManager(layoutManager);
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
        LastAdapter.with(dataList, BR._all)
                .map(User.class, new Type<MyeditorHeaderBinding>(R.layout.myeditor_header)
                        .onBind(new Function1<Type.Params<? extends MyeditorHeaderBinding>, Unit>() {
                            @Override
                            public Unit invoke(Type.Params<? extends MyeditorHeaderBinding> params) {
                                return null;
                            }
                        })
                        .onClick(new Function1<Type.Params<? extends MyeditorHeaderBinding>, Unit>() {
                            @Override
                            public Unit invoke(Type.Params<? extends MyeditorHeaderBinding> params) {
                                return null;
                            }
                        }))
                .map(ArrayList.class, new Type<MyeditorPopularListBinding>(R.layout.myeditor_popular_list)
                        .onClick(new Function1<Type.Params<? extends MyeditorPopularListBinding>, Unit>() {
                            @Override
                            public Unit invoke(Type.Params<? extends MyeditorPopularListBinding> params) {
                                return null;
                            }
                        })
                        .onBind(new Function1<Type.Params<? extends MyeditorPopularListBinding>, Unit>() {
                            @Override
                            public Unit invoke(Type.Params<? extends MyeditorPopularListBinding> params) {

                                params.getBinding().popularRecyclerView.setLayoutManager(horizontalPopularLayoutManager);
                                LastAdapter.with(popularList, BR._all)
                                        .map(CardNews.class, new Type<MyeditorPopularListContentBinding>(R.layout.myeditor_popular_list_content)
                                                .onBind(new Function1<Type.Params<? extends MyeditorPopularListContentBinding>, Unit>() {
                                                    @Override
                                                    public Unit invoke(Type.Params<? extends MyeditorPopularListContentBinding> params) {
                                                        return null;
                                                    }
                                                }).onClick(new Function1<Type.Params<? extends MyeditorPopularListContentBinding>, Unit>() {
                                                    @Override
                                                    public Unit invoke(Type.Params<? extends MyeditorPopularListContentBinding> params) {
                                                        return null;
                                                    }
                                                })).into(params.getBinding().popularRecyclerView);
                                return null;
                            }
                        }))
                .map(CardNews.class, new Type<MainNewsfeedCommonContentBinding>(R.layout.main_newsfeed_common_content)
                        .onBind(new Function1<Type.Params<? extends MainNewsfeedCommonContentBinding>, Unit>() {
                            @Override
                            public Unit invoke(Type.Params<? extends MainNewsfeedCommonContentBinding> params) {
                                return null;
                            }
                        })
                        .onClick(new Function1<Type.Params<? extends MainNewsfeedCommonContentBinding>, Unit>() {
                            @Override
                            public Unit invoke(Type.Params<? extends MainNewsfeedCommonContentBinding> params) {
                                return null;
                            }
                        }))
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
