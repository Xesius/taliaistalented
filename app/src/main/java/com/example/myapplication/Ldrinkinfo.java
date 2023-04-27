package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Ldrinkinfo {

//קלאס שמכיל בתוכו את כול המידע של הקינוחים לאדאפטר

    //כותרת שם של המוצר
    public static String foodT[] =
            {
                    "קולה",
                    "קולה זירו",
                    "מים",
                    "פיוז טי",
                    "מים בטעמים אפרסק",
            };

    //תיאור של המוצר
    public static String desc[] =
            {
                    "בקבוק קוקה קולה קטן",
                    "בקבוק קוקה קולה זירו קטן",
                    "מים מי עדן 750מל",
                    "בקבוק פיוז טי קטן 500מל",
                    "מים בטעמים נביעות בטעם אפרסק עדין",
            };
    //מחיר של המוצר
    public static int price[] =
            {
                    7,
                    6,
                    5,
                    8,
                    6,
            };

    //התמונה של המוצר
    public static Bitmap[] bitmap(Context context) {

        Bitmap bitmap[] = {
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.cola),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.cockzero),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.water),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.fuzetea),
                BitmapFactory.decodeResource(context.getResources(), R.mipmap.watertam),
        };
        return bitmap;


    }


}
