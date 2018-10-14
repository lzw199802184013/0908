package com.example.lzw.liuzhenwei20180921.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lzw.liuzhenwei20180921.R;
import com.example.lzw.liuzhenwei20180921.bean.NewsBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MysAdapter extends BaseAdapter {
    private Context context;
    private List<NewsBean.DataBean> beans = new ArrayList<>();
    private DisplayImageOptions options;

    public MysAdapter(Context context) {
        this.context = context;
        options = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (beans.get(position).getThumbnail_pic_s02()==null){
            return  0;
        }
        return 1;
    }



    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        ViewHolder viewHolder;
        ViewHolder1 viewHolder1;
        switch (type){
            case  0:
                if (view==null){
                    viewHolder = new ViewHolder();
                    view = View.inflate(context,R.layout.layout_item1,null);
                    viewHolder.title2=view.findViewById(R.id.title2);
                    viewHolder.content2 = view.findViewById(R.id.content2);
                    viewHolder.img = view.findViewById(R.id.img);
                    view.setTag(viewHolder);
                }else {
                    viewHolder= (ViewHolder) view.getTag();

                }
                NewsBean.DataBean bean = beans.get(i);
                viewHolder.title2.setText(bean.getTitle());
                viewHolder.content2.setText(bean.getDate());
                ImageLoader.getInstance().displayImage(bean.getThumbnail_pic_s(),viewHolder.img,options);
                break;

            case  1:
                if (view==null){
                    viewHolder1 = new ViewHolder1();
                    view = View.inflate(context,R.layout.layout_item2,null);
                    viewHolder1.title3=view.findViewById(R.id.title3);
                    viewHolder1.img1=view.findViewById(R.id.img1);
                    viewHolder1.img2=view.findViewById(R.id.img2);
                    viewHolder1.img3=view.findViewById(R.id.img3);

                    view.setTag(viewHolder1);
                }else {
                    viewHolder1= (ViewHolder1) view.getTag();

                }
                NewsBean.DataBean bean1 = beans.get(i);
                viewHolder1.title3.setText(bean1.getTitle());
                ImageLoader.getInstance().displayImage(bean1.getThumbnail_pic_s(),viewHolder1.img1,options);
                ImageLoader.getInstance().displayImage(bean1.getThumbnail_pic_s02(),viewHolder1.img2,options);
                ImageLoader.getInstance().displayImage(bean1.getThumbnail_pic_s03(),viewHolder1.img3,options);

                break;
        }


        return view;
    }

    public void setList(List<NewsBean.DataBean> list) {

        this.beans = list;
        notifyDataSetChanged();
    }

    class  ViewHolder{

        TextView title2,content2;
        ImageView img;
    }
    class  ViewHolder1{
        TextView title3;
        ImageView img1,img2,img3;

    }
}
