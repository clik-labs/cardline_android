package kr.edcan.cardline.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kr.edcan.cardline.R;
import kr.edcan.cardline.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected int onCreateViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected int onCreateViewToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setDefault() {
        binding = DataBindingUtil.setContentView(this, onCreateViewId());
        disableToggle();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
