package com.example.fallingball;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private MyView myView;
    private FrameLayout frameLayout;
    private LinearLayout linearLayout;
    private Button start,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new MyView(this));
        frameLayout = new FrameLayout(this);
        myView = new MyView(this);
        linearLayout = new LinearLayout(this);

        start = new Button(this);
        start.setText("Start falling");
        int id1 = 123;
        start.setId(id1);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               myView.moveBallDown();
            }
        });

        stop = new Button(this);
        stop.setText("Stop falling");
        int id2 = 124;
        stop.setId(id2);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.stopBall();
            }
        });

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.addView(start);
        linearLayout.addView(stop);

        frameLayout.addView(myView);
        frameLayout.addView(linearLayout);
        setContentView(frameLayout);
    }


}