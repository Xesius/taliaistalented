package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Lfoodinfo {

//קלאס שמכיל בתוכו את כול המידע של האוכל לאדאפטר

    //כותרת שם של המוצר
    public static String foodT[] =
            {
                    "אשר גדול",
                    "אשר קטן",
                    "חביתה",
            };

    //תיאור של המוצר
    public static String desc[] =
            {
                    "כריך שניצל גדול בבגאט עם תוספת לבחירה",
                    "כריך שניצל קטן בלחמניה עם תוספת לבחירה",
                    "כריך חביתה גדול בבגאט עם תוספת לבחירה",
            };
    //מחיר של המוצר
    public static int price[] =
            {
                    17,
                    10,
                    17,
            };

    //התמונה של המוצר
    public static Bitmap[] bitmap(Context context) {

        Bitmap bitmap[] = {
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.shnizel),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.shnizel),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.omlet),
        };
        return bitmap;


    }


}
