package com.xczn.substation.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.xczn.substation.R;
import com.xczn.substation.base.BaseBackFragment;

/**
 * Created by zhangxiao
 * Date on 2018/5/21.
 */
public class SubscribeFragment extends BaseBackFragment {

    public static SubscribeFragment newInstance() {
        return new SubscribeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subscribe, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle("订阅");
        initToolbarNav(toolbar);
    }
}