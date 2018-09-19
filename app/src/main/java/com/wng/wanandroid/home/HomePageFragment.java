package com.wng.wanandroid.home;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wng.wanandroid.R;
import com.wng.wanandroid.adapter.HomePageAdapter;
import com.wng.wanandroid.base.BaseFragment;
import com.wng.wanandroid.model.ArticleDetailData;
import com.wng.wanandroid.view.SuperEasyRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class HomePageFragment extends BaseFragment implements HomePageContract.View{
    private static final String TAG = "HomePageFragment";
    @BindView(R.id.refresh_layout)
    SuperEasyRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
//    @BindView(R.id.nested_scroll_view)
//    NestedScrollView nestedScrollView;
    private int currentPage;
    private HomePageContract.Presenter presenter;
    private LinearLayoutManager layoutManager;
    private HomePageAdapter adapter;

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        Handler handler = new Handler();
        presenter = new HomePagePresenter(this);
        presenter.getArticles(0);
        initListener();
//        refreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorPrimary));
//        refreshLayout.setOnRefreshListener(new Su.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                currentPage = 0;
//                presenter.getArticles(currentPage);
//            }
//        });
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
//                if (i1 == (nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight())) {
//                    loadMore();
//                }
//            }
//        });
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.VISIBLE);

        adapter = new HomePageAdapter();
        recyclerView.setAdapter(adapter);

    }

    private void initListener() {
        refreshLayout.setOnRefreshListener(new SuperEasyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    //    presenter.getArticles(0);
                        Toast.makeText(getActivity(),"刷新 成功",Toast.LENGTH_SHORT).show();
                    }
                },500);
            }
        });

        refreshLayout.setOnLoadMoreListener(new SuperEasyRefreshLayout.OnLoadMoreListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMore();
                        refreshLayout.finishLoadMore();
                        Toast.makeText(getActivity(),"加载更多成功",Toast.LENGTH_SHORT).show();
                    }
                },500);
            }
        });
    }
    private void loadMore() {
        currentPage+=1;
        presenter.getArticles(currentPage);
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void showList(List<ArticleDetailData> data, int page) {
        if (page == 0) {
            adapter.setData(data);
        } else {
            adapter.appendItems(data);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }
}
