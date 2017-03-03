package com.example.yangliu.reading.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yangliu.reading.Beans.ResultBean;
import com.example.yangliu.reading.MyApplication;
import com.example.yangliu.reading.R;
import com.example.yangliu.reading.tools.MyDBOpenHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WebViewActivity extends Activity {
    @InjectView(R.id.wed_title)
    TextView wedTitle;
    @InjectView(R.id.back)
    ImageView back;
    private WebView wv;
    private Toolbar toolbar;
    private ResultBean result1;
    private SQLiteDatabase db;
    private StringBuilder sb;
    MyDBOpenHelper myDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.inject(this);
        if(!MyApplication.isnet){
            Toast.makeText(this,"请检查网络连接！",Toast.LENGTH_SHORT).show();
        }
        wv = (WebView) findViewById(R.id.science_wv);
        toolbar = (Toolbar) findViewById(R.id.Webtoolbar);
        Intent intent = getIntent();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final String parent = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        wedTitle.setText(title);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(parent);
    }

    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        }
        super.onBackPressed();
    }


}
