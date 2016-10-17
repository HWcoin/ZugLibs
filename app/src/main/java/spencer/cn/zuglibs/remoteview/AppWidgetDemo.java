package spencer.cn.zuglibs.remoteview;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import spencer.cn.zuglibs.R;

/**
 * Created by Administrator on 2016/10/13.
 */

public class AppWidgetDemo extends AppWidgetProvider {
    public static final String WIDGET_ACTION = "android.app.widget.provider.demo";
    public AppWidgetDemo(){
        super();
    }
    @Override
    public void onReceive(final Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.e("sos", "action:"+intent.getAction());
        if (intent.getAction().equals(WIDGET_ACTION)){
            new Thread(){
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            }.start();
        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

    }
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }


}
