package com.wng.wanandroid;

import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import com.wng.wanandroid.base.BaseActivity;
import com.wng.wanandroid.helper.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.bottom_navigation_view) BottomNavigationView bottomNav;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: " + bottomNav);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);

    }
}
