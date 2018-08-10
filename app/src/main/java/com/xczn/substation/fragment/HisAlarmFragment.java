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

import com.xczn.substation.R;
import com.xczn.substation.adapter.HisAlarmAdapter;
import com.xczn.substation.base.BaseBackFragment;
import com.xczn.substation.entity.HisAlarmBean;
import com.xczn.substation.listener.OnItemClickListener;
import com.xczn.substation.request.SubstationService;
import com.xczn.substation.util.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author zhangxiao
 * @Date 2018/8/6 0006
 * @Comment
 */
public class HisAlarmFragment extends BaseBackFragment {

    private HisAlarmAdapter adapter;
    private List<HisAlarmBean> mHisAlarmBeans;

    public static HisAlarmFragment newInstance(){
        return new HisAlarmFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        //hi
        mHisAlarmBeans = new ArrayList<>();
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolbarNav(toolbar);
        toolbar.setTitle("历史报警");
        toolbar.inflateMenu(R.menu.menu_type_alarm);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.type_all://通知
                        showHisAlarmType("all");
                        break;
                    case R.id.type_notify://通知
                        showHisAlarmType("notify");
                        break;
                    case R.id.type_general://一般
                        showHisAlarmType("general");
                        break;
                    case R.id.type_abnormal://异常
                        showHisAlarmType("abnormal");
                        break;
                    case R.id.type_fault://错误
                        showHisAlarmType("fault");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        adapter = new HisAlarmAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh, Object obj) {
                HisAlarmBean hiaAlarm = (HisAlarmBean) obj;
                start(HisAlarmDetailFragment.newInstance(hiaAlarm.getMessage()));
            }
        });
        RecyclerView rv_main = view.findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false));
        rv_main.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        rv_main.setAdapter(adapter);
    }

    @SuppressLint("CheckResult")
    private void initData() {
        RetrofitUtils.getRetrofit().create(SubstationService.class)
                .getHisAlarms()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<HisAlarmBean>>() {
                               @Override
                               public void accept(List<HisAlarmBean> hisAlarmBeans) {
                                   mHisAlarmBeans = hisAlarmBeans;
                                   adapter.setData(mHisAlarmBeans);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                            }
                        });
    }

    private void showHisAlarmType(String type) {
        List<HisAlarmBean> selectedHisAlarmBeans = new ArrayList<>();
        if (type.equals("all")) {
            selectedHisAlarmBeans = mHisAlarmBeans;
        } else {
            for (HisAlarmBean hisAlarmBean : mHisAlarmBeans) {
                if (hisAlarmBean.getType().equals(type)) {
                    selectedHisAlarmBeans.add(hisAlarmBean);
                }
            }
        }
        adapter.setData(selectedHisAlarmBeans);
    }
}
