package com.xczn.substation.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xczn.substation.R;
import com.xczn.substation.entity.MenuBean;
import com.xczn.substation.listener.OnItemClickListener;
import com.xczn.substation.util.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangxiao
 * @Date 2018/8/3 0003
 * @Comment RecyclerView 适配器
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.DemoHolder> {

    private LayoutInflater mInflater;
    private List<MenuBean> list = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private FragmentActivity activity;

    public MenuAdapter(FragmentActivity activity) {
        this.activity = activity;
        this.mInflater = LayoutInflater.from(activity);
    }

    public void setData(List<MenuBean> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DemoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_menu, parent, false);
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
        holder.tvMenuName.setText(list.get(position).getMenuName());
        Glide.with(activity).load(list.get(position).getDrawableId()).into(holder.ivMenuImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DemoHolder extends RecyclerView.ViewHolder{
        private TextView tvMenuName;
        private ImageView ivMenuImage;

        DemoHolder(View itemView) {
            super(itemView);
            tvMenuName = itemView.findViewById(R.id.tv_menu_name);
            ivMenuImage = itemView.findViewById(R.id.iv_menu_image);
        }
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }
}
