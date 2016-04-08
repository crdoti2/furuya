package com.example.r_n_010.test2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by R-N-010 on 2016/04/07.
 */
public class MySurfaceView implements SurfaceHolder.Callback, Runnable, View.OnClickListener {
    private Thread thread;
    private SurfaceHolder holder;
    private Bitmap image = null;
    private Bitmap bmp1 = null, bmp2 = null,bmp3 = null,bmp4 = null,bmp5 = null;
    private float r =0;
    private boolean spin1 = false;
    private boolean spinFinish1 = false;
    private boolean spin2 = false;
    private boolean spinFinish2 = false;
    private boolean spin3 = false;
    private boolean spinFinish3 = false;
    private int bmpX = 300, bmpY = 300;
    private int speed = 75;
    ArrayList<ImageData> arrayImageData = new ArrayList<ImageData>();
    LinkedList<Integer> DisplayOrder1 = new LinkedList<Integer>();
    LinkedList<Integer> DisplayOrder2 = new LinkedList<Integer>();
    LinkedList<Integer> DisplayOrder3 = new LinkedList<Integer>();


    private int count = 0;


    private float width, height;

    public MySurfaceView(Context context ,SurfaceView sv ){
        //super(context);

        holder = sv.getHolder();
        holder.addCallback(this);
        //holder.setFixedSize(getWidth(), getHeight());

        for (int i = 0; i < 5; i++){
            arrayImageData.add(new ImageData());
        }

        for(int i=0 ; i < arrayImageData.size() ; i++){
            DisplayOrder1.add(i);
            DisplayOrder2.add(i);
            DisplayOrder3.add(i);
        }

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon1);
        //bmp1 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(0).bmp1 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(2).bmp2 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(1).bmp3 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon2);
        //bmp2 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(1).bmp1 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(3).bmp2 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
       arrayImageData.get(0).bmp3 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon3);
        //bmp3 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(2).bmp1 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(0).bmp2 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(4).bmp3 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon4);
        //bmp4 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(3).bmp1 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(4).bmp2 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(3).bmp3 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);

        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon5);
        //bmp5 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(4).bmp1 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(1).bmp2 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);
        arrayImageData.get(2).bmp3 = Bitmap.createScaledBitmap(image, bmpX, bmpY, false);


        arrayImageData.get(0).display1 = true;
        arrayImageData.get(1).display1 = true;
        arrayImageData.get(2).display1 = true;
        arrayImageData.get(3).display1 = true;
        arrayImageData.get(4).display1 = false;

        arrayImageData.get(0).display2 = true;
        arrayImageData.get(1).display2 = true;
        arrayImageData.get(2).display2 = true;
        arrayImageData.get(3).display2 = true;
        arrayImageData.get(4).display2 = false;

        arrayImageData.get(0).display3 = true;
        arrayImageData.get(1).display3 = true;
        arrayImageData.get(2).display3 = true;
        arrayImageData.get(3).display3 = true;
        arrayImageData.get(4).display3 = false;

        arrayImageData.get(0).y1 = bmpY*2;
        arrayImageData.get(1).y1 = bmpY;
        arrayImageData.get(2).y1 = 0;
        arrayImageData.get(3).y1 = -bmpY;
        arrayImageData.get(4).y1 = -bmpY;

        arrayImageData.get(0).y2 = bmpY*2;
        arrayImageData.get(1).y2 = bmpY;
        arrayImageData.get(2).y2 = 0;
        arrayImageData.get(3).y2 = -bmpY;
        arrayImageData.get(4).y2 = -bmpY;

        arrayImageData.get(0).y3 = bmpY*2;
        arrayImageData.get(1).y3 = bmpY;
        arrayImageData.get(2).y3 = 0;
        arrayImageData.get(3).y3 = -bmpY;
        arrayImageData.get(4).y3 = -bmpY;





    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int f, int w, int h) {
        width = w;
        height = h;

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread=null;
    }

    @Override
    public void run() {
        while (thread != null) {


            doDraw(holder);
        }
    }

    private void doDraw(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        if (canvas != null){
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            canvas.drawColor(Color.GREEN);

            for(int i=0 ; i < arrayImageData.size() ; i++){
                ImageData ID = arrayImageData.get(i);

                if(ID.display1){
                    canvas.drawBitmap(arrayImageData.get(i).bmp1,ID.x1,ID.y1, paint);
                }
                if(ID.display2){
                    canvas.drawBitmap(arrayImageData.get(i).bmp2,ID.x2,ID.y2, paint);
                }
                if(ID.display3){
                    canvas.drawBitmap(arrayImageData.get(i).bmp3,ID.x3,ID.y3, paint);
                }
            }





            if (spin1) {
                for(int i=0 ; i < arrayImageData.size() ; i++){
                    ImageData ID = arrayImageData.get(i);

                    if(ID.display1){
                        //canvas.drawBitmap(arrayImageData.get(i).bmp,ID.x1 ,ID.y1 , paint);
                        ID.y1 = ID.y1 + speed;
                    }

                }

                r=r+1;

                int n1 = DisplayOrder1.get(0);//一番下の画像

                if(arrayImageData.get(n1).y1 == 900) {

                    arrayImageData.get(n1).display1 = false; //画面から隠れた画像を"非表示"にする
                    arrayImageData.get(n1).y1 = -bmpY; //画面から隠れた画像の座標を初期位置に戻す

                    arrayImageData.get(DisplayOrder1.get(4)).display1 = true; //待機している次の画像を"表示"にする

                    //順番を入れ替える
                    int a = DisplayOrder1.poll();
                    DisplayOrder1.add(a);

                }



            }


            if (spin2) {
                for(int i=0 ; i < arrayImageData.size() ; i++){
                    ImageData ID = arrayImageData.get(i);


                    if(ID.display2){
                        //canvas.drawBitmap(arrayImageData.get(i).bmp,ID.x1 ,ID.y1 , paint);
                        ID.y2 = ID.y2 + speed;
                    }

                }

                r=r+1;



                int n2 = DisplayOrder2.get(0);//一番下の画像

                if(arrayImageData.get(n2).y2 == 900) {

                    arrayImageData.get(n2).display2 = false; //画面から隠れた画像を"非表示"にする
                    arrayImageData.get(n2).y2 = -bmpY; //画面から隠れた画像の座標を初期位置に戻す

                    arrayImageData.get(DisplayOrder2.get(4)).display2 = true; //待機している次の画像を"表示"にする

                    //順番を入れ替える
                    int a = DisplayOrder2.poll();
                    DisplayOrder2.add(a);

                }




            }


            if (spin3) {
                for(int i=0 ; i < arrayImageData.size() ; i++){
                    ImageData ID = arrayImageData.get(i);


                    if(ID.display3){
                        //canvas.drawBitmap(arrayImageData.get(i).bmp,ID.x1 ,ID.y1 , paint);
                        ID.y3 = ID.y3 + speed;
                    }
                }

                r=r+1;





                int n3 = DisplayOrder3.get(0);//一番下の画像

                if(arrayImageData.get(n3).y3 == 900) {

                    arrayImageData.get(n3).display3 = false; //画面から隠れた画像を"非表示"にする
                    arrayImageData.get(n3).y3 = -bmpY; //画面から隠れた画像の座標を初期位置に戻す

                    arrayImageData.get(DisplayOrder3.get(4)).display3 = true; //待機している次の画像を"表示"にする

                    //順番を入れ替える
                    int a = DisplayOrder3.poll();
                    DisplayOrder3.add(a);

                }

            }



            if(spinFinish1){


                    for(int i=0 ; i < arrayImageData.size() ; i++){
                        ImageData ID = arrayImageData.get(i);

                        if(ID.display1){
                            canvas.drawBitmap(arrayImageData.get(i).bmp1,ID.x1,ID.y1, paint);
                            ID.y1 = ID.y1 + speed;
                        }

                    }

                    r=r+1;

/*
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
*/

                if(arrayImageData.get(DisplayOrder1.get(0)).y1 == 900) spinFinish1 = false;



                    int n = DisplayOrder1.get(0);//一番下の画像


                    if(arrayImageData.get(n).y1 >= 900) {

                        arrayImageData.get(n).display1 = false; //画面から隠れた画像を"非表示"にする
                        arrayImageData.get(n).y1 = -bmpY; //画面から隠れた画像の座標を初期位置に戻す

                        arrayImageData.get(DisplayOrder1.get(4)).display1 = true; //待機している次の画像を"表示"にする

                        //順番を入れ替える
                        int a = DisplayOrder1.poll();
                        DisplayOrder1.add(a);


                    }







            }



            if(spinFinish2) {


                for (int i = 0; i < arrayImageData.size(); i++) {
                    ImageData ID = arrayImageData.get(i);


                    if (ID.display2) {
                        canvas.drawBitmap(arrayImageData.get(i).bmp2, ID.x2, ID.y2, paint);
                        ID.y2 = ID.y2 + speed;
                    }

                }

                r = r + 1;

/*
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
*/


                if (arrayImageData.get(DisplayOrder2.get(0)).y2 == 900) spinFinish2 = false;




                int n2 = DisplayOrder2.get(0);//一番下の画像

                if (arrayImageData.get(n2).y2 == 900) {

                    arrayImageData.get(n2).display2 = false; //画面から隠れた画像を"非表示"にする
                    arrayImageData.get(n2).y2 = -bmpY; //画面から隠れた画像の座標を初期位置に戻す

                    arrayImageData.get(DisplayOrder2.get(4)).display2 = true; //待機している次の画像を"表示"にする

                    //順番を入れ替える
                    int a = DisplayOrder2.poll();
                    DisplayOrder2.add(a);

                }


            }



            if(spinFinish3) {


                for (int i = 0; i < arrayImageData.size(); i++) {
                    ImageData ID = arrayImageData.get(i);


                    if (ID.display3) {
                        canvas.drawBitmap(arrayImageData.get(i).bmp3, ID.x3, ID.y3, paint);
                        ID.y3 = ID.y3 + speed;
                    }
                }

                r = r + 1;

/*
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
*/


                if (arrayImageData.get(DisplayOrder3.get(0)).y3 == 900) spinFinish3 = false;





                int n3 = DisplayOrder3.get(0);//一番下の画像

                if (arrayImageData.get(n3).y3 == 900) {

                    arrayImageData.get(n3).display3 = false; //画面から隠れた画像を"非表示"にする
                    arrayImageData.get(n3).y3 = -bmpY; //画面から隠れた画像の座標を初期位置に戻す

                    arrayImageData.get(DisplayOrder3.get(4)).display3 = true; //待機している次の画像を"表示"にする

                    //順番を入れ替える
                    int a = DisplayOrder3.poll();
                    DisplayOrder3.add(a);

                }
            }



/*
            paint.setTextSize(200);
            String s = String.valueOf(DisplayOrder1.get(0));
            canvas.drawText(s, 100, 900, paint);
            String s2 = String.valueOf(DisplayOrder1.get(1) );
            canvas.drawText(s2, 200, 900, paint);
            String s3 = String.valueOf(DisplayOrder1.get(2) );
            canvas.drawText(s3, 300, 900, paint);
            String s4 = String.valueOf(DisplayOrder1.get(3) );
            canvas.drawText(s4, 400, 900, paint);
            String s5 = String.valueOf(DisplayOrder1.get(4) );
            canvas.drawText(s5, 500, 900, paint);
            String s6 = String.valueOf(DisplayOrder1.size() );
            canvas.drawText(s6, 600, 900, paint);

            String s7 = String.valueOf(arrayImageData.get(4).display1);
            canvas.drawText(s7, 700, 900, paint);
*/
            paint.setTextSize(200);
            String s = String.valueOf(width );
           // canvas.drawText(s, 100, 900, paint);
            //count++;
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent event){


        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button1) {
            spin1 = false;
            spinFinish1 = true;

        }else if (id == R.id.button2){
            spin2 = false;
            spinFinish2 = true;


        }else if (id == R.id.button3){

            spin3 = false;
            spinFinish3 = true;
        }else if (id == R.id.image){
            spin1 = true;
            spin2 = true;
            spin3 = true;

        }

    }
}

