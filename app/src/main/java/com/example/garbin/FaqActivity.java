package com.example.garbin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FaqActivity extends AppCompatActivity {

    private RecyclerView faqRecyclerView;
    private FAQAdapter faqAdapter;
    private List<FAQItem> faqList;
    private Spinner problemSpinner;
    private EditText locationEditText;
    private EditText detailsEditText;
    private Button submitrep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        faqRecyclerView = findViewById(R.id.faqRecyclerView);
        faqList = new ArrayList<>();
        faqList.add(new FAQItem("What is GarBin?", "This is a new project made by a team studying at ICBT Gampaha."));
        faqList.add(new FAQItem("Where is this app made?", "In Sri Lanka, And only developers in Sri Lanka."));
        faqList.add(new FAQItem("Why is this so important", "Recycling is important and should be done."));
        faqList.add(new FAQItem("Can this App help reduce polution", "Yes by showing people how it will impact in their life."));
        faqList.add(new FAQItem("What if i have problems related to garbage", "There is a problem reporting section in this app and use it."));
        faqList.add(new FAQItem("Will this app be available in Sinhala", "Yes the developers of the app will make the app multilingual, So there will be more than just Sinhala and English. "));
        faqAdapter = new FAQAdapter(faqList);
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        faqRecyclerView.setAdapter(faqAdapter);

        problemSpinner = findViewById(R.id.problem_spinner);
        locationEditText = findViewById(R.id.location_edit_text);
        detailsEditText = findViewById(R.id.details_edit_text);
        submitrep = (Button) findViewById(R.id.submit_button);

        locationEditText.addTextChangedListener(submitwatcher);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.problem_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        problemSpinner.setAdapter(adapter);
    }
    public void onSubmitButtonClick(View view) {
        String problem = (String) problemSpinner.getSelectedItem();
        String location = locationEditText.getText().toString();
        String details = detailsEditText.getText().toString();

        if (problem.equals("") || location.equals("")) {
            Toast.makeText(FaqActivity.this, "Fill in the fields to report", Toast.LENGTH_SHORT).show();
        }

        //DATABESE

        Toast.makeText(this, "Reported Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    private TextWatcher submitwatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String problemSpinnerinput = problemSpinner.getSelectedItem().toString().trim();
            String locationinput = locationEditText.getText().toString().trim();

            submitrep.setEnabled(!problemSpinnerinput.isEmpty() && !locationinput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}