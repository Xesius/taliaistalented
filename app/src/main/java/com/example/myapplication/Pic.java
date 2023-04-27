package com.example.myapplication;

import android.graphics.Bitmap;
//עצם של ביטמאפ שיעזור בשמירת התמונה בדאטאבייס

public class Pic {
    private Bitmap bitmap;//הצהרת משתנה ביטמאפ


    //פונקציה פונה
    public Pic(Bitmap bitmap) {

        this.bitmap = bitmap;
    }

    //גאטרים וסאטרים
    public Bitmap getBitmap() {

        return bitmap;

    }


    public void setBitmap(Bitmap bitmap) {

        this.bitmap = bitmap;

    }


}
