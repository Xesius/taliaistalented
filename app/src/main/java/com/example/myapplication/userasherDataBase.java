package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

public class userasherDataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "APP37.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE01_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PASS = "password";
    private static final String COLUMN_IMAGE = "image";


    private SQLiteDatabase database;
    private final Context context;

    public userasherDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryUserTable = "CREATE TABLE IF NOT EXISTS " + TABLE01_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_IMAGE + " VARCHAR, " +
                COLUMN_PASS + " TEXT);";


        sqLiteDatabase.execSQL(queryUserTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE01_NAME);
        onCreate(sqLiteDatabase);
    }

    //מכניס משתמש חדש לדאטאבייס
    public void insertUser(String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PASS, password);
        long result = db.insert(TABLE01_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }


    //בודק האם המשתמש קיים במערכת (נוצר בשביל לבדוק את ההתחברות)
    public int checkForUser(String name, String userPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM " + TABLE01_NAME + " WHERE name = '" + name + "' AND password = '" + userPassword + "'";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor.getCount();
    }

    //בודק האם השם משתמש קיים(נוצר בשביל ההרשמה שלא היה משתמש בעל אותו שם)
    public int doesExist(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM " + TABLE01_NAME + " WHERE name = '" + name + "'";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor.getCount();
    }

    //מקבל את האידי של המשתמש
    @SuppressLint("Range")
    public String getId(String name, String userPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM " + TABLE01_NAME + " WHERE name = '" + name + "' AND password = '" + userPassword + "'";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }


        cursor.moveToFirst();
        int a = cursor.getInt(cursor.getColumnIndex("id"));

        return String.valueOf(a);
    }

    //מקבל את כול המידע מהדאטאבייס (לא בשימשוש כרגע)
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE01_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    //מקבל את האידי בעזרת השם (נוצר בשביל השמירה והשליפה של התמונה)
    @SuppressLint("Range")
    public String getIdbyName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM " + TABLE01_NAME + " WHERE name = '" + name + "'";

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }


        cursor.moveToFirst();
        int a = cursor.getInt(cursor.getColumnIndex("id"));

        return String.valueOf(a);
    }

    //מכניס את התמונה שצולמה לאותו למשתמש
    public long addPictoProf(Pic userProfilepic, String name) {
        long id = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String selection = "id = ?";
        Cursor cursor = null;
        String query = "SELECT id FROM " + TABLE01_NAME + " WHERE name = '" + name + "'";
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        cursor.moveToFirst();
        @SuppressLint("Range") int a = cursor.getInt(cursor.getColumnIndex("id"));

        contentValues.put(COLUMN_IMAGE, Helper.bitmapToByteArray(userProfilepic.getBitmap()));
        db.update(TABLE01_NAME, contentValues, selection, new String[]{String.valueOf(a)});  // number 1 is the _id here, update to variable for your code

        return id;
    }

    //מקבל את התמונה של אותו משתמש
    @SuppressLint("Range")
    public Bitmap getPic(long id) {

        byte[] rv = new byte[0];
        SQLiteDatabase db = this.getWritableDatabase();
        String whereclause = "id =?";
        String[] whereargs = new String[]{String.valueOf(id)};
        Cursor csr = db.query(TABLE01_NAME, null, whereclause, whereargs, null, null, null);
        if (csr.moveToFirst()) {
            rv = csr.getBlob(csr.getColumnIndex(COLUMN_IMAGE));
            return Helper.byteArrayToBitmap(rv);
        } else
            return null;

    }

}
