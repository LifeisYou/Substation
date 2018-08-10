package com.xczn.substation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xczn.substation.R;
import com.xczn.substation.adapter.DemoAdapter;
import com.xczn.substation.base.BaseBackFragment;
import com.xczn.substation.listener.OnItemClickListener;
import com.xczn.substation.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangxiao
 * @Date 2018/8/6 0006
 * @Comment
 */
public class DemoFragment extends BaseBackFragment {

    private DemoAdapter adapter;

    public static DemoFragment newInstance(){
        return new DemoFragment();
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
        initToolbarNav(toolbar);
        toolbar.inflateMenu(R.menu.main);

        adapter = new DemoAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh, Object obj) {
                ToastUtils.showShortToast(_mActivity, (String) obj);
            }
        });
        RecyclerView rv_main = view.findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(_mActivity));
        rv_main.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        rv_main.setAdapter(adapter);
    }
    private void initData() {
        List<String> list = new ArrayList<>();
        list.add("First");
        list.add("Second");
        list.add("Third");
        adapter.setData(list);
    }
}
