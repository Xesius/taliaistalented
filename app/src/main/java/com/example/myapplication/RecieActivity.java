package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class RecieActivity extends AppCompatActivity {
    //עמוד קבלה המציג מחיר סופי ולמי הקבלה(שם משתמש)

    //הצהרת משתנים
    TextView recipe, name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recie);

        //מקבל את המידע מהטרד בעזרת המפתח (שם) שהבאנו לו
        SharedPreferences sp = getApplicationContext().getSharedPreferences("details1", Context.MODE_PRIVATE);
        String fname = sp.getString("fname", "");

        //הכנסה מידע לטקסטויו מהאקסמל
        recipe = findViewById(R.id.reicpe);
        name = findViewById(R.id.recipename);
        price = findViewById(R.id.sum);

        //מקבל בעזרת פוטאקסטרה את המידע של המחיר הכללי שיצא
        Intent intent = getIntent();
        String totalprice = intent.getStringExtra("allpirce");


        //יצירת ראנדום
        Random rand = new Random();
        //יצירת מספר ראנדומלי בתווך 0-990
        int recieid = rand.nextInt(999);
        recipe.setText("קבלה מס " + String.valueOf(recieid) + " :");//מגידר את הטקסט לקבלה + מספר ראנדומלי שיצרנו שורה מעל
        name.setText("הוזמן ל " + fname); //מגדיר את הטקסט מחדש ליחד עם השם משתמש
        price.setText("סהכ " + totalprice + "₪");//מגדיר את הטקסט לקבל את הסכום הכללי מאהקסטרה
    }

    //פונקציה המעבירה חזרה לעמוד הראשי
    public void gobacktomain(View view) {
        Intent intent = new Intent(RecieActivity.this, MainActivity.class);
        startActivity(intent);
    }
}