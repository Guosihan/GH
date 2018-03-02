package com.example.gsh.gh;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Method;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gsh on 2018/3/1.
 */

public class Modell implements Model {


    private final Person person;
    //    private final MainActivity view;
    private DataBean data;

    public Modell(Person person) {
        this.person =person;
    }

//    public Modell(MainActivity view) {
//        this.view =view;
//    }

    @Override
    public DataBean getData() {
        String url = "http://gank.io/api/data/Android/10/1";

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("ModellOkhttp", e + "");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String s = response.body().string();
                DataBean dataBean = gson.fromJson(s, DataBean.class);
                Log.i("TAG","3");
                if (dataBean != null) {
                    Log.i("TAG","2");
                    data = dataBean;

                    person.setRecycleViewData(dataBean);


                }
            }
        });

        return data;
    }


    @Override
    public void loadMore() {
        int i=1;
        i=i+1;
        String url = "http://gank.io/api/data/Android/10/"+i;

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("ModellOkhttp", e + "");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String s = response.body().string();
                DataBean dataBean = gson.fromJson(s, DataBean.class);
                Log.i("TAG","3");
                if (dataBean != null) {
                    Log.i("TAG","2");
                    data = dataBean;

                    person.setRecycleViewData(dataBean);


                }
            }
        });
    }

    @Override
    public void refre() {

    }

}
