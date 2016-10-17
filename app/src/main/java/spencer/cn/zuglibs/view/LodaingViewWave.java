package spencer.cn.zuglibs.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/10/8.
 */

public class LodaingViewWave extends View {

    private Paint mWave;//绘制水纹
    private PorterDuffXfermode waveMode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    //绘制圆
    private Canvas mCircleCanvas;
    private Canvas mRectCanvas;
    private Bitmap dstBitmap;
    private Bitmap srcBitmap;
    private int mWidth;
    private int mHeight;

    Path p = new Path();
    int y = mHeight/2;//动态变化
    int offset = 20;

    public void init(Context context){
        mWave = new Paint();
        mWave.setColor(Color.parseColor("#33b5e5"));
        this.setLayerType(LAYER_TYPE_SOFTWARE, null);

        //不断刷新
        new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    postInvalidate();
                }
            }
        }.start();
    }

    public LodaingViewWave(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public LodaingViewWave(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LodaingViewWave(Context context) {
        super(context);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        }else{
            mWidth = 300;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }else {
            mHeight = 300;
        }

        setMeasuredDimension(widthSize, heightSize);

        dstBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mCircleCanvas = new Canvas(dstBitmap);
        mCircleCanvas.drawCircle(mWidth/2, mHeight/2, mWidth>mHeight?mHeight/2:mWidth/2, mWave);

        srcBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mRectCanvas = new Canvas(srcBitmap);
        mWave.setColor(Color.parseColor("#ff0000"));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, 300, 300, mWave, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(dstBitmap, 0, 0, mWave);

        y = mHeight/2;
        p.reset();
        p.moveTo(0, y);
        p.cubicTo(mWidth/3, y+offset, mWidth/3, y-offset, mWidth, y);
        offset = (offset + 10)%50;
        Log.e("SoS", offset+"");
        p.lineTo(mWidth, mHeight);
        p.lineTo(0, mHeight);
        p.lineTo(0, y);
        p.close();
        mRectCanvas.drawPath(p, mWave);

        mWave.setStyle(Paint.Style.FILL_AND_STROKE);
        mWave.setColor(Color.parseColor("#00ff00"));
        mWave.setXfermode(waveMode);

        canvas.drawBitmap(srcBitmap, 0, 0, mWave);

        mWave.setXfermode(null);

        canvas.restoreToCount(layerId);


    }
}
