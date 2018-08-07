package com.xczn.substation.entity;

import com.xczn.substation.R;
import com.xczn.substation.fragment.AboutFragment;
import com.xczn.substation.fragment.AlarmFragment;
import com.xczn.substation.fragment.HisAlarmFragment;
import com.xczn.substation.fragment.HisChartFragment;
import com.xczn.substation.fragment.ServerFragment;
import com.xczn.substation.fragment.SoftFragment;
import com.xczn.substation.fragment.SubscribeFragment;
import com.xczn.substation.fragment.SubstationFragment;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author zhangxiao
 * @Date 2018/8/6 0006
 * @Comment
 */
public class MenuBean {

    private String menuName;
    private int drawableId;
    private SupportFragment fragment;

    private MenuBean(String menuName, int drawableId, SupportFragment fragment) {
        this.menuName = menuName;
        this.drawableId = drawableId;
        this.fragment = fragment;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public SupportFragment getFragment() {
        return fragment;
    }

    public static List<MenuBean> getMenuList() {
        List<MenuBean> list = new ArrayList<>();
        list.add(new MenuBean("变电站", R.drawable.ic_menu_substation_64, SubstationFragment.newInstance()));
        list.add(new MenuBean("我的报警", R.drawable.ic_menu_alarm_64, AlarmFragment.newInstance()));
        list.add(new MenuBean("历史报警", R.drawable.ic_menu_alarm_64, HisAlarmFragment.newInstance()));
        list.add(new MenuBean("历史报表", R.drawable.ic_menu_chart_64, HisChartFragment.newInstance()));
        list.add(new MenuBean("订阅报警", R.drawable.ic_menu_subscribe_64, SubscribeFragment.newInstance()));
        list.add(new MenuBean("服务器", R.drawable.ic_menu_server_64, ServerFragment.newInstance()));
        list.add(new MenuBean("软件信息", R.drawable.ic_menu_soft_64, SoftFragment.newInstance()));
        list.add(new MenuBean("关于我们", R.drawable.ic_menu_about_64, AboutFragment.newInstance()));
        return list;
    }
}
