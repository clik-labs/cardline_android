package kr.edcan.cardline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.edcan.cardline.R;

public class InformationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setDefault() {
        setToolbarTitle(getResources().getString(R.string.information));
    }

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_information;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }
}
