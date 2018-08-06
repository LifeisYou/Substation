package com.xczn.substation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xczn.substation.R;
import com.xczn.substation.adapter.DemoAdapter;
import com.xczn.substation.adapter.MenuAdapter;
import com.xczn.substation.entity.MenuBean;
import com.xczn.substation.listener.OnItemClickListener;
import com.xczn.substation.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author zhangxiao
 * @Date 2018/8/3 0003
 * @Comment
 */
public class MainFragment extends SupportFragment {

    private MenuAdapter adapter;

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("我的变电站");
        toolbar.inflateMenu(R.menu.main);

        adapter = new MenuAdapter(_mActivity);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh, Object obj) {
                start(((MenuBean) obj).getFragment());
            }
        });
        RecyclerView rv_main = view.findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new GridLayoutManager(_mActivity, 3));
        //rv_main.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        rv_main.setAdapter(adapter);
    }
    private void initData() {
        adapter.setData(MenuBean.getMenuList());
    }
}
