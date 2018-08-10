package com.xczn.substation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xczn.substation.R;
import com.xczn.substation.adapter.HisAlarmAdapter;
import com.xczn.substation.base.BaseBackFragment;
import com.xczn.substation.entity.HisAlarmBean;
import com.xczn.substation.listener.OnItemClickListener;
import com.xczn.substation.request.SubstationService;
import com.xczn.substation.util.RetrofitUtils;
import com.xczn.substation.util.ToastUtils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author zhangxiao
 * @Date 2018/8/6 0006
 * @Comment 历史报警信息详情
 */
public class HisAlarmDetailFragment extends BaseBackFragment {

    private String str;

    public static HisAlarmDetailFragment newInstance(String str){
        HisAlarmDetailFragment fragment = new HisAlarmDetailFragment();
        Bundle args = new Bundle();
        args.putString("str", str);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            str = getArguments().getString("str");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_his_alarm, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolbarNav(toolbar);
        toolbar.setTitle("历史报警详情");

        TextView tvHisAlarmMsg = view.findViewById(R.id.tv_his_alarm_message);
        tvHisAlarmMsg.setText(str);
    }

    private void initData() {
    }

}
