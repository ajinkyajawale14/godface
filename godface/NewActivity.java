package com.example.hp.godface;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import java.lang.Object;





public class NewActivity extends Activity {

    Landmark landmark;
    Face face;
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity);

    }

    int tv10 = 10;



/*
    public int method() {
        for (Landmark landmark : face.getLandmarks()) {
            int cx = (int) (landmark.getPosition().x);
            int cy = (int) (landmark.getPosition().y);
            return cx;
            return cy;

        }
    }
    */
}




