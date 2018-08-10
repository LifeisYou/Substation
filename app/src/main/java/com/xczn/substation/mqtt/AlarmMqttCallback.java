package com.xczn.substation.mqtt;

import com.xczn.substation.util.NotificationUtils;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @Author zhangxiao
 * @Date 2018/4/28 0028
 * @Comment
 */

public class AlarmMqttCallback implements MqttCallback {

    //连接断开时的回调
    @Override
    public void connectionLost(Throwable cause) {
    }

    /**
     * 收到下推消息时的回调
     * @param topic 主题
     * @param message 消息信息
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        NotificationUtils.sendNotification(topic, message.toString());
    }
    //消息发送成功时的回调
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
