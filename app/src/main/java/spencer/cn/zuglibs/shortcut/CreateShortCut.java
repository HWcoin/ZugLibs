package spencer.cn.zuglibs.shortcut;

import android.content.Intent;
import android.net.Uri;

/**
 * Created by Administrator on 2016/10/11.
 */

/**
 * 1、添加快捷方式
 * 2、默认第一个浏览器选项
 */
public class CreateShortCut {
    public void createShortCut(String url, int pic, String shortCutTitle, int icon){
        Intent intent = new Intent();
        // 指定动作名称
        intent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        // 指定快捷方式的图标，可以使用Bitmap，必须是可以修改的Bitmap，而且使用Intent.EXTRA_SHORTCUT_ICON
//        Parcelable icon = Intent.ShortcutIconResource.fromContext(this, pic);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        // 指定快捷方式的名称
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortCutTitle);
        intent.putExtra("duplicate", false);
        // 指定快捷图标激活哪个url
        Intent i = new Intent();
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));

        //默认第一个浏览器选项
//        List<ResolveInfo> resolveInfos = this.getPackageManager().queryIntentActivities(i, PackageManager.MATCH_DEFAULT_ONLY);
//        if (resolveInfos.size()>0)
//            i.setPackage(resolveInfos.get(0).activityInfo.packageName);

        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, i);
//        sendBroadcast(intent);发送广播、生成shortcut
    }

    /**
     * 判断是否已经有快捷方式
     */
//    public boolean hasShortCut(){
//        String url = "";
//        url = "content://"+getAuthorityFromPermission(this, "com.android.launcher.permission.READ_SETTINGS")+"/favorites?notify=true";
//
//        ContentResolver resolver = this.getContentResolver();
//        Cursor cursor = resolver.query(Uri.parse(url), null, "title=?",
//                new String[] {shortCutTitle}, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            cursor.close();
//            return true;
//        }
//
//        return false;
//    }
    //根据手机型号获取android系统桌面包名
//    static String getAuthorityFromPermission(Context context, String permission){
//        if (permission == null) return null;
//        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
//        if (packs != null) {
//            for (PackageInfo pack : packs) {
//                ProviderInfo[] providers = pack.providers;
//                if (providers != null) {
//                    for (ProviderInfo provider : providers) {
//                        if (permission.equals(provider.readPermission)) return provider.authority;
//                        if (permission.equals(provider.writePermission)) return provider.authority;
//                    }
//                }
//            }
//        }
//        return null;
//    }


}
