package com.wng.wanandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.wng.wanandroid.R;
import com.wng.wanandroid.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebVeiewActivity extends BaseActivity{
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.web_view_progressbar)
    ProgressBar webViewProgressBar;

    private String url;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("URL");
        title = intent.getStringExtra("TITLE");

        toolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
        getSupportActionBar().setTitle(title != null ? title : "");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                webViewProgressBar.setProgress(newProgress);
                if (newProgress >= 100) {
                    webViewProgressBar.setVisibility(View.GONE);
                } else {
                    webViewProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_web_view;
    }
}
