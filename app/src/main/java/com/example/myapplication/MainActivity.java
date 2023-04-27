package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.guestname);

        //מקבל את המידע מהטרד בעזרת המפתח (שם) שהבאנו לו
        SharedPreferences sp = getApplicationContext().getSharedPreferences("details1", Context.MODE_PRIVATE);
        String fname = sp.getString("fname","");
        //מגדיר את הטקסט מחדש ליחד עם השם משתמש
        textView.setText("שלום " + fname + " !");

    }
    //יוצר את התפריט
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //לפי הid שהבאנו בתפריט בודקים על איזה לוחצים ולפיו מבצעים את הפעולה
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.item_pg2)//מעבר לעמוד 2
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        if (id == R.id.cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        if (id == R.id.logout)
        {
            logoutuser();
        }
        if (id == R.id.profile)
        {
            Intent intent = new Intent(this,userprofile.class);
            startActivity(intent);
        }

        //למעשה זה בודק האם לחצנו על כפתור היציאה אם כן זה יראה לנו תיבה שבה היה לנו בחירה אם נלחץ כן זה יצא ואם לא זה ישאר באפלקציה
        if (item.getItemId() == R.id.item_exit) //Exit app
        {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("יציאה");//כותרת
            builder.setIcon(R.mipmap.ic_launcher_round);//איקון שהיה בכותרת
            builder.setMessage("האם אתה בטוח שאתה רוצה לצאת?");
            builder.setPositiveButton("כן", new DialogInterface.OnClickListener() //ליסטניר לכפתור ה"חיובי"(בחירה ראשונה)
            {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();//סוגר את התיבה וסוגר את האפלקציה
                    finishAffinity();
                }
            });
            builder.setNegativeButton("לא", new DialogInterface.OnClickListener() //ליסטניר לכפתור ה"שלילי"(בחירה שני)
            {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();//סוגר את התיבה ולא עושה כלום למעשה משאיר אותנו באפלקציה
                    Toast.makeText(MainActivity.this, "לחצת לא", Toast.LENGTH_LONG).show();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        return true;
    }


    //מנתק את המשתמש ומחזיר אותו לעמוד הראשון של התחברות או הרשמה
    public void logoutuser()
    {
        SharedPreferences preferences = getSharedPreferences("details1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new  Intent(this,logorsign.class);
        startActivity(intent);
    }

    public void nextpage(View view)
    {
        Intent intent= new Intent(this,MainActivity2.class);
        startActivity (intent);
    }
}