package com.example.myapplication;

import android.graphics.Bitmap;

public class food {
    //יצירת עצם פוד המכיל בתוכו

    private Bitmap bitmap;//ביטמאפ לתמונה
    private String title;//כותרת למוצר
    private long id;//אידי למוצר
    private String des;//תיאור למוצר
    private int price;//מחיר למוצר


    //פעונה הנוצרה על מנת ליצור את העצם
    public food() {

    }

    //פעולה בונה של העצם
    public food(Bitmap bitmap, String title, String des, int price) {
        this.bitmap = bitmap;
        this.title = title;
        this.des = des;
        this.price = price;
    }

    //הצהרות על גאטרים וסאטרים של העצם

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


    public Bitmap getBitmap() {

        return bitmap;

    }

    public void setBitmap(Bitmap bitmap) {

        this.bitmap = bitmap;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return des;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDes() {
        this.des = des;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}



