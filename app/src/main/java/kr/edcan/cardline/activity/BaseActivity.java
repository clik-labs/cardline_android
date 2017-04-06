package kr.edcan.cardline.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import kr.edcan.cardline.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(onCreateViewId());
        if(onCreateViewToolbarId() != 0) this.setSupportActionBar((Toolbar)findViewById(onCreateViewToolbarId()));
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
    }

    protected abstract void setDefault();
    @LayoutRes
    protected abstract int onCreateViewId();
    @IdRes
    protected abstract int onCreateViewToolbarId();

}
