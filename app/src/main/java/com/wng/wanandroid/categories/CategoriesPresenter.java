package com.wng.wanandroid.categories;

import android.util.Log;

import com.wng.wanandroid.base.BasePresenter;
import com.wng.wanandroid.home.HomePageContract;
import com.wng.wanandroid.http.RetrofitClient;
import com.wng.wanandroid.http.RxService;
import com.wng.wanandroid.model.CategoriesData;
import com.wng.wanandroid.model.CategoryDetailData;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class CategoriesPresenter extends BasePresenter<CategoriesContract.View> implements CategoriesContract.Presenter{
    private static final String TAG = "CategoriesPresenter";
    private CategoriesContract.View view;
    private Disposable disposable;
    public CategoriesPresenter(CategoriesContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void getCategories() {
        disposable = RetrofitClient.getInstance()
                .create(RxService.class)
                .getCategories()
                .filter(new Predicate<CategoriesData>() {
                    @Override
                    public boolean test(CategoriesData categoriesData) throws Exception {
                        return categoriesData.getErrorCode() != -1;
                    }
                }).map(new Function<CategoriesData, List<CategoryDetailData>>() {
                    @Override
                    public List<CategoryDetailData> apply(CategoriesData categoriesData) throws Exception {
                        return categoriesData.getData();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CategoryDetailData>>() {
                    @Override
                    public void accept(List<CategoryDetailData> list) throws Exception {
                        for (CategoryDetailData data : list
                             ) {
                            Log.d(TAG, "accept: " + data.getName());
                        }
                        view.showCategories(list);
                    }
                });
    }

}
