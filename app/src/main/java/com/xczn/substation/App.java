package com.xczn.substation;

import android.app.Application;
import android.content.Context;

import com.xczn.substation.mqtt.MqttUtils;
import com.xczn.substation.util.SharedPreferencesUtils;

import org.eclipse.paho.client.mqttv3.MqttException;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;

/**
 * @Author zhangxiao
 * @Date 2018/8/3 0003
 * @Comment 全局Application
 */
public class App extends Application {

    private static Application mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        init();
    }

    private void init() {
        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /**
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
        SharedPreferencesUtils.init(this);

        try {
            MqttUtils.getInstance().connect(getAppContext(), "tcp://192.168.1.120:1883", "test");
//            MqttUtils.getInstance().subscribe("text", 2); //异步操作，需要等连接完成，才能订阅，需放在
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static Application getApplication() {
        return mApplication;
    }

    public static Context getAppContext() {
        return mApplication.getApplicationContext();
    }
}
