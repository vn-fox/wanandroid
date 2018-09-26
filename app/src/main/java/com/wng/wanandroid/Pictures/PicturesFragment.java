package com.wng.wanandroid.Pictures;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wng.wanandroid.R;
import com.wng.wanandroid.adapter.PicturesAdapter;
import com.wng.wanandroid.base.BaseFragment;
import com.wng.wanandroid.model.PicturesData;
import com.wng.wanandroid.view.SuperEasyRefreshLayout;

import java.util.List;

import butterknife.BindView;

public class PicturesFragment extends BaseFragment implements PicturesContract.View {
    @BindView(R.id.refresh_layout)
    SuperEasyRefreshLayout refreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private int currentPage;


    private PicturesContract.Presenter presenter;
    private PicturesAdapter adapter;

    @Override
    public void showList(List<PicturesData.PictureDetailData> data, int page) {
        if (page ==1){
            adapter.setData(data);

        } else {
            adapter.appendItems(data);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_pictures;
    }


    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        initListener();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter = new PicturesPresenter(this);
        presenter.getPictures(0);

        adapter = new PicturesAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void loadMore() {
        currentPage+=1;
        presenter.getPictures(currentPage);
    }


    private void initListener() {
        refreshLayout.setOnRefreshListener(new SuperEasyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                        presenter.getPictures(1);
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
                    }
                },500);
            }
        });
    }
}
