package com.example.hp.godface;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.Landmark;

import java.io.InputStream;
import java.sql.DriverManager;

import static com.example.hp.godface.R.raw.photo;

public class MainActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    DatabaseHelper myDb;

    Button button;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        myDb = new DatabaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= (Button)findViewById(R.id.button3);

        InputStream inputStream = getResources().openRawResource(R.raw.photo);
        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        FaceDetector detector = new FaceDetector.Builder(getApplicationContext())
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setTrackingEnabled(false)
                .setMode(FaceDetector.FAST_MODE)
                .build();

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Face> face = detector.detect(frame);

        if (!detector.isOperational()) {
            Log.w(TAG, "face detector dependencies not there");
            Log.w(TAG, "hello");
            return;
        }
               MyView myView = (MyView) findViewById(R.id.my_view);
                myView.setContent(bitmap, face);

        //Fun();
        showvalues();

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      Intent newintent= new Intent(MainActivity.this,NewActivity.class);
                      startActivity(newintent);
                    }
                }
        );


    }

    private void showvalues() {
        System.out.println(Landmark.LEFT_EYE);
    }

    public void Fun() {
        boolean isUpdate = myDb.insertdata(leftEye,rightEye,nose,bottomMouth,leftMouth,rightMouth,leftEar,rightEar,leftCheek,rightCheek);
        if(isUpdate == true)
        {
            Toast.makeText(MainActivity.this,"Data Update" ,Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext())
                        {
                            buffer.append("LEFT_EYE"+ res.getString(1)+"\n");
                            buffer.append("RIGHT_EYE :"+ res.getString(2)+"\n");
                            buffer.append("NOSE_BASE :"+ res.getString(3)+"\n");
                            buffer.append("LEFT_EAR"+ res.getString(4)+"\n\n");
                            buffer.append("RIGHT_EAR"+ res.getString(5)+"\n\n");
                            buffer.append("NOSE"+ res.getString(6)+"\n\n");
                            buffer.append("LEFTCHEEK"+ res.getString(7)+"\n\n");
                            buffer.append("RIGHTCHEEK"+ res.getString(8)+"\n\n");
                            buffer.append("CHIN"+ res.getString(9)+"\n\n");
                        }
                                       }
                }
        );
    }



    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    double leftEye = Landmark.LEFT_EYE;
    double rightEye = Landmark.RIGHT_EYE;
    double nose = Landmark.NOSE_BASE;
    double bottomMouth = Landmark.BOTTOM_MOUTH;
    double leftMouth = Landmark.LEFT_MOUTH;
    double rightMouth = Landmark.RIGHT_MOUTH;
    double leftEar = Landmark.LEFT_EAR;
    double rightEar = Landmark.RIGHT_EAR;
    double leftCheek = Landmark.LEFT_CHEEK;
    double rightCheek = Landmark.RIGHT_CHEEK;

  // double diffeye = rightEye - leftEye;
    //double diffear = rightEar - leftEar;  //facewidth
    //double diffcheek = rightCheek - leftCheek;
    //double diffnoseTobottommounth = bottomMouth - nose;
//    double diffeyesTolips = bottomMouth - leftEye;  //faceheight;

/*
    double[] intArray = new double[10];
    intArray[0] = diffeye;
    intArray[1] = diffear;
    intArray[2] = diffcheek;
    intArray[3] = diffnoseTobottommounth;
    intArray[4] = diffeyesTolips;
    */

}
