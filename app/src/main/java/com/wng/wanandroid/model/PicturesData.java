package com.wng.wanandroid.model;

import java.util.List;

public class PicturesData {
    private boolean error;
    private List<PictureDetailData> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<PictureDetailData> getResults() {
        return results;
    }

    public void setResults(List<PictureDetailData> results) {
        this.results = results;
    }

    public class PictureDetailData{
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;
    }
}
