package kr.edcan.cardline.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityCardlineViewBinding;
import kr.edcan.cardline.utils.ImageSingleTon;

public class CardlineViewActivity extends EditorBaseActivity {
    ActivityCardlineViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cardline_view);

        binding.showProfile.setImageUrl("http://precs.ivyro.net/files/2017.jpg", ImageSingleTon.getInstance(this).getImageLoader());
    }


}
