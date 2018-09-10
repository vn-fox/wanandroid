package com.wng.wanandroid.base;

public interface BaseContract {
    interface View {
        boolean isNetworkConnected();
        void showError(String message);
    }

    interface Presenter<V extends BaseContract.View> {
        void attachView(V view);
        void detachView();
    }
}
