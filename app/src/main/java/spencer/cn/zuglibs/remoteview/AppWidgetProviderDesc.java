package spencer.cn.zuglibs.remoteview;

/**
 * Created by Administrator on 2016/10/13.
 */
public class AppWidgetProviderDesc {
}
/**
 * 步骤：
 * 1、定义界面res/layout下新建一个xml文件内容自定义，作为桌面小部件的布局，demo:widget.xml
 * 2、定义小部件的配置信息，在res/xml文件夹下，创建:app_widget_provider_info.xml
 * 3、定义小部件实现类、创建一个实现AppWidgetProvider的类，实质为广播。
 *      重写方法：
 *                 onEnable:当小部件第一次被添加时调用，多次添加调用一次
 *                 onUpdate:更新时调用，即updatePeriodMillis时间到时调用
 *                 onDeleted:每次删除时调用
 *                 onDisable:当最后一个小部件被删除时调用
 *                 onReceive:
 * 4、在Manifest.xml文件中定义这个广播：
 * <receiver
 *          android:name="packagename.classname">
 *          <meta-data
 *                      android:name="android.appwidget.provider"
 *                      android:resource="@xml/app_widget_provider_info.xml">
 *
 *          </meta-data>
 *          <intent-filter>
 *              <action android:name="actionname"/>
 *              <action android:name="android.appwidget.action.APPWIDGET_UPDATE"/>
 *          </intent-filter>
 * </receiver>
 *
 *
 */
