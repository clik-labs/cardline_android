package kr.edcan.cardline.activity;

import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityNewCardBinding;
import kr.edcan.cardline.databinding.ContentNewcardBinding;
import kr.edcan.cardline.utils.CartaTagThemeHelper;
import kr.edcan.cardline.views.CartaTagConfiguration;
import kr.edcan.cardline.views.CartaTagView;

public class NewCardActivity extends BaseActivity {


    ActivityNewCardBinding binding;
    RecyclerView typeSelecter;
    GridLayoutManager layoutManager;
    ArrayList<Type> typeList = new ArrayList<>();
    LastAdapter typeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {
        setToolbarTitle("새로 만들기");
        binding = (ActivityNewCardBinding) baseBinding;
        layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        typeSelecter = binding.touchContainer;
        typeSelecter.setLayoutManager(layoutManager);
        setList();
        typeAdapter = new LastAdapter(typeList, BR.content)
                .map(Type.class, new ItemType<ContentNewcardBinding>(R.layout.content_newcard) {
                    @Override
                    public void onBind(@NotNull final Holder<ContentNewcardBinding> holder) {
                        super.onBind(holder);
                        holder.getBinding().tagView.setConfiguration(
                                (typeList.get(holder.getAdapterPosition()).isEnabled())
                                        ? CartaTagThemeHelper.selectedMainTab : CartaTagThemeHelper.nonSelectedMainTab
                        );
                        holder.getBinding().tagView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                for (int i = 0; i < typeList.size(); i++) {
                                    typeList.get(i).setEnabled(i == holder.getAdapterPosition());
                                    ((CartaTagView) typeSelecter.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.tagView))
                                            .setConfiguration((typeList.get(i).isEnabled())
                                                    ? CartaTagThemeHelper.selectedMainTab : CartaTagThemeHelper.nonSelectedMainTab);
                                }
                            }
                        });
                    }
                })
                .into(typeSelecter);

        binding.makeCardNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch EditActivity
                int type = currentSelectedPosition();
            }
        });
    }

    private void setList() {
        Collections.addAll(typeList,
                new Type("정치", true),
                new Type("연예"),
                new Type("과학 기술"),
                new Type("IT / 산업"),
                new Type("생활 / 리뷰"),
                new Type("영화 / 음악"),
                new Type("냥 / 멍"),
                new Type("만화 / 애니"),
                new Type("요리")
        );
    }


    @Override
    protected int onCreateViewId() {
        return R.layout.activity_new_card;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    int currentSelectedPosition() {
        for (int i = 0; i < typeList.size(); i++) {
            if (typeList.get(i).isEnabled) return i;
        }
        return 0;
    }

    public class Type {
        private boolean isEnabled = false;
        private String title;

        public Type(String title) {
            this.title = title;
        }

        public Type(String title, boolean isEnabled) {
            this.title = title;
            this.isEnabled = isEnabled;
        }

        public boolean isEnabled() {
            return isEnabled;
        }

        public void setEnabled(boolean enabled) {
            isEnabled = enabled;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
