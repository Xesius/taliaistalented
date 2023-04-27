package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Helper {
    //קלאס שבעזרתו נמיר את התמונה לביטמאפ ובכך נשמור בדאטאבייס

    //ביט למערך
    public static byte[] bitmapToByteArray(Bitmap in) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        in.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        return bytes.toByteArray();

    }

    //מערך לביט
    public static Bitmap byteArrayToBitmap(byte[] bytes) {
        if (bytes != null) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } else {
            return null;
        }
    }
}