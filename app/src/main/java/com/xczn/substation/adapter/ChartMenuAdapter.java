package com.xczn.substation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xczn.substation.R;
import com.xczn.substation.entity.ChartMenuBean;
import com.xczn.substation.entity.HisAlarmBean;
import com.xczn.substation.listener.OnItemClickListener;
import com.xczn.substation.util.DensityUtil;
import com.xczn.substation.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangxiao
 * @Date 2018/8/3 0003
 * @Comment RecyclerView 适配器
 */
public class ChartMenuAdapter extends RecyclerView.Adapter<ChartMenuAdapter.ChartMenuHolder> {

    private Context context;
    private List<ChartMenuBean> list = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public ChartMenuAdapter() {
    }

    public void setData(List<ChartMenuBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChartMenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_chart_menu, parent, false);

        final ChartMenuHolder holder = new ChartMenuHolder(view);
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
    public void onBindViewHolder(@NonNull ChartMenuHolder holder, int position) {
        holder.tv_chart_menu_name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ChartMenuHolder extends RecyclerView.ViewHolder{
        private TextView tv_chart_menu_name;

        ChartMenuHolder(View itemView) {
            super(itemView);
            tv_chart_menu_name = itemView.findViewById(R.id.tv_chart_menu_name);
            }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }
}
