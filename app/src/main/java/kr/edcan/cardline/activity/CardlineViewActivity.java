package kr.edcan.cardline.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityCardlineViewBinding;
import kr.edcan.cardline.utils.ImageSingleTon;


public class CardlineViewActivity extends EditorBaseActivity {
    ActivityCardlineViewBinding binding;
    Context context;
    String title;
    String type;
    String card_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cardline_view);
        context = this;

        Intent intent = getIntent(); // TODO 진행필
        TextView titleView = binding.showTitle;
        String[] s = titleView.getText().toString().split("\\s+");
        String front = "";
        String back = "";
        for (int i = 0; i < s.length; i++) {
            if (i < s.length / 2) {
                front = front + s[i] + " ";
            } else {
                back = back + s[i] + " ";
            }
        }

        binding.showTitle.setText(front + "\n" + back);

        binding.showProfile.setImageUrl("http://precs.ivyro.net/files/2017.jpg", ImageSingleTon.getInstance(this).getImageLoader());

        binding.cardlineCommentShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CardlineCommentViewActivity.class).putExtra("title", title).putExtra("card_token", card_token));
            }
        });
    }


}
