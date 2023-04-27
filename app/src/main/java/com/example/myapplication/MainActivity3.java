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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<food> foodsArrayList, desertArrayList, drinkArrayList;
    private food lastSelected, lastSelected2, lastSelected3;
    private ListView listView, listView2, listView3;
    foodAdapter adapter, adapter2, adapter3;
    private String username;
    private String title;
    private String dis, price;
    foodcartDB foodcartDB;
    int location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();//קריאה לפונקציה הדואגת לההצהרות


        //מקבל מידע מהדאטאבייס
        foodcartDB = new foodcartDB(this);

        //לולאה שתעבור על השמות של המוצרים ותוסיף בהתאם לליסט את המידע המתקבל
        for (int i = 0; i < Lfoodinfo.foodT.length; i++) {
            food food = new food(Lfoodinfo.bitmap(MainActivity3.this)[i], Lfoodinfo.foodT[i], Lfoodinfo.desc[i], Lfoodinfo.price[i]);
            foodsArrayList.add(food);
        }
        for (int i = 0; i < Ldesertinfo.foodT.length; i++) {
            food desert = new food(Ldesertinfo.bitmap(MainActivity3.this)[i], Ldesertinfo.foodT[i], Ldesertinfo.desc[i], Ldesertinfo.price[i]);
            desertArrayList.add(desert);
        }
        for (int i = 0; i < Ldrinkinfo.foodT.length; i++) {
            food drink = new food(Ldrinkinfo.bitmap(MainActivity3.this)[i], Ldrinkinfo.foodT[i], Ldrinkinfo.desc[i], Ldrinkinfo.price[i]);
            drinkArrayList.add(drink);
        }


        //יותר גישה לאדאפטר שיצרנו למנות
        adapter = new foodAdapter(this, foodsArrayList);
        adapter2 = new foodAdapter(this, desertArrayList);
        adapter3 = new foodAdapter(this, drinkArrayList);
        //על מנת שליסט יראה לנו את המנות הוא צריך דרך לגשת לממשק משתמש(ui) והדאפטר למעשה הוא גשר שמגשר בין המידע המתקבל מהממשק לליסט
        listView.setAdapter(adapter);//נכניס לליסט את האדאפטר שיגשר
        listView2.setAdapter(adapter2);//נכניס לליסט את האדאפטר שיגשר
        listView3.setAdapter(adapter3);//נכניס לליסט את האדאפטר שיגשר

    }


    //יוצר את התפריט
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //לפי הid שהבאנו בתפריט בודקים על איזה לוחצים ולפיו מבצעים את הפעולה
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_pg2)//מעבר לעמוד 2
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        }
        if (id == R.id.logout) {
            logoutuser();
        }
        if (id == R.id.cart) {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        }
        if (id == R.id.profile) {
            Intent intent = new Intent(this, userprofile.class);
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
                    Toast.makeText(MainActivity3.this, "לחצת לא", Toast.LENGTH_LONG).show();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        return true;
    }


    //מנתק את המשתמש ומחזיר אותו לעמוד הראשון של התחברות או הרשמה
    public void logoutuser() {
        SharedPreferences preferences = getSharedPreferences("details1", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, logorsign.class);
        startActivity(intent);
    }

    //פונקציה הדואגת לכול ההצהרות
    public void init() {
        lastSelected = new food();
        listView = (ListView) findViewById(R.id.foodview);
        listView2 = (ListView) findViewById(R.id.desertview);
        listView3 = (ListView) findViewById(R.id.drinkview);
        listView.setOnItemClickListener(this);
        listView2.setOnItemClickListener(this);
        listView3.setOnItemClickListener(this);
        foodsArrayList = new ArrayList<>();
        desertArrayList = new ArrayList<>();
        drinkArrayList = new ArrayList<>();
        username = getIntent().getStringExtra("name");//get the username
        title = getIntent().getStringExtra("title"); //get the information from the older page
        dis = getIntent().getStringExtra("desc"); //get the information from the older page
        price = getIntent().getStringExtra("price"); //get the information from the older page
    }

    //ניצור ליסטניר לליסט של האוכל
    public void listfood() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //על כול מנה (item) שנלחץ נקבל את מיקומו ובכך למעשה נקבל את המידע מהאדאפטר על אותה מנה ובכך נשמור אותם בפוטאקסטרה שנכול להציג אותם בעמוד אחרי
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                lastSelected = adapter.getItem(i);//קבלת מיקום בליסט
                //נקבל רפרנס לויו (לאותה מנה, לא יודע בדיוק איך להסביר את זה במושגים נורמלים)
                View view1 = view;
                foodcart foodl = new foodcart(lastSelected.getTitle(), lastSelected.getId(), lastSelected.getDes(), lastSelected.getPrice(), String.valueOf(location++));
                long test = foodcartDB.addFoodCartToDB(foodl);
                Toast.makeText(MainActivity3.this, lastSelected.getTitle() + " התוסף לעגלה", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ניצור ליסטניר לליסט של הקינוחים
    public void listdesert() {
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //על כול מנה (item) שנלחץ נקבל את מיקומו ובכך למעשה נקבל את המידע מהאדאפטר על אותה מנה ובכך נשמור אותם בפוטאקסטרה שנכול להציג אותם בעמוד אחרי
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                lastSelected2 = adapter2.getItem(i);//קבלת מיקום בליסט
                //נקבל רפרנס לויו (לאותה מנה, לא יודע בדיוק איך להסביר את זה במושגים נורמלים)
                View view1 = view;
                foodcart desertl = new foodcart(lastSelected2.getTitle(), lastSelected2.getId(), lastSelected2.getDes(), lastSelected2.getPrice(), String.valueOf(location++));
                long test2 = foodcartDB.addFoodCartToDB(desertl);
                Toast.makeText(MainActivity3.this, lastSelected2.getTitle() + " התוסף לעגלה", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ניצור ליסטניר לליסט של השתייה
    public void listdrink() {
        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //על כול מנה (item) שנלחץ נקבל את מיקומו ובכך למעשה נקבל את המידע מהאדאפטר על אותה מנה ובכך נשמור אותם בפוטאקסטרה שנכול להציג אותם בעמוד אחרי
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                lastSelected3 = adapter3.getItem(i);//קבלת מיקום בליסט
                //נקבל רפרנס לויו (לאותה מנה, לא יודע בדיוק איך להסביר את זה במושגים נורמלים)
                View view1 = view;
                foodcart drinkl = new foodcart(lastSelected3.getTitle(), lastSelected3.getId(), lastSelected3.getDes(), lastSelected3.getPrice(), String.valueOf(location++));
                long test3 = foodcartDB.addFoodCartToDB(drinkl);
                Toast.makeText(MainActivity3.this, lastSelected3.getTitle() + " התוסף לעגלה", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //פונקציה ללחיצה על כפתור של פריט בודק לפי איזה ליסט ומבצע אל הליסטנר שלו בהתאם
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if (adapterView.getId() == R.id.foodview) {
            listfood();
        } else if (adapterView.getId() == R.id.desertview) {
            listdesert();
        } else if (adapterView.getId() == R.id.drinkview) {
            listdrink();
        }
    }
}