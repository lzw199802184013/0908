package com.example.lzw.liuzhenwei20180921;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.BaseActivity;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.com.bwie.liuzhenwei.fragment.FiveFragment;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.com.bwie.liuzhenwei.fragment.FourFragment;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.com.bwie.liuzhenwei.fragment.OneFragment;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.com.bwie.liuzhenwei.fragment.ThreeFragment;
import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.com.bwie.liuzhenwei.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

/*
    作者：刘振伟
    日期：2018.09.21
    作用 ：继承baseActivity基类，实现展示列表功能等。。
 */
public class MainActivity extends BaseActivity {

    private TabLayout tablayout;
    private ViewPager view_vp;
    private List<Fragment> fragments = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

        view_vp=(ViewPager)findViewById(R.id.view_vp);
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        fragments.add(new ThreeFragment());
        fragments.add(new FourFragment());
        fragments.add(new FiveFragment());
        view_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });



    }


}
