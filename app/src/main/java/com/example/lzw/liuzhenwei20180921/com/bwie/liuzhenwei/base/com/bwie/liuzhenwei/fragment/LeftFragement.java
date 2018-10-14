package com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.com.bwie.liuzhenwei.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.lzw.liuzhenwei20180921.R;
import com.example.lzw.liuzhenwei20180921.ShowActivity;
import com.example.lzw.liuzhenwei20180921.adapter.MysAdapter;
import com.example.lzw.liuzhenwei20180921.bean.NewsBean;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.net.HelpAsync;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.net.NetworkUtils;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.net.SharedPreUtils;
import com.example.lzw.liuzhenwei20180921.database.DBUtils;
import com.example.xlistviewlib.XListView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LeftFragement extends Fragment {
    private XListView xlistview;
    private int page=1;
    private  String dataUrl="http://www.xieast.com/api/news/news.php?type=top&page="+page;
    private List<NewsBean.DataBean> beans = new ArrayList<>();
    private List<NewsBean.DataBean> beansAll = new ArrayList<>();
    private MysAdapter mysAdapter;
    private DBUtils dbUtils;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_left, container, false);
        xlistview=(XListView)view.findViewById(R.id.xlistview);
        dbUtils = new DBUtils(getActivity());
        return  view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mysAdapter = new MysAdapter(getActivity());
        xlistview.setAdapter(mysAdapter);
        xlistview.setPullRefreshEnable(true);
        xlistview.setPullLoadEnable(true);

        xlistview.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                    beansAll.clear();
                    page=1;
                    doGet(page);
                    xlistview.stopRefresh();
            }

            @Override
            public void onLoadMore() {
                page++;
              doGet(page);
                xlistview.stopLoadMore();
            }
        });
        doGet(page);
        xlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ShowActivity.class);
                intent.putExtra("url",beansAll.get(i-1).getUrl());
                startActivity(intent);
            }
        });


    }

    private void doGet(int page) {
        boolean connected = NetworkUtils.isConnected(getActivity());
        if (connected){
            new HelpAsync().get(dataUrl).result(new HelpAsync.HttpListener() {
                @Override
                public void success(String data) {
                    dbUtils.insert(data);
//                    SharedPreUtils.put(getActivity(),"dd",data);
                    Gson gson = new Gson();
                    NewsBean newsBean = gson.fromJson(data, NewsBean.class);
                    beans = newsBean.getData();
                    beansAll.addAll(beans);
                    mysAdapter.setList(beansAll);


                }

                @Override
                public void fail() {

                }
            });
        }else
        {
            String query = dbUtils.query();
//            String dd = SharedPreUtils.getString(getActivity(), "dd");
            Gson gson = new Gson();
            NewsBean newsBean = gson.fromJson(query, NewsBean.class);
            beans = newsBean.getData();
            beansAll.addAll(beans);
            mysAdapter.setList(beansAll);


        }

    }
    public  void  toast(String msg){

        Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
    }
}
