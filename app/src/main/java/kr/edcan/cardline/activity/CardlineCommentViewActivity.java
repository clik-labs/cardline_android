package kr.edcan.cardline.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityCardlineCommentViewBinding;

public class CardlineCommentViewActivity extends EditorBaseActivity {
    ActivityCardlineCommentViewBinding binding;
    String card_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cardline_comment_view);

        Intent i = getIntent();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("" + i.getStringExtra("title"));
        card_token = i.getStringExtra("token");

        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

}
