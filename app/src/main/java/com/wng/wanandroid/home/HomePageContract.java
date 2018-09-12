package com.wng.wanandroid.home;

import com.wng.wanandroid.base.BaseContract;
import com.wng.wanandroid.model.ArticleDetailData;

import java.util.List;

public interface HomePageContract {
    interface View extends BaseContract.View {
        void showList(List<ArticleDetailData> data, int page);
    }

    interface Presenter extends BaseContract.Presenter<HomePageContract.View> {
        void getArticles(int page);
    }
}
