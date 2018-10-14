package com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.com.bwie.liuzhenwei.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lzw.liuzhenwei20180921.R;

import java.util.ArrayList;
import java.util.List;

public class OneFragment extends Fragment {
    private TabLayout tablayout;
    private ViewPager view_vp1;
    private String [] mData={"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    private List<LeftFragement> fragmentList = new ArrayList<LeftFragement>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_one, container, false);
        tablayout=(TabLayout)view.findViewById(R.id.tablayout);
        view_vp1=(ViewPager)view.findViewById(R.id.view_vp1);
        for (int a=0;a<mData.length;a++){
            fragmentList.add(new LeftFragement());
    }

        return  view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
            view_vp1.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return mData.length;
                }

                @Override
                public CharSequence getPageTitle(int position) {
                    return mData[position];
                }
            });
            tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            tablayout.setupWithViewPager(view_vp1);
    }

}
