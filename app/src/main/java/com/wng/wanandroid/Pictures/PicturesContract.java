package com.wng.wanandroid.Pictures;

import com.wng.wanandroid.base.BaseContract;
import com.wng.wanandroid.model.PicturesData;

import java.util.List;

public interface PicturesContract {
    interface View extends BaseContract.View {
        void showList(List<PicturesData.PictureDetailData> data, int page);
    }

    interface Presenter extends BaseContract.Presenter<PicturesContract.View> {
        void getPictures(int page);
    }
}
