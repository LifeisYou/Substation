package com.xczn.substation.mqtt;

import android.support.v4.app.FragmentActivity;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @Author zhangxiao
 * @Date 2018/4/28 0028
 * @Comment
 */

public class AlarmMqttCallback implements MqttCallback {

    private final FragmentActivity activity;
    private static int MessageID = 0;

    public AlarmMqttCallback(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void connectionLost(Throwable cause) {
//        Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show();
    }

    /**
     * 当消息推送来，调用
     * @param topic 主题
     * @param message 消息信息
     * @throws Exception 错误
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        sendNotification();

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }

    private void sendNotification(){
//        String channelID = "alarm_01";
//        String channelName ="Alarm";
//        Intent intent = new Intent(activity, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(activity, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            final Bitmap largeIcon = ((BitmapDrawable) activity.getResources().getDrawable(R.drawable.soft_image)).getBitmap();
//
//            NotificationChannel mChannel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
//            NotificationManager notificationManager = (NotificationManager) activity.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
//            if (notificationManager != null) {
//                notificationManager.createNotificationChannel(mChannel);
//
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(activity.getApplicationContext(), channelID);
//                builder.setSmallIcon(R.drawable.ic_menu_list_alarm)
//                        .setLargeIcon(largeIcon)
//                        .setTicker("您有新的报警信息")
//                        .setContentTitle(alarm.equipment)
//                        .setContentText(alarm.message)
//                        .setOngoing(false)    //正在进行的同时，不可删除
//                        .setWhen(System.currentTimeMillis())
//                        .setVisibility(VISIBILITY_PUBLIC)
//                        .setContentIntent(pendingIntent)            //
//                        .setAutoCancel(true)//设置点击消失
//                        .setPriority(PRIORITY_MAX)
//                        .setDefaults(Notification.DEFAULT_ALL);     //设置通知效果
//                builder.setChannelId(channelID);
//                notificationManager.notify(MessageID, builder.build());
//                MessageID++;
//
//                /**
//                 * 1. PRIORITY_MIN - 最低级别（-2）；
//                 * 2. PRIORITY_LOW - 较低级别（-1）；
//                 * 3. PRIORITY_DEFAULT - 默认级别（0）；
//                 * 4. PRIORITY_HIGH - 较高级别（1）；
//                 * 5. PRIORITY_MAX - 最高级别（2）；
//                 */
//            }
//        } else {
//
//            NotifyUtil.buildBigText(MessageID, R.drawable.ic_menu_list_alarm, alarm.equipment, alarm.message)
//                    .setHeadup()
//                    .setTicker("您有新的报警信息")
//                    .setBigIcon(R.drawable.soft_image)
//                    .setSmallIcon(R.drawable.ic_menu_list_alarm)
//                    .setContentIntent(pendingIntent)
//                    .setAction(true, true, true)
//                    //.setForgroundService()
//                    //.setFullScreenIntent(pendingIntent)
//                    .setLockScreenVisiablity(VISIBILITY_PUBLIC)
//                    .show();
//            MessageID++;
//        }
    }
}
