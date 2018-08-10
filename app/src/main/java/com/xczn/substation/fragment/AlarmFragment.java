package com.xczn.substation.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.xczn.substation.R;
import com.xczn.substation.adapter.AlarmAdapter;
import com.xczn.substation.base.BaseBackFragment;
import com.xczn.substation.entity.AlarmBean;
import com.xczn.substation.listener.OnItemClickListener;
import com.xczn.substation.request.SubstationService;
import com.xczn.substation.util.NotificationUtils;
import com.xczn.substation.util.RetrofitUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author zhangxiao
 * @Date 2018/8/6 0006
 * @Comment
 */
public class AlarmFragment extends BaseBackFragment {

    private AlarmAdapter adapter;
    private List<AlarmBean> mAlarmBeans;
    private RecyclerView rv_main;
    private ItemTouchHelper itemTouchHelper;

    public static AlarmFragment newInstance(){
        return new AlarmFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mAlarmBeans = new ArrayList<>();
        initView(view);
        initItemHelper();
        initData();
        return view;
    }

    private void initView(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        initToolbarNav(toolbar);
        toolbar.setTitle("实时报警");
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

        adapter = new AlarmAdapter();
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh, Object obj) {
                AlarmBean alarm = (AlarmBean) obj;
                NotificationUtils.sendNotification(alarm.getEquip(), alarm.getMessage());
                //start(HisAlarmDetailFragment.newInstance(alarm.getMessage()));
                if (!alarm.isFirstOpen()) {
                    adapter.updateAlarm(alarm);
                }
            }
        });
        rv_main = view.findViewById(R.id.rv_main);
        rv_main.setLayoutManager(new LinearLayoutManager(_mActivity, LinearLayoutManager.VERTICAL, false));
        rv_main.addItemDecoration(new DividerItemDecoration(_mActivity, DividerItemDecoration.VERTICAL));
        rv_main.setAdapter(adapter);
    }

    @SuppressLint("CheckResult")
    private void initData() {
        RetrofitUtils.getRetrofit().create(SubstationService.class)
                .getAlarms()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<AlarmBean>>() {
                               @Override
                               public void accept(List<AlarmBean> alarmBeans) {
                                   mAlarmBeans = alarmBeans;
                                   adapter.setData(mAlarmBeans);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                            }
                        });
    }

    private void showHisAlarmType(String type) {
        List<AlarmBean> selectedAlarmBeans = new ArrayList<>();
        if (type.equals("all")) {
            selectedAlarmBeans = mAlarmBeans;
        } else {
            for (AlarmBean hisAlarmBean : mAlarmBeans) {
                if (hisAlarmBean.getType().equals(type)) {
                    selectedAlarmBeans.add(hisAlarmBean);
                }
            }
        }
        adapter.setData(selectedAlarmBeans);
    }

    private void initItemHelper() {
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            private RecyclerView.ViewHolder vh;

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder) {
                // 拖拽的标记，这里允许上下左右四个方向
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT |
                        ItemTouchHelper.RIGHT;
                // 滑动的标记，这里允许左右滑动
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            /*
                这个方法会在某个Item被拖动和移动的时候回调，这里我们用来播放动画，当viewHolder不为空时为选中状态
                否则为释放状态
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if (viewHolder != null) {
                    vh = viewHolder;
                    // pickUpAnimation(viewHolder.itemView);
                } else {
                    if (vh != null) {
                        // putDownAnimation(vh.itemView);
                    }
                }
            }


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                // 移动时更改列表中对应的位置并返回true
                Collections.swap(mAlarmBeans, viewHolder.getAdapterPosition(), target
                        .getAdapterPosition());
                return true;
            }

            /*
                当onMove返回true时调用
             */
            @Override
            public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int
                    fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
                super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
                // 移动完成后刷新列表
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target
                        .getAdapterPosition());
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // 将数据集中的数据移除
                mAlarmBeans.remove(viewHolder.getAdapterPosition());
                // 刷新列表
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        itemTouchHelper.attachToRecyclerView(rv_main);
    }

}
