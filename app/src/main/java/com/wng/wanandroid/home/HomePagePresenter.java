package com.wng.wanandroid.home;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.wng.wanandroid.MainActivity;
import com.wng.wanandroid.base.BasePresenter;
import com.wng.wanandroid.model.ArticleDetailData;
import com.wng.wanandroid.model.ArticlesData;

import java.io.IOException;
import java.util.List;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomePagePresenter extends BasePresenter<HomePageContract.View> implements HomePageContract.Presenter{
    private static final String TAG = "HomePagePresenter";
    private HomePageContract.View view;
    private Handler handler;

    public  HomePagePresenter(HomePageContract.View view, Handler handler){
        this.view = view;
        this.handler = handler;
    }
    @Override
    public void getArticles(int page) {
        Log.d(TAG, "getArticles: " + page);
        loadData(page);

    }
    public void loadDataRx(int page) {

    }

    public void loadData(final int page) {
        OkHttpClient okHttpClient = new OkHttpClient();

         Request request = new Request.Builder()
                .url("http://www.wanandroid.com/article/list/" + page + "/json")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.d(TAG, "onResponse: result: " + result);
                ArticlesData articlesData = new Gson().fromJson(result, ArticlesData.class);
                ArticlesData.Data articleData = articlesData.getData();
                final List<ArticleDetailData> list = articleData.getDatas();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.showList(list, page);
                    }
                });
                //  view.showList(list, page);
            }
        });

    }
}
