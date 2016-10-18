package spencer.cn.zuglibs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class GalleryActivity extends AppCompatActivity {
    Button pick;
    ImageView showGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        pick = (Button) findViewById(R.id.btn_pick_pic);
        showGallery = (ImageView) findViewById(R.id.iv_show_gallery);

        pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注意MediaStore.Images.Media.EXTERNAL_CONTENT_URI、MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                //以及Uri如何转变为InputStream
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 234);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri imageFileUri = data.getData();
            Log.e("sos", data.getAction()+":"+Intent.ACTION_PICK);
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri));
//                Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight());
                showGallery.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
