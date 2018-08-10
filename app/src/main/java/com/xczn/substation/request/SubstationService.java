package com.xczn.substation.request;

import com.xczn.substation.entity.AlarmBean;
import com.xczn.substation.entity.ChartMenuBean;
import com.xczn.substation.entity.HisAlarmBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @Author zhangxiao
 * @Date 2018/8/7 0007
 * @Comment 网络接口
 */
public interface SubstationService {
    @GET("hisalarm")
    Observable<List<HisAlarmBean>> getHisAlarms();

    @GET("hisalarm")
    Observable<List<AlarmBean>> getAlarms();

    @GET("chartmenus")
    Observable<List<ChartMenuBean>> getChartMenus();
}
