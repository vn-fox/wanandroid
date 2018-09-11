package com.wng.wanandroid.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.wng.wanandroid.R;
import com.wng.wanandroid.base.BaseFragment;
import com.wng.wanandroid.model.ArticleDetailData;

import java.util.List;

import butterknife.BindView;

public class HomePageFragment extends BaseFragment implements HomePageContract.View{
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();



    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void showList(List<ArticleDetailData> data, int page) {

    }
}
