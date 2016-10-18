package spencer.cn.zuglibs;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity {

    private static final int CARMERA = 0x111;
    private static final int STORAGE = 0x112;

    Button normalNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取拍照权限、读取外存权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},CARMERA);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE);
        }

        normalNotification = (Button) findViewById(R.id.normal_notification);
        normalNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoteNotification();
            }
        });
    }
    //授予权限
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.e("SOS", "requestCode:"+requestCode);
        for (int i=0; i < permissions.length; i++){
            Log.e("SOS", "permissions["+i+"]:"+permissions[i]);
        }
        for (int i=0; i < grantResults.length; i++){
            Log.e("SOS", "grantResults["+i+"]:"+grantResults[i]);
        }
        switch (requestCode){
            case CARMERA:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    Log.e("SOS", "camera permission is granted");
                break;
            case STORAGE:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    Log.e("SOS", "storage permission is granted");
                break;
            default:
                return;
        }
    }
    //拍照
    public void onCapture(View view){
        Intent i = new Intent(this, TakePicture.class);
        startActivity(i);
    }
    //正常发送Notification,缺少contentView报错
    public void onNotification(){
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello world";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent i = new Intent(this, Notification1Activity.class);
        PendingIntent intent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = intent;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, notification);
    }
    //发送包含RemoteViews的Notification
    public void onRemoteNotification(){
        Notification notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
        notification.tickerText = "hello world";
        notification.when = System.currentTimeMillis();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        Intent i = new Intent(this, Notification1Activity.class);
        PendingIntent intent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews remoteView = new RemoteViews(getPackageName(), R.layout.remote_view_layout);
        remoteView.setTextViewText(R.id.textView, "hello remoteView");
        remoteView.setImageViewResource(R.id.iv_remote_icon, R.mipmap.ic_launcher);
        PendingIntent reIntent = PendingIntent.getActivity(this, 1, new Intent(this, Notification1Activity.class), PendingIntent.FLAG_UPDATE_CURRENT );
        remoteView.setOnClickPendingIntent(R.id.iv_remote_icon, reIntent);
        notification.contentView = remoteView;
        notification.contentIntent = intent;
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0x111, notification);
    }
    //Camera照相
    public void onCamera(View view){
        Intent intent = new Intent(this, CameraPictureActivity.class);
        startActivity(intent);
    }
    //Gallery
    public void onGallery(View view){
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);
    }
}
