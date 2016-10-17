package spencer.cn.zuglibs.remoteview;

import android.app.Notification;
import android.content.Intent;

import spencer.cn.zuglibs.R;

/**
 * Created by Administrator on 2016/10/13.
 */

public class NotificationDemo {
    //常规通知栏
    public void sendNotification(){
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello world";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent i = new Intent();
//        PendingIntent intent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.contentIntent = intent;
//        NotificationManager manager = getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(1, notification);
    }

    //发送包含RemoteVew的通知
    public void sendRemoteViewNotification(){
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello world";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent i = new Intent();
//        PendingIntent intent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

//        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.remote_view_layout);
//        remoteView.setTextViewText(R.id.textView, "hello remoteView");
//        remoteView.setImageViewResource(R.id.iv_remote_icon, R.mipmap.ic_launcher);
//        PendingIntent reIntent = PendingIntent.getActivity(this, 1, new Intent(this, Notification1Activity.class), PendingIntent.FLAG_UPDATE_CURRENT );
//        remoteView.setOnClickPendingIntent(R.id.iv_remote_icon, reIntent);
//        notification.contentView = remoteView;
//        notification.contentIntent = intent;
//        NotificationManager manager = getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(1, notification);
    }
}
