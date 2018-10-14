package com.example.lzw.liuzhenwei20180921.bean;

import java.util.List;

public class NewsItem {
    private List<ItemBean> data;

    public List<ItemBean> getData() {
        return data;
    }

    public void setData(List<ItemBean> data) {
        this.data = data;
    }
    public  class  ItemBean{
        private String img;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
