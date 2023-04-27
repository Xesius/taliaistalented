package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    //עמוד זה נותן גישה להתחבר

    //הצהרה על משתנים
    private EditText userName, password;//הצהרה על תיבת הטקטסט של שם משתמש וסיסמא
    SharedPreferences sp;//הצהרה על שרדפרפרנס שבעזרתו נשמור מידע
    Context context;//הצהרה על הקשר של כול מה שיש לאקטביתי
    int countUsers;//הצהרה מסוג אינט ששומר את את השם המשתמש והסיסמא שהוכנסו

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;//נשמור בקשר את המידע של האקטיבתי הנוכחי

        //הכנסת מידע לכול משתנה בעזרת הid שלהם בxml
        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);

        //ניצור כמו תיקייה שבה ישמר המידע בעזרת שרדפרפרנס
        sp = getSharedPreferences("details1", Context.MODE_PRIVATE);

    }

    //פונקציה אוןקלילק של כפתור ההתחברות
    public void loginClick(View view) {
        //נקבל מידע מהדאטאבייס
        userasherDataBase myUser = new userasherDataBase(loginActivity.this);
        //נכנס את המידע מהדאטאבייס לאינט שהצהרנו עליו מקודם על מנת לבדוק האם המשתמש קיים
        countUsers = myUser.checkForUser(userName.getText().toString(), password.getText().toString());

        //בדיקה האם המשתמש שהוכנסו קיים(אם שווה ל1 קיים ואם לא אז המשתמש איינו קיים)
        if (countUsers == 1) {
            //אם קיים משתמש נעבור לעמוד הבא שהוא העמוד הראשי
            Intent intent = new Intent(this, MainActivity.class);
            SharedPreferences.Editor editor = sp.edit();//נותנים גישה לערוך את התייקה שיצרנו לפני
            editor.putString("fname", userName.getText().toString());//נשמור בתייקיה שיצרנו לפני משתנה בשם fname שבו ישמר השם משתמש
            editor.commit();//פקודה זו למעשה תשמור את המידע ותנסכרן אותו לתיקייה
            startActivity(intent);//נעבור לעמוד שקבענו לפני
        } else {
            //אם המשתמש אינו קיים יקפוץ הודעה שמתמש אינו קיים
            Toast.makeText(context, "No such user!", Toast.LENGTH_SHORT).show();
        }
    }

    //פונקציה של הכפתור השני שתעביר להרשמה
    public void tosign(View view) {
        //מעבר עמוד להרמשה
        Intent intent = new Intent(this, signUpActivity.class);
        startActivity(intent);
    }
}