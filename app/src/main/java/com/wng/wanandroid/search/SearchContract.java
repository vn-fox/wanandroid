package com.wng.wanandroid.search;


import com.wng.wanandroid.base.BaseContract;
import com.wng.wanandroid.model.ArticleDetailData;
import com.wng.wanandroid.model.HotKeyDetailData;

import java.util.List;

public interface SearchContract {
    interface View extends BaseContract.View {
        void hideImn();
        void showEmptyView(boolean toShow);
        void showNoResult();
        void showResults(List<ArticleDetailData> resultItems, int page);
        void showHotKeys(List<HotKeyDetailData> hotKeyList);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void search(String keyword, int page);
        void getHotKeys();
    }
}
