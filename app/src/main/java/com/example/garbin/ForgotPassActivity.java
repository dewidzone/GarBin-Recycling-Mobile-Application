package com.example.garbin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassActivity extends AppCompatActivity {

    EditText username;
    Button passreset;

    DBhelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        username = (EditText) findViewById(R.id.unf);
        passreset = (Button) findViewById(R.id.reset);

        myDB = new DBhelper(this);

        passreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();

                Boolean checkuser = myDB.checkusername(user);
                if (checkuser == true) {

                    Intent intent = new Intent(getApplicationContext(),ResetPassActivity.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(ForgotPassActivity.this, "User Does Not Exists. ", Toast.LENGTH_SHORT).show();
                }
                if (user.equals("")) {
                    Toast.makeText(ForgotPassActivity.this, "Please Enter User Name To Reset.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}