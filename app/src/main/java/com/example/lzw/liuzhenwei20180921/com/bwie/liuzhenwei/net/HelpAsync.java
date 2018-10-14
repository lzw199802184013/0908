package com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.net;

import android.os.AsyncTask;

        import com.google.common.io.CharStreams;

        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

public class HelpAsync {

    public  HelpAsync(){

    }
    public HelpAsync get(String url){
        doHttp(url,"GET","");
        return  this;
    }

    private  void doHttp(String url,String method,String string){
        myAsyncTask = new MyAsyncTask(url, method, string);
        myAsyncTask.execute();
    }
    public  HelpAsync post(String url,String string){
        doHttp(url,"POST",string);
        return  this;
    }
    private  MyAsyncTask myAsyncTask;
    private  class MyAsyncTask extends AsyncTask<String,Integer,String>{
        private  String url,method,string;
        public  MyAsyncTask(String url,String method,String string){
            this.url= url;
            this.method=method;
            this.string=string;
        }
        @Override
        protected String doInBackground(String... strings) {
            String msg="";
            try {
                URL mUrl= new URL(url);
                HttpURLConnection connection=(HttpURLConnection) mUrl.openConnection();
                connection.setRequestMethod(method);
                connection.setConnectTimeout(5000);
                if ("POST".equals(method)){
                    PrintWriter writer = new PrintWriter(connection.getOutputStream());
                    writer.write(string);
                    writer.flush();
                    writer.close();
                }
                connection.connect();
                int code = connection.getResponseCode();
                if (code==HttpURLConnection.HTTP_OK){
                    String data=CharStreams.toString(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                    msg=data;
                }else {
                    msg="0";
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);
            if ("0".equals(string)){
                listener.fail();
            }else {
                listener.success(string);
            }
        }
    }
    private HttpListener listener;
    public  void  result(HttpListener listener){
        this.listener= listener;
    }
    public  interface  HttpListener{
        void  success(String data);
        void  fail();

    }
}
