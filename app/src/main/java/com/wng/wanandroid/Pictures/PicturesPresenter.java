package com.wng.wanandroid.Pictures;

import android.util.Log;

import com.wng.wanandroid.base.BasePresenter;
import com.wng.wanandroid.http.RetrofitClient;
import com.wng.wanandroid.http.RxService;
import com.wng.wanandroid.model.PicturesData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class PicturesPresenter extends BasePresenter<PicturesContract.View> implements PicturesContract.Presenter {
    private static final String TAG = "PicturesPresenter";
    private Disposable disposable;
    private PicturesContract.View view;

    PicturesPresenter(PicturesContract.View view) {
        this.view = view;
    }

    @Override
    public void getPictures(final int page) {

        disposable = RetrofitClient.getInstanceP()
                .create(RxService.class)
                .getPictures(page)
                .filter(new Predicate<PicturesData> () {
                    @Override
                    public boolean test(PicturesData picturesData) throws Exception {
                        return picturesData.isError() != true;
                    }
                }).map(new Function<PicturesData, List<PicturesData.PictureDetailData>>() {
                    @Override
                    public List<PicturesData.PictureDetailData> apply(PicturesData picturesData) throws Exception {
                        return picturesData.getResults();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PicturesData.PictureDetailData>>() {
                    @Override
                    public void accept(List<PicturesData.PictureDetailData> list) throws Exception {
                        for (PicturesData.PictureDetailData data : list
                                ) {
                            Log.d(TAG, "accept: " + data.getUrl());
                        }
                        view.showList(list,page);
                    }
                });

    }
}
