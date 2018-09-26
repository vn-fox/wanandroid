package com.wng.wanandroid.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private RetrofitClient() {
    }

    private static class ClientHolder{

//        private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                //添加对Cookies的管理
//                .cookieJar(new CookieManger(App.getContext()))
//                .build();


        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RxService.BaseUri)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        private static Retrofit retrofitP = new Retrofit.Builder()
                .baseUrl(RxService.BasePUri)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Retrofit getInstance(){
        return ClientHolder.retrofit;
    }

    public static Retrofit getInstanceP() {
        return ClientHolder.retrofitP;
    }
}
