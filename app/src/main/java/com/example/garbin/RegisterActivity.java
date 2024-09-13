package com.example.garbin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, phone, password, confirmpass, city, address;
    Button signup;
    DBhelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.inputusernamereg);
        password = (EditText) findViewById(R.id.inputpassword);
        phone = (EditText) findViewById(R.id.inputphone);
        confirmpass = (EditText) findViewById(R.id.inputconpass);
        city = (EditText) findViewById(R.id.inputcity);
        address = (EditText) findViewById(R.id.inputaddress);

        signup = (Button) findViewById(R.id.signinbtn);

        myDB = new DBhelper(this);

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String phoneno = phone.getText().toString();
                String pass = password.getText().toString();
                String repass = confirmpass.getText().toString();
                String cityad = city.getText().toString();
                String addressno = address.getText().toString();

                if (user.equals("") || phoneno.equals("") || pass.equals("") || repass.equals("") || cityad.equals("") || addressno.equals("")) {

                    Toast.makeText(RegisterActivity.this, "Fill All The Fields.", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean usercheckresult = myDB.checkusernamePasswordPhonenoCitynoAddressno(user,pass,phoneno,cityad,addressno);
                        if (usercheckresult == false) {

                            boolean regResult = myDB.insertData(user, pass, phoneno, cityad, addressno);
                            if (regResult == true) {
                                Toast.makeText(RegisterActivity.this, "Registration Successful.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoadingScreen.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "User Already Exists.\n Please Log In.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password Does Not Match.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        TextView btn = findViewById(R.id.textviewlogin);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}