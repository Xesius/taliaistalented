package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class userprofile extends AppCompatActivity implements View.OnClickListener, SensorEventListener {
    //עמוד שבו מוצג הפרופיל שבו יש מידע על המשתמש


    //הצהרה על משתנים
    TextView tv, tempsens;
    ImageButton img;
    Bitmap bitmap;
    userasherDataBase userDB;
    Button btnsave, btncncl, btnapp, logoutbtn;
    SharedPreferences sp, sp2;
    private SensorManager sensormanager;
    private Sensor temperature;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        tv = findViewById(R.id.usernameid);
        img = (ImageButton) findViewById(R.id.userpic);
        btnapp = (Button) findViewById(R.id.btntoapp);
        logoutbtn = (Button) findViewById(R.id.btnlogout);
        btnsave = (Button) findViewById(R.id.btnsave);
        tempsens = (TextView) findViewById(R.id.tempsensor);
        logoutbtn.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnapp.setOnClickListener(this);
        img.setOnClickListener(this);
        bitmap = null;
        //מקבל מידע מתיקיית details1
        SharedPreferences sp = getApplicationContext().getSharedPreferences("details1", Context.MODE_PRIVATE);
        String fname = sp.getString("fname", "");
        //מציג את המידע המתקבל שהוא למעשה השם של המשתמש
        tv.setText("טוב לראותך " + fname);


        //מקבל מידע מהדאטאבייס
        userDB = new userasherDataBase(this);

        if ((userDB.getPic(Long.parseLong(userDB.getIdbyName(fname)))) != null) {
            img.setImageBitmap((userDB.getPic(Long.parseLong(userDB.getIdbyName(fname)))));
        }


        //גישה לחיישנים
        sensormanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        temperature = sensormanager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        //בודק האם הטלפון תומך בחיישן טמפרטורה אם כן מראה אם לא מקפיץ הודעה שהטלפון אינו תומך
        if (temperature != null)
            tempsens.setText("Phone Temp: " + temperature.getPower());
        else
            tempsens.setText("Sorry - your device doesn't have an ambient temperature sensor");


    }

    @Override
    //פונקציה של הכפתורים(צילום תמונה,חזרה,והתנתקות) בודק לפי אי די
    public void onClick(View v) {
        if (btnapp == v) {
            //אם חזרה סוגר את העמוד
            finish();
        }
        //שמירת תמונה
        if (btnsave == v) {
            SharedPreferences sp = getApplicationContext().getSharedPreferences("details1", Context.MODE_PRIVATE);
            String fname = sp.getString("fname", "");
            if (bitmap == null) {
                bitmap = (BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
            } else {
                img.setImageBitmap(bitmap);
            }
            Pic pic = new Pic(bitmap);
            if (pic.getBitmap() == null) {
                Toast.makeText(userprofile.this, "התוכן ריק(null)", Toast.LENGTH_SHORT).show();
            } else {
                long id = userDB.addPictoProf(pic, fname);
                Toast.makeText(userprofile.this, "Save data", Toast.LENGTH_SHORT).show();
            }
        }
        if (img == v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);
        }
        //התנתקות ומחיקה מידע מהשרדפרפרנס של השם משתמש
        if (logoutbtn == v) {
            SharedPreferences preferences = getSharedPreferences("details1", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this, logorsign.class);
            startActivity(intent);
        }
    }

    //המשך שמירת תמונה
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                if (bitmap != null) {
                    img.setImageBitmap(bitmap);

                }

            } else {
                img.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round));
            }
        }
    }

    // פונקציות של החיישן טמפרטורה
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, temperature, SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    @Override
    //פעולת האזנה
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != Sensor.TYPE_AMBIENT_TEMPERATURE) {
            return;
        }

        if (temperature != null) {
            tempsens.setText("Temp sensor: " + temperature.getPower());
        } else
            tempsens.setText("Sorry - your device doesn't have an ambient temperature sensor");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}