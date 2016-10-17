package spencer.cn.zuglibs;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class TakePicture extends AppCompatActivity {
    int requestCode = 0x114;
    private ImageView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);


        show = (ImageView) findViewById(R.id.iv_show);
    }
    public void onTake(View v){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri urlpath = null;
        //equals "file:///sdcard/mypic.jpg"
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED){
//            String url = Environment.getExternalStorageDirectory().getAbsolutePath()+"/mypic.jpg";
//            File urlfile = new File(url);
//            urlpath = Uri.fromFile(urlfile);

            //保存到Media.EXTERNAL_CONTENT_URI指定位置
           urlpath = getContentResolver().insert(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new ContentValues());
            //由此Media指定的可以通过如下方法来解析图片
//            BitmapFactory.Options options = null;
//            try {
//                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(urlpath), null, options);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
        }

        i.putExtra(MediaStore.EXTRA_OUTPUT, urlpath);


        startActivityForResult(i, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Bundle b = data.getExtras();
            Bitmap bitmap = (Bitmap) b.get("data");

            show.setImageBitmap(bitmap);
        }
    }
}
