package kr.edcan.cardline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.edcan.cardline.R;

public class CardEditorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {

    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_card_editor;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return 0;
    }
}
