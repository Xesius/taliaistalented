package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class foodcartDB extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "cart.db";
    private static final int DATABASE_VERSION = 8;
    private static final String TABLE_NAME = "foodcart";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BITMAP = "bitmap";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_INDEX = "location";

    private static final String[] allColumns = {COLUMN_ID, COLUMN_BITMAP, COLUMN_TITLE, COLUMN_DESCRIPTION, String.valueOf(COLUMN_PRICE), COLUMN_INDEX};


    private static final String CREATE_TABLE_FOODCART = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_INDEX + " TEXT,"
            + COLUMN_BITMAP + " BLOB,"
            + COLUMN_PRICE + " INTEGER,"
            + COLUMN_TITLE + " TEXT);";


    private SQLiteDatabase database;


    public foodcartDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase2) {
        sqLiteDatabase2.execSQL(CREATE_TABLE_FOODCART);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase2, int i, int i1) {
        sqLiteDatabase2.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase2);
    }


    public void open() {
        database = this.getWritableDatabase();
        Log.d("data", "database connection open");
    }

    @Override
    public synchronized void close() {
        super.close();

        //Log.d("data", "database close");
    }

    //מוסיף מוצר לדאטאבייס בהתאם לעצם פודקארט
    public long addFoodCartToDB(foodcart dish) {
        long id = -1;
        database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DESCRIPTION, dish.getDescription());
        contentValues.put(COLUMN_TITLE, dish.getTitle());
        contentValues.put(String.valueOf(COLUMN_PRICE), dish.getPrice());
        contentValues.put(COLUMN_INDEX, dish.getIndex());
        id = database.insert(TABLE_NAME, null, contentValues);
        database.close();
        return id;
    }


    //מקבל את כול הרשימה של הדאטבייס בשביל שנוכל להציג אותה בעמוד
    public ArrayList<foodcart> getAllFoodCart() {
        database = getReadableDatabase();
        ArrayList<foodcart> l = new ArrayList<foodcart>();
        Cursor cursor = database.query(
                TABLE_NAME,
                allColumns,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") byte[] bitArray = cursor.getBlob(cursor.getColumnIndex(COLUMN_BITMAP));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                @SuppressLint("Range") String des = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex(String.valueOf(COLUMN_PRICE)));
                @SuppressLint("Range") String index = cursor.getString(cursor.getColumnIndex(COLUMN_INDEX));
                //Bitmap bitmap = BitmapFactory.decodeByteArray(bitArray, 0, bitArray.length);
                foodcart c = new foodcart(title, id, des, price, index);
                l.add(c);
            }
        }
        return l;
    }


    //מוחק שורה מסויימת
    public void deleteFoodCart(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_INDEX + "='" + id + "'");
        Log.d("data", id);
        db.close();
    }

    //מוחק את כול השורות מהדאטבייס (כלומר מאפס אותו)
    public void deleAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME);

    }

    //מחשב את את המחיר הכללי של כול השורות(מוצרים)
    @SuppressLint("Range")
    public int getSumValue() {
        int sum = 0;
        database = this.getReadableDatabase();
        String sumQuery = String.format("SELECT SUM(%s) as Total FROM %s", this.COLUMN_PRICE, this.TABLE_NAME);
        Cursor cursor = database.rawQuery(sumQuery, null);
        if (cursor.moveToFirst())
            sum = cursor.getInt(cursor.getColumnIndex("Total"));
        return sum;
    }


}