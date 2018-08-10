package com.xczn.substation.mqtt;

import android.content.Context;

import com.xczn.substation.util.ToastUtils;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @Author zhangxiao
 * @Date 2018/4/28 0028
 * @Comment
 */

public class AlarmActionListener implements IMqttActionListener{

    private  Context context;
    private Action action;
    enum Action {
        /**
         * Connect Action
         **/
        CONNECT,
        /**
         * Disconnect Action
         **/
        DISCONNECT,
        /**
         * Subscribe Action
         **/
        SUBSCRIBE,
        /**
         * Publish Action
         **/
        PUBLISH
    }

    AlarmActionListener(Context context, Action action) {
        this.context = context;
        this.action = action;
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        switch (action) {
            case CONNECT:
                connect();
                break;
            case DISCONNECT:
                //disconnect();
                break;
            case SUBSCRIBE:
                subscribe();
                break;
            case PUBLISH:
                //publish();
                break;
        }
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        exception.printStackTrace();
    }

    private void connect() {
        ToastUtils.showShortToast(context, "Client Connecting ");
        //连接成功后，订阅报警
        try {
            MqttUtils.getInstance().subscribe("text", 2);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribe() {
        ToastUtils.showShortToast(context, "Client Subscribing ");
    }
}
