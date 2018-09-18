package com.wng.wanandroid.home;

import android.os.Handler;
import android.util.Log;

import com.wng.wanandroid.base.BasePresenter;
import com.wng.wanandroid.http.RetrofitClient;
import com.wng.wanandroid.http.RxService;
import com.wng.wanandroid.model.ArticleDetailData;
import com.wng.wanandroid.model.ArticlesData;

import java.util.List;


import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class HomePagePresenter extends BasePresenter<HomePageContract.View> implements HomePageContract.Presenter{
    private static final String TAG = "HomePagePresenter";
    private HomePageContract.View view;
    private Disposable disposable;

    public  HomePagePresenter(HomePageContract.View view){
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
    public void getArticles(int page) {
        Log.d(TAG, "getArticles: " + page);
        loadDataRx(page);

    }
    public void loadDataRx(final int page) {
        disposable = RetrofitClient.getInstance()
                .create(RxService.class)
                .getArticles(page)
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
                  Log.d(TAG, "accept: " + articleDetailData.size());
                  view.showList(articleDetailData, page);
              }
          });
    }

}
