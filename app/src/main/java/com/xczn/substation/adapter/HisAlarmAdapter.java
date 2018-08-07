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
public class HisAlarmAdapter extends RecyclerView.Adapter<HisAlarmAdapter.HisAlarmHolder> {

    private Context context;
    private List<HisAlarmBean> list = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public HisAlarmAdapter() {
    }

    public void setData(List<HisAlarmBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HisAlarmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_alarm, parent, false);

        final HisAlarmHolder holder = new HisAlarmHolder(view);
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
    public void onBindViewHolder(@NonNull HisAlarmHolder holder, int position) {
        Drawable nav_left= context.getResources().getDrawable(R.drawable.ic_alarm_resolve);
        nav_left.setBounds(0, 0, nav_left.getMinimumWidth(), nav_left.getMinimumHeight());
        holder.tv_his_alarm_equip.setCompoundDrawablePadding(DensityUtil.px2dip(context, 20));
        holder.tv_his_alarm_equip.setCompoundDrawables(nav_left, null, null, null);

        holder.tv_his_alarm_equip.setText(list.get(position).getEquip());
        holder.tv_his_alarm_message.setText(list.get(position).getMessage());
        holder.tv_his_alarm_time.setText(TimeUtils.getNewChatTime(list.get(position).getTime()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HisAlarmHolder extends RecyclerView.ViewHolder{
        private TextView tv_his_alarm_equip, tv_his_alarm_message, tv_his_alarm_time;

        HisAlarmHolder(View itemView) {
            super(itemView);
            tv_his_alarm_equip = itemView.findViewById(R.id.tv_alarm_equipment);
            tv_his_alarm_message = itemView.findViewById(R.id.tv_alarm_message);
            tv_his_alarm_time = itemView.findViewById(R.id.tv_alarm_time);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }
}
