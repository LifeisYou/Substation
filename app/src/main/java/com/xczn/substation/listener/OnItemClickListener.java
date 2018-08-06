package com.xczn.substation.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Author zhangxiao
 * @Date 2018/8/3 0003
 * @Comment
 */
public interface OnItemClickListener {
    void onItemClick(int position, View view, RecyclerView.ViewHolder vh, Object obj);
}
