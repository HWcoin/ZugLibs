package spencer.cn.zuglibs.permissions;

/**
 * Created by Administrator on 2016/10/11.
 */
//Android6.0以上危险权限需要代码请求，模拟在Activity中请求权限
class CheckPermision {

}
//    if (ContextCompat.checkSelfPermission(MainActivity,Manifest.permission.READ_CONTACTS)
//            != PackageManager.PERMISSION_GRANTED) {
//        // Should we show an explanation?
//        if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
//                Manifest.permission.READ_CONTACTS)) {
//            // Show an expanation to the user *asynchronously* -- don't block
//            // this thread waiting for the user's response! After the user
//            // sees the explanation, try again to request the permission.
//        } else {
//            // No explanation needed, we can request the permission.
//            ActivityCompat.requestPermissions(thisActivity,
//                    new String[]{Manifest.permission.READ_CONTACTS},
//                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//            // app-defined int constant. The callback method gets the
//            // result of the request.
//        }
//    }

//处理权限请求响应：当你的app请求权限，系统显示一个dialog给用户，当用户响应时，
// 系统调用onRequestPermissionResult()并传递请求码，
// 你的app必须重写这个方法并检查该权限是否已经授予。
//@Override
//public void onRequestPermissionsResult(int requestCode,
//        String permissions[], int[] grantResults) {
//        switch (requestCode) {
//        case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
//        // If request is cancelled, the result arrays are empty.
//        if (grantResults.length > 0
//        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//        // permission was granted, yay! Do the
//        // contacts-related task you need to do.
//
//        } else {
//
//        // permission denied, boo! Disable the
//        // functionality that depends on this permission.
//        }
//        return;
//        }
//
//        // other 'case' lines to check for other
//        // permissions this app might request
//        }
//        }




