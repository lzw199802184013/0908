package com.example.lzw.liuzhenwei20180921.com.bwie.liuzhenwei.net;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.common.io.CharStreams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
public class Helper {
    private final int HTTP_SUCCESS = 100;
    private final int HTTP_FAIL = 101;
    private final String TAG = "Helper";

    public Helper() {
    }

    public Helper get(String url) {
        doHttp(0, url, "");
        return this;
    }


    private void doHttp(final int type, final String url, final String string) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL murl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) murl.openConnection();
                    String method = "GET";
                    if (type == 1) {
                        method = "POST";
                    }
                    connection.setRequestMethod(method);
                    connection.setConnectTimeout(5000);
                    if (type == 1) {
                        PrintWriter writer = new PrintWriter(connection.getOutputStream());
                        writer.write(string);
                        writer.flush();
                        writer.close();
                    }
                    connection.connect();
                    int code = connection.getResponseCode();
                    Message msg = Message.obtain();
                    if (code == HttpURLConnection.HTTP_OK) {
                        String data = CharStreams.toString(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                        msg.obj = data;
                        msg.what = HTTP_SUCCESS;
                    } else {
                        msg.what = HTTP_FAIL;
                    }
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HTTP_SUCCESS:
                    String data = (String) msg.obj;
                    listener.success(data);
                    break;
                case HTTP_FAIL:
                    listener.fail();
                    break;
            }
        }
    };

    public Helper post(String url, String string) {
        doHttp(1, url, string);
        return this;
    }

    private HttpListener listener;
    public void result(HttpListener listener) {

        this.listener = listener;
    }

    public interface HttpListener {
        void success(String data);

        void fail();
    }
}
