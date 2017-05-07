package kr.edcan.cardline.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.FragmentCardlinestudioBinding;
import kr.edcan.cardline.databinding.FragmentMyeditorBinding;
import kr.edcan.cardline.databinding.FragmentNewsfeedBinding;
import kr.edcan.cardline.databinding.MainNewsfeedHeaderBinding;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class NewsFeedFragment {
    private Context context;
    private FragmentNewsfeedBinding binding;
    private MainNewsfeedHeaderBinding headerBinding;
    private ArrayList arrayList = new ArrayList<>();

    public NewsFeedFragment(Context context, FragmentNewsfeedBinding binding) {
        this.context = context;
        this.binding = binding;
        arrayList.add("aasdf");
        setFragment();
    }

    private void setFragment() {
        LastAdapter.with(arrayList, BR._all)
                .map(String.class,
                        new Type<ViewDataBinding>(R.layout.main_newsfeed_header)
                                .onBind(new Function1<Type.Params<? extends ViewDataBinding>, Unit>() {
                                    @Override
                                    public Unit invoke(Type.Params<? extends ViewDataBinding> params) {
                                        Log.e("Asdf", "asdf");
                                        return null;
                                    }
                                })
                                .onClick(new Function1<Type.Params<? extends ViewDataBinding>, Unit>() {
                                    @Override
                                    public Unit invoke(Type.Params<? extends ViewDataBinding> params) {
                                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                                        return null;
                                    }
                                }))
                .handler(new LayoutHandler() {
                    @Override
                    public int getItemLayout(@NotNull Object o, int i) {
                        return 0;
                    }
                })
                .into(binding.newsFeedRecyclerView);
    }
}
