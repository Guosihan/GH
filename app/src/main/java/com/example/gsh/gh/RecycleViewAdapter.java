package com.example.gsh.gh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gsh on 2018/3/1.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {


    private final Context context;
    private final List<DataBean.ResultsBean> data;

    public RecycleViewAdapter(Context context, List<DataBean.ResultsBean> data) {
        this.context=context;
        this.data=data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recycle_item, parent,false);
        ViewHolder viewholder=new ViewHolder(inflate);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.ViewHolder holder, int position) {
        DataBean.ResultsBean resultsBean = data.get(position);
        if (resultsBean ==null){
            Log.i("recy data null","aaa");
        }else{
           Glide.with(context).load(resultsBean.getImages()!=null?resultsBean.getImages().get(0):R.mipmap.ic_launcher).into(holder.img);
            Log.i("TAG",resultsBean.getImages()+"");
            holder.tv_who.setText(resultsBean.getWho()+"");
            holder.tv_time.setText(resultsBean.getDesc());
        }

    }


    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_time;
        private TextView tv_who;
        private ImageView img;
        public ViewHolder(android.view.View itemView) {
            super(itemView);
            tv_time = itemView.findViewById(R.id.recy_data);
            tv_who=itemView.findViewById(R.id.recy_who);
            img=itemView.findViewById(R.id.recy_img);
        }
    }
}
