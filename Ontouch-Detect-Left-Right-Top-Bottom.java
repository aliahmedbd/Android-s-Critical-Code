package com.example.aliahmed.test1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Ali Ahmed on 11/30/2015.
 */
public class GameCanvas extends View{
    Paint paint;
    float x=0,y=0;
   float left=0;
    float top=0;
    float right=0;
    float bottom=0;
    boolean firstTime=true;
    int min_distance=100;
    private float downX, downY, upX, upY;
    protected void calculateNextPos(){
        y++;
       // left=left+1;
       // right=right+1;
    }
    protected void onDraw(Canvas canvas){


        if(firstTime){

            firstTime=false;
            x=canvas.getWidth()/2;
            y=canvas.getHeight()/2;

            left=getWidth()/3;
            top=getHeight()-30;
            right=300;
            bottom=getHeight();
        }
        calculateNextPos();
        canvas.drawARGB(255, 255, 255, 255);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, 25, paint);
        canvas.drawRect(left, top, right, bottom, paint);
        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       //y=y+50;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                downX=event.getX();
                downY=event.getY();
                return true;

            }
            case MotionEvent.ACTION_UP:{
                upX=event.getX();
                upY=event.getY();

                float deltaX=downX-upX;
                float deltaY=downY-upY;

                if(Math.abs(deltaX) > Math.abs(deltaY)){
                    if(Math.abs(deltaX) > min_distance) {
                        if (deltaX < 0) {
                            left=left+100;
                            right=right+100;
                            return true;
                        }
                        if (deltaX > 0) {
                            left=left-100;
                            right=right-100;
                            //Right to left
                            return true;

                        }
                    }
                    else{
                        return  false;
                    }
                }
                else{
                    if(Math.abs(deltaY) > min_distance) {
                        if (deltaY < 0) {
                            //top to bottom
                            return true;
                        }
                        if (deltaY > 0) {
                            //bottom to top
                            return true;

                        }
                    }
                    else{
                        return  false;
                    }
                }

            }

        }
        return super.onTouchEvent(event);
    }

    public GameCanvas(Context context) {
        super(context);
        paint=new Paint();

    }
}
