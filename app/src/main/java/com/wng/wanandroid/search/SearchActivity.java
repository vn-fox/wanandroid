package com.wng.wanandroid.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.wng.wanandroid.R;
import com.wng.wanandroid.adapter.SearchResultAdapter;
import com.wng.wanandroid.base.BaseActivity;
import com.wng.wanandroid.model.ArticleDetailData;
import com.wng.wanandroid.model.HotKeyDetailData;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity implements SearchContract.View{
    @BindView(R.id.search_view)
    android.support.v7.widget.SearchView searchView;
    @BindView(R.id.search_results)
    RecyclerView resultsRecyclerView;
    @BindView(R.id.no_result_layout)
    FrameLayout noResultLayout;
    @BindView(R.id.flow_layout)
    TagFlowLayout flowLayout;

    private SearchResultAdapter searchResultAdapter;
    private SearchContract.Presenter presenter;
    private final int INDEX = 0;
    private int currentPage;
    private String keyWords;
    private LinearLayoutManager layoutManager;
    private int articlesListSize;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        layoutManager = new LinearLayoutManager(this);
        resultsRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.recycler_divider));
        resultsRecyclerView.addItemDecoration(itemDecoration);
        searchResultAdapter = new SearchResultAdapter();
        resultsRecyclerView.setAdapter(searchResultAdapter);
        presenter = new SearchPresenter(this);
        presenter.getHotKeys();

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                hideImn();
                hideTagFlowLayout(true);
                presenter.search(s, INDEX);
                keyWords = s;
                currentPage = INDEX;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                hideTagFlowLayout(false);
                return true;
            }
        });


        //滑动到底部加载下一页
        resultsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() == articlesListSize - 1) {
                        loadMore();
                    }
                }
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void showNoResult() {

    }

    @Override
    public void showResults(List<ArticleDetailData> resultItems, int page) {
        if (resultItems == null) {
            showEmptyView(true);
            return;
        }
        showEmptyView(false);
        searchResultAdapter.setData(resultItems);


    }

    @Override
    public void showHotKeys(final List<HotKeyDetailData> hotKeyList) {
        flowLayout.setAdapter(new TagAdapter<HotKeyDetailData>(hotKeyList) {
            @Override
            public View getView(FlowLayout parent, int position, HotKeyDetailData hotKeyDetailData) {
                TextView textView = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.item_flow_layout, flowLayout, false);
                if (hotKeyList == null) {
                    return null;
                }
                textView.setText(hotKeyDetailData.getName());
                flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                    @Override
                    public boolean onTagClick(View view, int position, FlowLayout parent) {
                        //这个逻辑会回调searchView的onQueryTextSubmit方法
                        searchView.setQuery(hotKeyList.get(position).getName(),true);
                        return true;
                    }
                });
                return textView;
            }
        });
    }

    private void loadMore() {
        currentPage+=1;
        presenter.search(keyWords,currentPage);
    }
    private void hideTagFlowLayout(boolean hide) {
        if (hide) {
            flowLayout.setVisibility(View.GONE);
            resultsRecyclerView.setVisibility(View.VISIBLE);
        }else {
            flowLayout.setVisibility(View.VISIBLE);
            resultsRecyclerView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean isActive() {
        return true;

    }

    @Override
    public void hideImn() {
        InputMethodManager manager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isActive()) {
            manager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        }
    }

    @Override
    public void showEmptyView(boolean toShow) {
        noResultLayout.setVisibility(toShow?View.VISIBLE:View.INVISIBLE);
        resultsRecyclerView.setVisibility(!toShow?View.VISIBLE:View.INVISIBLE);
    }
}
