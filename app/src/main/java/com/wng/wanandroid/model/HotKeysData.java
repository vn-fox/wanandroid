package com.wng.wanandroid.model;

import java.util.List;

public class HotKeysData {
    private int errorCode;
    private String errorMsg;
    private List<HotKeyDetailData> data;

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

    public List<HotKeyDetailData> getData() {
        return data;
    }

    public void setData(List<HotKeyDetailData> data) {
        this.data = data;
    }
}
