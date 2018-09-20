package com.wng.wanandroid.categories;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.wng.wanandroid.R;
import com.wng.wanandroid.adapter.CategoriesAdapter;
import com.wng.wanandroid.base.BaseFragment;
import com.wng.wanandroid.model.CategoryDetailData;

import java.util.List;

import butterknife.BindView;

public class CategoriesFragment extends BaseFragment implements CategoriesContract.View{
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.empty_view) LinearLayout emptyView;

    private CategoriesContract.Presenter presenter;
    private CategoriesAdapter adapter;


    @Override
    public int getLayout() {
        return R.layout.fragmeng_categories;
    }

    @Override
    public void showCategories(List<CategoryDetailData> data) {
        adapter.setData(data);
    }

    @Override
    public void showEmptyView(boolean toShow) {
        emptyView.setVisibility(toShow? View.VISIBLE:View.INVISIBLE);
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new CategoriesPresenter(this);
        presenter.getCategories();
        adapter = new CategoriesAdapter();
        recyclerView.setAdapter(adapter);

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
