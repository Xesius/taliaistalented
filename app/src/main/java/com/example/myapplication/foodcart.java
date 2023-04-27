package com.example.myapplication;

import android.graphics.Bitmap;

public class foodcart {
    //יצירת עצם פודקאארט המכיל בתוכו

    private Bitmap bitmap;//ביטמאפ לתמונה
    private String title;//כותרת למוצר
    private long id;//אידי למוצר
    private String des;//תיאור למוצר
    private int price;//מחיר למוצר

    //פעונה הנוצרה על מנת ליצור את העצם
    public foodcart() {

    }

    //פעולה בונה של העצם
    public foodcart(String title, long id, String des, int price, String index) {
        this.title = title;
        this.id = id;
        this.des = des;
        this.price = price;
        this.index = index;
    }

    //הצהרות על גאטרים וסאטרים של העצם

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }


    public foodcart(String title, long id) {
        this.title = title;
        this.id = id;
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    private String index;

}



