package com.example.bmo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.icu.text.SimpleDateFormat;
import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import model.Construction;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ImageButton start = findViewById(R.id.start);
        ImageButton useless1 = findViewById(R.id.uselessebutton1);
        ImageButton useless2 = findViewById(R.id.uselessebutton2);
        ImageButton useless3 = findViewById(R.id.uselessebutton3);
        ImageButton useless4 = findViewById(R.id.uselessebutton4);
        ImageButton useless5 = findViewById(R.id.uselessebutton5);
        ImageButton useless6 = findViewById(R.id.uselessebutton6);


        MediaPlayer sonBip = MediaPlayer.create(this, R.raw.bip);

        start.setOnClickListener(view -> {
            sonBip.start();
            Intent intent = new Intent(MainActivity.this, Menu.class);
            startActivity(intent);
        } );







        // Useless button just sound
        useless1.setOnClickListener(view -> {
            sonBip.start();

        } );
        useless2.setOnClickListener(view -> {
            sonBip.start();
        } );
        useless6.setOnClickListener(view -> {
            sonBip.start();
        } );
        useless3.setOnClickListener(view -> {
            sonBip.start();
        } );
        useless4.setOnClickListener(view -> {
            sonBip.start();
        } );
        useless5.setOnClickListener(view -> {
            sonBip.start();
        } );

    }
}