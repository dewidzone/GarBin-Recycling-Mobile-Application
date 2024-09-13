package com.example.garbin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetPassActivity extends AppCompatActivity {

    private EditText pass, repass;
    private TextView username;
    private Button confirmreset;
    DBhelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        pass = (EditText) findViewById(R.id.pass_reset);
        repass = (EditText) findViewById(R.id.repass_reset);
        username = (TextView) findViewById(R.id.username_reset);
        confirmreset = (Button) findViewById(R.id.confirm_reset);
        myDB = new DBhelper(this);


        pass.addTextChangedListener(loginTextWatcher);
        repass.addTextChangedListener(loginTextWatcher);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        confirmreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if (password.equals(repassword)) {

                    Boolean checkpassupdate = myDB.updatepass(user, password);
                    if (checkpassupdate == true) {

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);

                        Toast.makeText(ResetPassActivity.this, "Password Updated Successfully.", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(ResetPassActivity.this, "Password Not Updated.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ResetPassActivity.this, "Passwords Does Not Match.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String usernameInput = pass.getText().toString().trim();
            String passwordInput = repass.getText().toString().trim();

            confirmreset.setEnabled(!usernameInput.isEmpty() && !passwordInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}