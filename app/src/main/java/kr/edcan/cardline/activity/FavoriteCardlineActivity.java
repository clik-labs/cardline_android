package kr.edcan.cardline.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridLayout;

import com.github.nitrico.lastadapter.BR;
import com.github.nitrico.lastadapter.LastAdapter;
import com.github.nitrico.lastadapter.LayoutHandler;
import com.github.nitrico.lastadapter.Type;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityFavoriteCardlineBinding;
import kr.edcan.cardline.databinding.MainNewsfeedCommonContentBinding;
import kr.edcan.cardline.models.CardNews;

public class FavoriteCardlineActivity extends BaseActivity {

    private ActivityFavoriteCardlineBinding binding;
    private RecyclerView favoriteRecyclerView;
    private GridLayoutManager layoutManager;
    private ArrayList<CardNews> favoriteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {
        setToolbarTitle("내가 마음에 든 카드 목록");
        binding = (ActivityFavoriteCardlineBinding) baseBinding;
        favoriteRecyclerView = binding.favoriteRecyclerView;
        layoutManager = new GridLayoutManager(this, 2);
        favoriteRecyclerView.setLayoutManager(layoutManager);


        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));
        favoriteList.add(new CardNews("asdf", "asdf", "asdf"));

        LastAdapter.with(favoriteList, BR._all)
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
                        return R.layout.main_newsfeed_common_content;
                    }

                })
                .into(favoriteRecyclerView);
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_favorite_cardline;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
