package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    //עמוד הספלאש

    //הצהרה של מתשתנים
    ImageView imageView, imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //מסתיר את הסרגל עבודה וגורם למסך מלא(איפה שהמניו)
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //הכנסת מידע לכול משתנה בעזרת הid שלהם בxml
        imageView = findViewById(R.id.bg);
        imageView2 = findViewById(R.id.bg2);

        //הקוד שבעזרתו הרקע מאחורה יזוז
        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);//הערכים התחלתים והסופים של האנמציה
        animator.setRepeatCount(ValueAnimator.INFINITE);//קובע שהאנמציה אינסופית ולכן לא תפסיק לעולם
        animator.setInterpolator(new LinearInterpolator());//יוצר אינטרפולטור חדש
        animator.setDuration(10000L);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final float progress = (float) animation.getAnimatedValue();//מקבל את המידע שהכנסו לפני
                final float width = imageView.getWidth();//מקבל את הרוחב של התמונה
                final float translationX = width * progress; //נחשב את המעבר של ציר האיקס בכך נכפיל את הרוחב בערך הנוכחי
                imageView.setTranslationX(translationX);//נבצע את האנמציה על התמונה הראשונה
                imageView2.setTranslationX(translationX - width);//נבצע את האנמציה על התמונה השנייה
                //למעשה האנמציה מתבצעת על שני התמונות בשביל ליצור הרגשה שזה תמונה אחת מתי שהם יזוזו

            }
        });
        animator.start();//מתחיל את האנמציה

        //פונקציה של דיללי שתתבצע לאחר איקס שניות
        new Handler().postDelayed(new Runnable() {
            @Override
            //לאחר שהדיללי נגמר מבצע את הפעולות
            public void run() {
                //מעבר עמוד להתחברות או הרשמה
                Intent intent = new Intent(splash.this, logorsign.class);
                startActivity(intent);
                finish();//סוגר את העמוד הנוכחי הספלאש
                //שימוש נוסף באנימציה שיצרתי על מנת ליצור חוויה יותר נעימה למשתמש
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        }, 1500);//1000ms = 1sec => 1300ms = 1.3sec  מספר השניות של הדילי
    }
}