package com.wng.wanandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.wng.wanandroid.Pictures.PicturesFragment;
import com.wng.wanandroid.base.BaseActivity;
import com.wng.wanandroid.categories.CategoriesFragment;
import com.wng.wanandroid.helper.BottomNavigationViewHelper;
import com.wng.wanandroid.home.HomePageFragment;
import com.wng.wanandroid.search.SearchActivity;

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
        HomePageFragment homePageFragment = new HomePageFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, homePageFragment).show(homePageFragment)
                .commit();

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
            }
        });
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.home_page:
                        selectedFragment = new HomePageFragment();
                        break;
                    case R.id.knowledge_categories:
                        selectedFragment = new CategoriesFragment();
                        break;
                    case R.id.picture:
                        selectedFragment = new PicturesFragment();
                        break;
                    case R.id.more:
                        break;

                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment).show(selectedFragment)
                            .commit();
                }
                return true;
            }
        });


    }
}
