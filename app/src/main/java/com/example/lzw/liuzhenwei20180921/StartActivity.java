package com.example.lzw.liuzhenwei20180921;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.base.BaseActivity;

public class StartActivity extends BaseActivity {


    private ViewPager view_vp2;
    private Button click;
    private int[] imgData = {
            R.drawable.splash01,
            R.drawable.splash02,
            R.drawable.splash03,
            R.drawable.splash04
    };
  private SharedPreferences sp;

    @Override
    protected void initData() {

        view_vp2 = (ViewPager) findViewById(R.id.view_vp2);
        click = (Button) findViewById(R.id.click);
        viewAdapter = new ViewAdapter();
        view_vp2.setAdapter(viewAdapter);
        sp=getSharedPreferences("config",MODE_PRIVATE);
        boolean islogin = sp.getBoolean("islogin", false);
        if (islogin){
            startActivity(new Intent(StartActivity.this, MainActivity.class));
            finish();

        }
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putBoolean("islogin",true).commit();
                startActivity(new Intent(StartActivity.this, MainActivity.class));
                finish();
            }
        });

        view_vp2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int currentItem = view_vp2.getCurrentItem();
                if (currentItem==3){

                    click.setVisibility(View.VISIBLE);

                }else {
                    click.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_start;
    }

    private ViewAdapter viewAdapter;


    private class ViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgData.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(StartActivity.this, R.layout.layout_img, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image1);
            imageView.setBackgroundResource(imgData[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
