package com.example.r_n_010.test2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Button> arrayButton = new ArrayList<Button>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SurfaceView sv = (SurfaceView)findViewById( R.id.SV );
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
       // Button button4 = (Button) findViewById(R.id.button4);


        ImageView image = (ImageView)findViewById(R.id.image);
        image.setImageResource(R.drawable.icon6);




        MySurfaceView msv = new MySurfaceView( this, sv  );

        button1.setOnClickListener(msv);
        button2.setOnClickListener(msv);
        button3.setOnClickListener(msv);
        //button4.setOnClickListener(msv);

        image.setOnClickListener(msv);

        //MySurfaceView surfaceView = new MySurfaceView(this);
        //setContentView(surfaceView);
        //setContentView(new ImageTest(this));

    }
}
