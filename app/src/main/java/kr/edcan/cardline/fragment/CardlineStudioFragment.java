package kr.edcan.cardline.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.github.nitrico.lastadapter.Holder;
import com.github.nitrico.lastadapter.ItemType;
import com.github.nitrico.lastadapter.LastAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;

import kr.edcan.cardline.R;
import kr.edcan.cardline.activity.NewCardActivity;
import kr.edcan.cardline.databinding.FragmentCardlinestudioBinding;
import kr.edcan.cardline.databinding.StudioSavedContentBinding;
import kr.edcan.cardline.databinding.StudioSavedHeaderBinding;
import kr.edcan.cardline.models.CardNews;

/**
 * Created by Junseok Oh on 2017-04-09.
 */

public class CardlineStudioFragment extends Fragment implements View.OnClickListener {
    private int mPageNumber;
    private String title;
    private FragmentCardlinestudioBinding binding;

    ArrayList<Object> arrayList = new ArrayList<>();
    LastAdapter savedCardAdapter;
    RecyclerView savedCards;
    private GridLayoutManager layoutManager;


    public static CardlineStudioFragment create(int pageNumber) {
        CardlineStudioFragment fragment = new CardlineStudioFragment();
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

    public void onCardNewsClick(CardNews cardNews) {
        // Todo Launch Edit
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cardlinestudio, container, false);
        savedCards = binding.studioRecyclerView;
        layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position == 0) ? 2 : 1;
            }
        });
        savedCards.setLayoutManager(layoutManager);

        binding.makeCardOne.setOnClickListener(this);
        binding.makeCardTwo.setOnClickListener(this);
        Collections.addAll(arrayList,
                "저장된 저작물",
                new CardNews("title", "content", "url"),
                new CardNews("title", "content", "url"),
                new CardNews("title", "content", "url"),
                new CardNews("title", "content", "url"));

        savedCardAdapter = new LastAdapter(arrayList, BR.item)
                .map(String.class, new ItemType<StudioSavedHeaderBinding>(R.layout.studio_saved_header))
                .map(CardNews.class, new ItemType<StudioSavedContentBinding>(R.layout.studio_saved_content) {
                    @Override
                    public void onBind(@NotNull Holder<StudioSavedContentBinding> viewHolder) {
                        super.onBind(viewHolder);
                        viewHolder.getBinding().setActivity(CardlineStudioFragment.this);
                    }
                })
                .into(savedCards);
        savedCards.setFocusable(false);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(getContext(), NewCardActivity.class));
//        switch (view.getId()) {
//            case R.id.makeCardOne:
//                startActivity(new Intent(getContext(), EditorMainActivity.class).putExtra("cardType", 0));
//                break;
//            case R.id.makeCardTwo:
//                startActivity(new Intent(getContext(), EditorMainActivity.class).putExtra("cardType", 1));
//                break;
//        }
    }
}
