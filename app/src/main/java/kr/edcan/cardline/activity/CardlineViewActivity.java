package kr.edcan.cardline.activity;

import android.os.Bundle;

import kr.edcan.cardline.R;

public class CardlineViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {

    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_cardline_view;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }
}
