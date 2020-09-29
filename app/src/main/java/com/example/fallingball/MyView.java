package com.example.fallingball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

public class MyView extends SurfaceView implements SurfaceHolder.Callback,Runnable {
    private int circleRadius;
    private Paint circlePaint;
    private int xPos;
    private int yPos;
    private Context context;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Thread t;
    private boolean isFalling = false;

    public MyView(Context context) {
        super(context);
        this.context = context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        xPos = getWidth()/2;
        yPos = getHeight()/2;
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(getResources().getColor(R.color.dark_blue));
            circleRadius = 50;
            circlePaint = new Paint();
            circlePaint.setColor(Color.YELLOW);
            xPos = getWidth()/2;
            yPos = getHeight()/2;
            canvas.drawCircle(xPos, yPos, circleRadius, circlePaint);
        }
        else
        {
            Toast.makeText(context, "INVALID", Toast.LENGTH_SHORT).show();
        }
        if(canvas!=null)
            surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                moveBallDown();
                break;
            case MotionEvent.ACTION_UP:
                stopBall();
                break;
        }
        invalidate();
        return true;
    }

    public void stopBall() {
        stopThread();
    }


    public void moveBallDown() {
        startThread();
    }

    private void startThread() {
        isFalling = true;
        t = new Thread(this);
        t.start();
    }
    private void stopThread() {
        isFalling = false;
        try{
            t.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(isFalling)
        {
            if(surfaceHolder.getSurface().isValid()){
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(getResources().getColor(R.color.dark_blue));
                circleRadius = 50;
                circlePaint = new Paint();
                circlePaint.setColor(Color.YELLOW);
                canvas.drawCircle(xPos, yPos, circleRadius, circlePaint);
                yPos += 10;
                if(yPos == getHeight())
                    yPos = getHeight()/2;
            }
            else
            {
                Toast.makeText(context, "INVALID", Toast.LENGTH_SHORT).show();
            }
            try{
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                if(canvas!=null)
                {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


}
