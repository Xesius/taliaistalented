package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUpActivity extends AppCompatActivity {
    //עמוד זה נותן גישה להרשם


    //הצהרת משתנים
    private Button signUp;
    private Context context;
    private EditText userName, password;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //הכנסת מידע לכול משתנה בעזרת הid שלהם בxml
        context = this;
        signUp = findViewById(R.id.signUpButton);
        userName = findViewById(R.id.newUsername);
        password = findViewById(R.id.newPassword);
    }

    //פונקציה של כפתור שתבצע רשמה בהתאם לנתונים שמכניסים
    public void signUpClick(View view) {
        //נקבל מידע מהדאטאבייס
        userasherDataBase myUser = new userasherDataBase(signUpActivity.this);
        //בודק האם התיבות טקסט לא ריקות אם לא יכניס ל איף הבא
        if ((userName.getText().length() > 0) && (password.getText().toString().length() > 0)) {
            //בודק בעזרת הדאטאבייס האם המשתמש קיים ואם לא יצור אותו
            if (myUser.doesExist(userName.getText().toString()) == 0) {
                //משתמש בפונקציות שיצרתי בדאטאבייס ומכניס את המידע
                myUser.insertUser(userName.getText().toString(), password.getText().toString());
                //מעבר עמוד להתחברות
                Intent intent = new Intent(this, loginActivity.class);
                intent.putExtra("id", (myUser.getId(userName.getText().toString(), password.getText().toString())).toString());
                startActivity(intent);
            }
            //אם המתשמש קיים יקפוץ הודעה שהמשתמש קיים
            else {
                Toast.makeText(context, "Username exists!", Toast.LENGTH_SHORT).show();
            }
        }
        //אם התיברות טקטסט ריקות יקפוץ הודעה שלא זוהה מידע כלומר לא הוכנס כלום
        else {
            Toast.makeText(context, "No data detected!", Toast.LENGTH_SHORT).show();
        }
    }

    //מעבר עמוד להתחברות
    public void tologin(View view) {
        //מעבר עמוד להתחברות
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }
}