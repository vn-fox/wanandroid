package com.wng.wanandroid.model;

import java.util.List;

public class ArticlesData {
    private int errorCode;
    private String errorMsg;
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {
        public String getCurPage() {
            return curPage;
        }

        public void setCurPage(String curPage) {
            this.curPage = curPage;
        }

        public List<ArticleDetailData> getDatas() {
            return datas;
        }

        public void setDatas(List<ArticleDetailData> datas) {
            this.datas = datas;
        }

        private String curPage;
        private List<ArticleDetailData> datas;
    }
}
