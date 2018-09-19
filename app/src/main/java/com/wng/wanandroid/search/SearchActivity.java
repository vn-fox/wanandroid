package com.wng.wanandroid.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.wng.wanandroid.R;
import com.wng.wanandroid.adapter.SearchResultAdapter;
import com.wng.wanandroid.base.BaseActivity;
import com.wng.wanandroid.model.ArticleDetailData;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity implements SearchContract.View{
    @BindView(R.id.search_input)
    EditText searchInput;
    @BindView(R.id.clear_iv)
    ImageView clearInput;
    @BindView(R.id.search_results)
    RecyclerView resultsRecyclerView;
    @BindView(R.id.no_result_layout)
    FrameLayout noResultLayout;

    private SearchResultAdapter searchResultAdapter;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        resultsRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.recycler_divider));
        resultsRecyclerView.addItemDecoration(itemDecoration);
        searchResultAdapter = new SearchResultAdapter();
        resultsRecyclerView.setAdapter(searchResultAdapter);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void closeKeyboard() {

    }

    @Override
    public void showNoResult() {

    }

    @Override
    public void showResults(List<ArticleDetailData> resultItems, int page) {

    }
}
