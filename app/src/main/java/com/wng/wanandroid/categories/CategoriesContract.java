package com.wng.wanandroid.categories;

import com.wng.wanandroid.base.BaseContract;
import com.wng.wanandroid.home.HomePageContract;
import com.wng.wanandroid.model.CategoryDetailData;

import java.util.List;

public interface CategoriesContract {

    interface View extends BaseContract.View {
        void showCategories(List<CategoryDetailData> data);
        void showEmptyView(boolean toShow);

    }

    interface Presenter extends BaseContract.Presenter<CategoriesContract.View> {
        void getCategories();
    }
}
