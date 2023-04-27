package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ldesertinfo {

//קלאס שמכיל בתוכו את כול המידע של הקינוחים לאדאפטר

    //כותרת שם של המוצר
    public static String foodT[] =
            {
                    "במבה נוגט",
                    "קליק",
                    "מסטיק",
                    "סוכרייה",
            };

    //תיאור של המוצר
    public static String desc[] =
            {
                    "במבה במילוי נוגט",
                    "קליק קורנפלקס",
                    "מסטיק בטעמים שונים",
                    "סוכרייה על מקל בטעם תות",
            };
    //מחיר של המוצר
    public static int price[] =
            {
                    7,
                    8,
                    3,
                    2,
            };

    //התמונה של המוצר
    public static Bitmap[] bitmap(Context context) {

        Bitmap bitmap[] = {
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.bambanogat),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.klick),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.mastik),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.sicria),
        };
        return bitmap;


    }


}
