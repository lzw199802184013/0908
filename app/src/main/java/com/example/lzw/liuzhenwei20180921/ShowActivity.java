package com.example.lzw.liuzhenwei20180921;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lzw.liuzhenwei20180921.bean.NewsItem;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.BaseActivity;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.net.HelpAsync;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class ShowActivity extends BaseActivity {



    private WebView webview;
    private String itemUrl="http://www.xieast.com/api/banner.php";
    private List<NewsItem.ItemBean> itemData = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();
    private BGABanner viewpager;

    @Override
    protected void initData() {
        viewpager=(BGABanner)findViewById(R.id.viewpager);
        webview=(WebView)findViewById(R.id.webview);
        mTitle.add("第一页");
        mTitle.add("第二页");
        mTitle.add("第三页");
        mTitle.add("第四页");
        Intent intent = getIntent();
        String extra = intent.getStringExtra("url");
        webview.loadUrl(extra);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        doGet();
    }

    private void doGet() {
        new HelpAsync().get(itemUrl).result(new HelpAsync.HttpListener() {
            @Override
            public void success(String data) {
                Gson gson = new Gson();
                NewsItem newsItem = gson.fromJson(data, NewsItem.class);
                itemData = newsItem.getData();
                viewpager.setAdapter(new BGABanner.Adapter() {

                    @Override
                    public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                        ImageLoader.getInstance().displayImage(itemData.get(position).getImg(),(ImageView) itemView);
                    }
                });
                viewpager.setData(itemData,mTitle);

            }

            @Override
            public void fail() {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_show;
    }
    public  void  toast(String msg){

        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
