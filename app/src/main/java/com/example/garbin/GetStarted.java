package com.example.garbin;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GetStarted extends AppCompatActivity {

    private TextView mRandomText;
    boolean DoublePressToExit = false;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        mRandomText = findViewById(R.id.random_text);

        String[] randomTexts = getResources().getStringArray(R.array.random_texts);

        int randomNumber = (int) (Math.random() * randomTexts.length);

        mRandomText.setText(randomTexts[randomNumber]);
    }

    public void goToSecondActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (DoublePressToExit) {
            finishAffinity();
            toast.cancel();
        } else {
            DoublePressToExit = true;
            toast = Toast.makeText(this,"Press Again To Exit GarBin", Toast.LENGTH_SHORT);
            toast.show();
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    DoublePressToExit = false;
                }
            },1500);
        }
    }
}