package com.wng.wanandroid.http;

import com.wng.wanandroid.model.ArticlesData;
import com.wng.wanandroid.model.CategoriesData;
import com.wng.wanandroid.model.HotKeysData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RxService {
    String BaseUri = "http://www.wanandroid.com/";
    //获取主页文章列表
    @GET("article/list/{page}/json")
    Observable<ArticlesData> getArticles(@Path("page") int page);

    //获取查询的文章，k指的是用户输入的词
    @POST("article/query/{page}/json")
    Observable<ArticlesData> queryArticles(@Path("page") int page, @Query("k") String k);

    //获取热搜词
    @GET("/hotkey/json")
    Observable<HotKeysData> getHotKeys();

    //获取分类文章，cid指的是类别
    @GET("article/list/{page}/json")
    Observable<ArticlesData> getArticlesFromCatg(@Path("page") int page, @Query("cid") int cid);

    //获取文章的所有分类
    @GET("tree/json")
    Observable<CategoriesData> getCategories();
}
