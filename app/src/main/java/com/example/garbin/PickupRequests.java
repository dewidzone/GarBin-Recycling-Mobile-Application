package com.example.garbin;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class PickupRequests extends AppCompatActivity {

    private TextView addressTextView;
    private ImageButton btnPlastic;
    private ImageButton btnPaper;
    private ImageButton btnOrganic;
    private TextView txtSelectedOption;
    private Button btnSendPickupRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_requests);

        addressTextView = findViewById(R.id.address_textview);
        btnPlastic = findViewById(R.id.btnPlastic);
        btnPaper = findViewById(R.id.btnPaper);
        btnOrganic = findViewById(R.id.btnOrganic);
        txtSelectedOption = findViewById(R.id.txtSelectedOption);
        btnSendPickupRequest = findViewById(R.id.btnSendPickupRequest);
        Intent intent = getIntent();
        double latitude = intent.getDoubleExtra("latitude", 0);
        double longitude = intent.getDoubleExtra("longitude", 0);

        btnPlastic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSelectedOption.setText("Selected : Plastic");
            }
        });

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSelectedOption.setText("Selected : Paper");
            }
        });

        btnOrganic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtSelectedOption.setText("Selected : Organic");
            }

        });
        btnSendPickupRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(PickupRequests.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                String addressString = address.getAddressLine(0);
                addressTextView.setText(addressString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
