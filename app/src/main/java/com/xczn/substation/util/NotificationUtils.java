package com.xczn.substation.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.xczn.substation.App;
import com.xczn.substation.MainActivity;
import com.xczn.substation.R;

import static android.content.Context.NOTIFICATION_SERVICE;
import static android.support.v4.app.NotificationCompat.CATEGORY_MESSAGE;
import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;
import static android.support.v4.app.NotificationCompat.PRIORITY_HIGH;
import static android.support.v4.app.NotificationCompat.VISIBILITY_PRIVATE;

/**
 * @Author zhangxiao
 * @Date 2018/8/9 0009
 * @Comment
 */
public class NotificationUtils {

    private static int messageId = 0;
    private static final String channelId = "my_notification_id";
    private static final String channelName ="my_notification_name";//用户看的
    private static final String ticker = "您有新的消息!";

    public static void sendNotification(String contentTitle, String contentText){
        Context context = App.getAppContext();

        //intent 点击事件，完成跳转动作
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //大图标
        Bitmap largeIcon = ((BitmapDrawable) context.getResources().getDrawable(R.drawable.soft_image)).getBitmap();

        //android-o上app发送的每个通知必须依附于一个channel.即，每个notification对象必须发送到指定的NotificationChannel.如果找不到channel，就会报错
        //通知渠道的id，渠道名字

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            NotificationCompat.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //创建通知渠道
                /**
                 * 重要等级我设为了IMPORTANCE_HIGH,屏幕弹窗。
                 * 公众号的订阅消息，这类消息不是那么重要，因此重要等级我设为了IMPORTANCE_DEFAULT，屏幕上不会弹出一条通知提醒了，只会在状态栏上显示一个小小的通知图标，
                 * 下拉展开通知栏，查看通知的详细信息。
                 * 重要等级还可以设置为IMPORTANCE_LOW、IMPORTANCE_MIN，分别对应了更低的通知重要程度。
                 */
                NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setShowBadge(true);
                manager.createNotificationChannel(notificationChannel);
                builder = new NotificationCompat.Builder(context, channelId);
            } else {
                builder = new NotificationCompat.Builder(context);
                builder.setPriority(PRIORITY_HIGH);   //等于IMPORTANCE_HIGH
            }
            builder.setSmallIcon(R.drawable.ic_menu_64)
                    .setLargeIcon(largeIcon)
                    .setAutoCancel(true)                    //设置点击消失
                    .setOngoing(false)                      //正在进行，不可删除
                    .setContentTitle(contentTitle)          //标题
                    .setContentText(contentText)            //正文
//                    .setNumber(1)
//                    .setContentIntent(pendingIntent)        //点击事件
                    .setCategory(CATEGORY_MESSAGE)        //通知类别
//                    .setFullScreenIntent(pendingIntent, true) //heads up, 8.0设置后不会自动消失
                    .setVisibility(VISIBILITY_PRIVATE)       //可见性，VISIBILITY_PUBLIC只有在没有锁屏时会显示通知 VISIBILITY_PRIVATE任何情况都会显示通知，
                    // VISIBILITY_SECRET在安全锁和没有锁屏的情况下显示通知
                    .setDefaults(Notification.DEFAULT_ALL)  //通知效果，呼吸灯、震动等
                    .setTicker(ticker)                      //消息提示，ticker
//                    .setChannelId(channelId)                //也可直接关联通知渠道
                    .setWhen(System.currentTimeMillis());   //设置显示时间，默认自带，可以不写

            manager.notify(messageId, builder.build());
            messageId++;
        }
    }
}
