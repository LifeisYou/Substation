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
import android.view.View;
import android.view.ViewGroup;

import com.xczn.substation.R;
import com.xczn.substation.adapter.ChartMenuAdapter;
import com.xczn.substation.adapter.HisAlarmAdapter;
import com.xczn.substation.base.BaseBackFragment;
import com.xczn.substation.entity.ChartMenuBean;
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
 * @Comment
 */
public class HisChartFragment extends BaseBackFragment {

    private ChartMenuAdapter adapter;

    public static HisChartFragment newInstance(){
        return new HisChartFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_his_chart, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolbarNav(toolbar);
        toolbar.setTitle("历史报表");

        adapter = new ChartMenuAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh, Object obj) {
                ChartMenuBean chartMenu = (ChartMenuBean) obj;
                ToastUtils.showShortToast(_mActivity, String.valueOf(chartMenu.getId()) );
            }
        });
        RecyclerView rv_main = view.findViewById(R.id.rv_his_chart);
        rv_main.setLayoutManager(new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false));
        rv_main.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        rv_main.setAdapter(adapter);
    }

    @SuppressLint("CheckResult")
    private void initData() {
        RetrofitUtils.getRetrofit().create(SubstationService.class)
                .getChartMenus()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ChartMenuBean>>() {
                               @Override
                               public void accept(List<ChartMenuBean> chartMenuBeans) throws Exception {
                                   adapter.setData(chartMenuBeans);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                            }
                        });
    }
}
