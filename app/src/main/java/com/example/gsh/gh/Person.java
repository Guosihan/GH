package com.example.gsh.gh;

import android.util.Log;

/**
 * Created by gsh on 2018/3/1.
 */

public class Person implements Per {
    private MainActivity view;
    private Modell model;

    public Person(MainActivity view) {
        this.view = view;
        this.model = new Modell(this);
    }

    @Override
    public void loadMore() {
        model.loadMore();
    }

    @Override
    public void refr() {
        model.refre();
    }

    @Override
    public void getDataFormService() {
        model.getData();

    }

    public void setRecycleViewData(final DataBean data) {

        Log.i("TAG", 1 + "");
        view.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setRecyclerViewData(data);
            }
        });
    }
}
