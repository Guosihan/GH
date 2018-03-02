package com.example.gsh.gh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements View {
    private Modell model;
    private Person person;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private List<DataBean.ResultsBean> data;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        model=new Modell();
        person = new Person(this);


        setPer(person);
        initView();

    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.main_lv);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swlayout);
        Refr();

    }

    public  void Refr() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setPer(person);


            }
        });
    }

    @Override
    public void LoadMore() {
        person.loadMore();
    }

    @Override
    public void setPer(Person person) {

       person.getDataFormService();

    }

    public void setRecyclerViewData(DataBean bean) {
        Log.i("TAG",bean.getResults()+"");
        adapter = new RecycleViewAdapter(this, bean.getResults());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                //获取最后一个可见view的位置
                int lastItemPosition = linearManager.findLastVisibleItemPosition();
                if (lastItemPosition ==adapter.getItemCount()-1){
                    LoadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
