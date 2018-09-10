package com.wng.wanandroid.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.wng.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View{
    @Nullable @BindView(R.id.toolbar) protected Toolbar toolbar;
    @Nullable @BindView(R.id.appbar_layout) protected AppBarLayout appbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(getLayout());


        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

    }
    public abstract int getLayout();


//    public void enableAppBarElevation(boolean enable) {
//        if (appbarLayout != null) {
//            appbarLayout.setElevation(enable ? getResources().getDimension(R.dimen.four_dp) : 0.0f);
//        }
//    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void showError(String message) {
        Log.e(getClass().getName(), message);
        Toast.makeText(this, R.string.toast_message_error_occurred, Toast.LENGTH_SHORT).show();
    }
}
