package com.wng.wanandroid.model;

import java.util.List;

public class CategoriesData {
    private List<CategoryDetailData> data;
    private int errorCode;
    private String errorMsg;

    public List<CategoryDetailData> getData() {
        return data;
    }

    public void setData(List<CategoryDetailData> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
