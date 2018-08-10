package com.xczn.substation.mqtt;

import android.content.Context;

import com.xczn.substation.App;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @Author zhangxiao
 * @Date 2018/8/7 0007
 * @Comment Mqtt管理工具
 */
public class MqttUtils {

    private static volatile MqttUtils instance;

    private MqttAndroidClient client = null;

    public static MqttUtils getInstance() {
        if (instance == null) {
            synchronized (MqttUtils.class) {
                if (instance == null) {
                    instance = new MqttUtils();
                }
            }
        }
        return instance;
    }

    private MqttConnectOptions getOptions(){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        options.setUserName("user");
        options.setPassword("user".toCharArray());
        return options;
    }

    /**
     * 推送订阅服务器主题
     * @param context 上下文
     * @param url 服务器ip+端口
     * @param clientId 主题id
     */
    public void connect(Context context, String url, String clientId) throws MqttException{
        client = new MqttAndroidClient(context, url, clientId);

        AlarmMqttCallback callback = new AlarmMqttCallback(); //订阅的回调函数，消息到来
        client.setCallback(callback);

        final AlarmActionListener listener = new AlarmActionListener(App.getAppContext(), AlarmActionListener.Action.CONNECT);
        if (client != null) {
            client.connect(getOptions(),null, listener);
        }
    }

    public void subscribe(String topic, int qos) throws MqttException{
        //final 的作用，使用全局的上下文，AppContext
        final AlarmActionListener listener = new AlarmActionListener(App.getAppContext(), AlarmActionListener.Action.SUBSCRIBE);
        if (client != null && client.isConnected()) {
           // client.subscribe(topic, qos);
            client.subscribe(topic, qos, null, listener);
        }
    }

    public void unsubscribeTopic(String clientId) throws MqttException {
        if (client != null) {
            client.unsubscribe(clientId);
        }
    }

}
