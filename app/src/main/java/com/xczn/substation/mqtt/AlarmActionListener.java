package com.xczn.substation.mqtt;

import android.content.Context;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @Author zhangxiao
 * @Date 2018/4/28 0028
 * @Comment
 */

public class AlarmActionListener implements IMqttActionListener{

    private final Context context;
    private final MqttAndroidClient client;

    public AlarmActionListener(Context context, MqttAndroidClient client) {
        this.context = context;
        this.client = client;
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        subscribeToTopic();
        try {
            client.subscribe("Alarm", 2);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        exception.printStackTrace();
    }

    public void subscribeToTopic(){
        try {
            client.subscribe(client.getClientId(), 2, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
//                    addToHistory("Subscribed!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                    addToHistory("Failed to subscribe");
                }
            });

        } catch (MqttException ex){
            System.err.println("Exception whilst subscribing");
            ex.printStackTrace();
        }
    }
}
