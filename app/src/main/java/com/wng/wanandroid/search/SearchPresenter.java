package com.wng.wanandroid.search;

import android.util.Log;

import com.wng.wanandroid.base.BasePresenter;
import com.wng.wanandroid.http.RetrofitClient;
import com.wng.wanandroid.http.RxService;
import com.wng.wanandroid.model.ArticleDetailData;
import com.wng.wanandroid.model.ArticlesData;
import com.wng.wanandroid.model.HotKeyDetailData;
import com.wng.wanandroid.model.HotKeysData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter{
    private static final String TAG = "SearchPresenter";
    private CompositeDisposable compositeDisposable;
    private SearchContract.View view;
    public SearchPresenter(SearchContract.View view) {
        compositeDisposable = new CompositeDisposable();
        this.view = view;
    }

    @Override
    public void getHotKeys() {
        Disposable disposable = RetrofitClient.getInstance()
                .create(RxService.class)
                .getHotKeys()
                .filter(new Predicate<HotKeysData>() {
                    @Override
                    public boolean test(HotKeysData hotKeysData) throws Exception {
                        return hotKeysData.getErrorCode() != -1;
                    }
                }).map(new Function<HotKeysData, List<HotKeyDetailData>>() {
            @Override
            public List<HotKeyDetailData> apply(HotKeysData hotKeysData) throws Exception {
                return hotKeysData.getData();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HotKeyDetailData>>() {
                    @Override
                    public void accept(List<HotKeyDetailData> hotKeyDetailData) throws Exception {
                        Log.d(TAG, "accept: " + hotKeyDetailData.size());
                        view.showHotKeys(hotKeyDetailData);
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void search(String keyword, final int page) {
        Disposable disposable = RetrofitClient.getInstance()
                .create(RxService.class)
                .queryArticles(page, keyword)
                .filter(new Predicate<ArticlesData>() {
                    @Override
                    public boolean test(ArticlesData articlesData) throws Exception {
                        return articlesData.getErrorCode() != -1;
                    }
                }).map(new Function<ArticlesData, List<ArticleDetailData>>() {
                    @Override
                    public List<ArticleDetailData> apply(ArticlesData articlesData) throws Exception {
                        return articlesData.getData().getDatas();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ArticleDetailData>>() {
                    @Override
                    public void accept(List<ArticleDetailData> articleDetailData) throws Exception {
                        Log.d(TAG, "accept: dd" + articleDetailData.size());
                        view.showResults(articleDetailData, page);
                    }
                });
        compositeDisposable.add(disposable);


    }

    @Override
    public void attachView(SearchContract.View view) {

    }
}
