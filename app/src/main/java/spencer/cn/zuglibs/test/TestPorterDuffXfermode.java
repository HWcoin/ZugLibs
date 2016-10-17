package spencer.cn.zuglibs.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/10/8.
 */

public class TestPorterDuffXfermode extends View {

    private Paint mWave;//绘制水纹
    private PorterDuffXfermode waveMode = new PorterDuffXfermode(PorterDuff.Mode.DST_OVER);

    //绘制圆
    private Paint mCircle;
    private Canvas mCircleCanvas;
    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;


    public void init(){
        mWave = new Paint();
        mWave.setColor(Color.parseColor("#33b5e5"));
        mCircle = new Paint();
        mCircle.setColor(Color.parseColor("#99cc00"));
    }

    public TestPorterDuffXfermode(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    public TestPorterDuffXfermode(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestPorterDuffXfermode(Context context) {
        super(context);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }

        setMeasuredDimension(widthSize, heightSize);

        mBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
        mCircleCanvas = new Canvas(mBitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("SOS", mCircle+"");
        mCircleCanvas.drawCircle(100, 100, 50, mCircle);

        mCircleCanvas.drawRect(100,100,200,200,mWave);

        canvas.drawBitmap(mBitmap,0,0,mCircle);


    }
}
