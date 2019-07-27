package com.example.hp.godface;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

public class MyView extends View {
    private SparseArray<Face> mfaces;
    private Bitmap mBitmap;

    public MyView(Context context , AttributeSet attrs ){
        super(context,attrs);
    }

    void setContent(Bitmap bitmap, SparseArray<Face>face)
    {
        mBitmap = bitmap;
        mfaces = face;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if((mBitmap != null) &&    (mfaces !=null))
        {
            double viewWidth = getWidth();
            double viewHeight = getHeight();

            double imageWidth = mBitmap.getWidth();
            double imageHeight = mBitmap.getHeight();

            double scale = Math.min(viewWidth/imageWidth , viewHeight/imageHeight);

            Rect destBounds = new Rect ( 0,0 , (int)(imageWidth*scale) , (int)(imageHeight*scale));
            canvas.drawBitmap(mBitmap , null ,destBounds , null);

            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStyle( Paint.Style.STROKE);
            paint.setStrokeWidth(4);

            for(int i=0;i<mfaces.size();i++)
            {
                Face face =mfaces.valueAt(i);
                int x = (int)face.getPosition().x;
                int y = (int)face.getPosition().y;
                int width = (int)(face.getWidth()*scale)+ x;
                int hieght = (int)(face.getHeight()*scale) + y;
                canvas.drawRect(x,y,width,hieght,paint);

                for(Landmark landmark :face.getLandmarks()){
                    int cx=(int) (landmark.getPosition().x *scale);
                    int cy=(int) (landmark.getPosition().y * scale);

                    canvas.drawCircle(cx , cy ,5 ,paint);


                }


            }

        }


    }
}
