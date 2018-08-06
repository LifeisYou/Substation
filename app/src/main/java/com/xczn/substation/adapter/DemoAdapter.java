package com.xczn.substation.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xczn.substation.R;
import com.xczn.substation.listener.OnItemClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * @Author zhangxiao
 * @Date 2018/8/3 0003
 * @Comment RecyclerView 适配器
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoHolder> {

    private LayoutInflater mInflater;
    private List<String> list = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public DemoAdapter(FragmentActivity activity) {
        this.mInflater = LayoutInflater.from(activity);
    }

    public void setData(List<String> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DemoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_alarm, parent, false);
        final DemoHolder holder = new DemoHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    int position = holder.getAdapterPosition();
                    mOnItemClickListener.onItemClick(position, view, holder, list.get(position));
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DemoHolder holder, int position) {
        //holder.tv_main.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class DemoHolder extends RecyclerView.ViewHolder{
        //private TextView tv_main;

        DemoHolder(View itemView) {
            super(itemView);
            //tv_main = itemView.findViewById(R.id.tv_main);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }
}
