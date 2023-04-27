package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {


    //הצהרת משתנים
    private ListView listView;//הצהרה על ליסט
    private ArrayList<foodcart> dishArrayList;//הצהרה על רשימה של עצם פודקארט
    private foodcartDB foodDb;//הצהערה על הדאטאבייס של המנות
    private foodcart lastSelected;//הצהרה על עצם פודקראט
    TextView sum;//הצהרה על טקסטויו
    int allsum;//הצהרה על משתנה מסוג אינט
    foodcartAdapter adapter;//הצהרה על אדאטפר שהותאם אישית

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();//קריאה לפונקציה הדואגת לכול ההצהרות
        sum = findViewById(R.id.sumall);//הכנסה מידע לטקסטויו מהאקסמל

        //מקבל את כול המנות שנכנסו לדאטאבייס בעמוד הקודם
        dishArrayList = foodDb.getAllFoodCart();

        //יותר גישה לאדאפטר שיצרנו למנות
        adapter = new foodcartAdapter(this, dishArrayList);
        //על מנת שליסט יראה לנו את המנות הוא צריך דרך לגשת לממשק משתמש(ui) והדאפטר למעשה הוא גשר שמגשר בין המידע המתקבל מהממשק לליסט
        listView.setAdapter(adapter);//נכניס לליסט את האדאפטר שיגשר
        //ליסטניר לליסט
        listView.setOnItemLongClickListener(new AdapterView
                .OnItemLongClickListener() {
            @Override
            //פונקציה של לחיצה ארוכה על מוצר נלחץ ארוך יפתח דיאלוג שיאפשר למשתמש לבחור אם למחוק או לא את המוצר מהעגלה
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createDialog(view, position);
                return true;
            }
        });

        //מציג את המחיר הסופי של כול המוצרים הנבחרו ביחד
        sum.setText(String.valueOf(foodDb.getSumValue()) + "₪");
        //שומר במשתנה אינט את הסכום בשביל שנוכל להעביר אותו אחר כך לעמוד של הקבלה
        allsum = foodDb.getSumValue();
    }

    //פונקציה הדואגת לכול ההצהרות
    public void init() {
        lastSelected = new foodcart();
        foodDb = new foodcartDB(this);
        listView = findViewById(R.id.cartview);
        dishArrayList = new ArrayList<>();
    }

    //יוצר דיאלוג שאם המשתמש בוחר כן המוצר נמחק מהעגלה
    public void createDialog(View view, int i) {
        lastSelected = adapter.getItem(i);
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("בטוח אתה רוצה למחוק את" + lastSelected.getTitle() + "    ?");
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("כן", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                adapter.remove(i, CartActivity.this);
                adapter.notifyDataSetChanged();
                sum.setText(String.valueOf(foodDb.getSumValue()) + "₪");
                allsum = foodDb.getSumValue();
                Toast.makeText(getApplicationContext(), lastSelected.getTitle(), Toast.LENGTH_LONG).show();
            }
        });

        adb.setNegativeButton("לא", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = adb.create();
        alertDialog.show();

    }

    //מרענן את האדאפטר
    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    //פונקציה של קנייה
    public void buyall(View view) {
        //אם הסכום גדול מ0(כלומר יש מוצרים) יקנה
        if (allsum > 0) {
            foodDb.deleAll();//מוחק את כול הרשימה מהדאטאבייס
            adapter.notifyDataSetChanged();//מעדכן את האדאפטר
            //מעביר לעמוד של הקבלה ומעביר את הסכום הסופי
            Intent intent = new Intent(CartActivity.this, RecieActivity.class);
            intent.putExtra("allpirce", String.valueOf(allsum));//מעביר את הסכום הסופי
            startActivity(intent);
        } else
            Toast.makeText(CartActivity.this, "העגלה ריקה", Toast.LENGTH_SHORT).show();


    }
}