package com.wng.wanandroid.http;

import com.wng.wanandroid.model.ArticlesData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RxService {
    String BaseUri = "http://www.wanandroid.com/";

    @GET("article/list/{page}/json")
    Observable<ArticlesData> getArticles(@Path("page") int page);
}
