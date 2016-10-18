package spencer.cn.zuglibs;

import android.content.ContentValues;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class CameraPictureActivity extends AppCompatActivity implements SurfaceHolder.Callback,
                        Camera.PictureCallback, View.OnClickListener{
    Button take;
    SurfaceView surfaceView;
    SurfaceHolder holder;
    Camera camera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_picture);
//        请求横屏显示
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        take = (Button) findViewById(R.id.btn_camera_on);
        surfaceView = (SurfaceView) findViewById(R.id.sv_sruface_view);
        holder = surfaceView.getHolder();
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(this);

        /**
         * 拍照时SurfaceView必须设置
         */
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        surfaceView.setClickable(true);
        surfaceView.setOnClickListener(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();

        try {
            //设置Camera.Parameters应该在setPreviewDisplay之前:
            /**
             * 1、设置parameter参数
             * 2、旋转camera、parameter参数
             */
            Camera.Parameters parameters = camera.getParameters();
            if (this.getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE){
                parameters.set("orientation", "portrait");
                camera.setDisplayOrientation(90);
                parameters.setRotation(90);
            }else{
                parameters.set("orientation", "landscape");
                camera.setDisplayOrientation(0);
                parameters.setRotation(0);
            }
            camera.setParameters(parameters);

            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            camera.release();
            e.printStackTrace();
        }

        camera.startPreview();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
    }

    //点击拍照

    /**
     *
     * @param data
     * @param camera
     * 往MediaStore插入一条数据并返回一个URI，利用URI获得OutputStream，用于写入byte数据
     * camera调用takePicture时传入CallBack作为回调接口
     */
    @Override
    public void onPictureTaken(byte[] data, Camera camera){
        Log.e("SOS", "onpicturetaken");
        Uri imageFileUri = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new ContentValues());
        try {
            OutputStream out = getContentResolver().openOutputStream(imageFileUri);
            out.write(data);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    /**
     * 点击拍照
     * @param v
     */
    @Override
    public void onClick(View v) {
        camera.takePicture(null, null, null, this);
    }
    /**
     * 延时拍照实现：
     * 需要使用到Timer，因此也需要用到Handler
     * Runnable timerTask = new Runnable(){
     *     public void run(){
     *         camera.takePicture(null, null, null CameraPictureActivity.class);
     *     }
     * }
     * timerHandler.postDelayed(timerTask, 10000);
     */
}
