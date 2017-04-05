package kr.edcan.cardline.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kr.edcan.cardline.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public abstract int returnLayoutState();
    void setToolbar(){

    }
}
