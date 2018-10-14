package com.example.lzw.liuzhenwei20180921.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtils {

    private SQLiteDatabase db;
    public  DBUtils(Context context){
        DBHelper dbHelper = new DBHelper(context,"news.db",null,1);
        db=dbHelper.getWritableDatabase();

    }
    //插入数据
    public  void  insert(String data){
        ContentValues values = new ContentValues();
        values.put("data",data);
        db.insert("news",null,values);

    }
    //查询数据
    public  String query(){
        Cursor cu = db.rawQuery("select data from news", null);
        String data="";
        while (cu.moveToNext()){
            data= cu.getString(cu.getColumnIndex("data"));
        }
        return  data;
    }
}
