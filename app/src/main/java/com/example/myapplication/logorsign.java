package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class logorsign extends AppCompatActivity {
    //עמוד זה יופיע ישר אחרי הספלאש והוא נותן בחירה של התחברות או הרשמה


    //אינטנט של המוזיקה שבעזרתו נדליק ונכבה את המוזיקה
    Intent musicIntnet;

    AirplaneModeChangeReceiver airplaneModeChangeReceiver = new AirplaneModeChangeReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logorsign);

        //נכניס את המידע של העמוד של הסרויס של המוזיקה
        musicIntnet = new Intent(this, MyService.class);
        startService(musicIntnet);

    }

    //פונקציה של כפתור אוןקלילק שתעביר להתחברות
    public void tologin2(View view) {
        //מעבר עמוד להתחברות
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }

    //פונקציה של כפתור אוןקלילק שתעביר להרשמה
    public void tosign2(View view) {
        //מעבר עמוד להרשמה
        Intent intent = new Intent(this, signUpActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
         IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(airplaneModeChangeReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(airplaneModeChangeReceiver);
    }
}
