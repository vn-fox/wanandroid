package com.wng.wanandroid.search;


import com.wng.wanandroid.base.BaseContract;
import com.wng.wanandroid.model.ArticleDetailData;

import java.util.List;

public interface SearchContract {
    interface View extends BaseContract.View {
        void closeKeyboard();
        void showNoResult();
        void showResults(List<ArticleDetailData> resultItems, int page);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void search(String keyword, int page);
    }
}
