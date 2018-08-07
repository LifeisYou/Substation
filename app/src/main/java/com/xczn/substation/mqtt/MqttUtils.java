package com.xczn.substation.mqtt;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/**
 * @Author zhangxiao
 * @Date 2018/8/7 0007
 * @Comment Mqtt管理工具
 */
public class MqttUtils {

    private static MqttAndroidClient client = null;

    public static MqttConnectOptions getOptions(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        options.setUserName("user");
        options.setPassword("user".toCharArray());
        return options;
    }

    public static MqttAndroidClient getClient(){
        return MqttUtils.client;
    }
}
