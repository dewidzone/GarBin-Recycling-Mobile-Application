package com.example.garbin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    TextView forgotpass, donthaveacc;
    Button login;
    CheckBox showpassword;
    DBhelper myDB;
    boolean DoublePressToExit = false;
    Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        showpassword = findViewById(R.id.showpassword);

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        username = (EditText) findViewById(R.id.inputusernamelog);
        password = (EditText) findViewById(R.id.inputpassword);
        login = (Button) findViewById(R.id.loginbtn);
        forgotpass = (TextView) findViewById(R.id.forgotpass);
        donthaveacc = (TextView) findViewById(R.id.textviewsignup);

        myDB = new DBhelper(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter User Details.", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean results = myDB.checkusernamePassword(user, pass);
                    if (results == true) {
                        String enteredUsername = username.getText().toString();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("username", enteredUsername);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //Thejan this is the Register user action//
        donthaveacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //Thejan this is the Forgot pass action//
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassActivity.class);
                startActivity(intent);
            }
        });
    }

    //Thejan the below code can be used to hind keyboard when we tap on screen//
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
