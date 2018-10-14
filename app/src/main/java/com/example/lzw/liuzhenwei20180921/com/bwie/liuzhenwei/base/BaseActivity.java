package com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lzw.liuzhenwei20180921.R;

/*
    作者：刘振伟
    日期：20180921
    作用：	Activity基类封装

 */
public  abstract class BaseActivity extends AppCompatActivity {
    private LinearLayout linearlayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        linearlayout=(LinearLayout)findViewById(R.id.linearlayout);
        View view = View.inflate(this,getLayoutId(),null);
        linearlayout.addView(view);
        initData();
    }

    protected abstract void initData();

    public  abstract int getLayoutId();
}
