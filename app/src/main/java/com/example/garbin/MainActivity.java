package com.example.garbin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    boolean DoublePressToExit = false;
    Toast toast;
    TextView welcomeMessage;
    SearchView searchView;
    private TextView garbageCollectionDay;
    private ImageView paperImage;
    private ImageView organicImage;
    private ImageView plasticImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendpickreqbtn = findViewById(R.id.sendpick_req);
        sendpickreqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GarbagePickupRequestActivity.class);
                startActivity(intent);
            }
        });

        Button myactivitybtn = findViewById(R.id.my_activity);
        myactivitybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyActivity.class);
                startActivity(intent);
            }
        });

        paperImage = findViewById(R.id.paper_image);
        organicImage = findViewById(R.id.organic_image);
        plasticImage = findViewById(R.id.plastic_image);

        garbageCollectionDay = findViewById(R.id.garbage_collection_day);

        Calendar calendar = Calendar.getInstance();

        String nextGarbageCollectionDay = getNextGarbageCollectionDay(calendar);
        garbageCollectionDay.setText(nextGarbageCollectionDay);

        Button faqButton = findViewById(R.id.faqbutton);
        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FaqActivity.class);
                startActivity(intent);
            }
        });

        welcomeMessage = findViewById(R.id.welcomeMessage);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        String message = "Hellow, " + username + " !";
        welcomeMessage.setText(message);

        searchView = findViewById(R.id.search_bar);
        searchView.setQueryHint("What Goes Where?");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String category = sortGarbage(query);
                Toast.makeText(getApplicationContext(), "Category: " + category, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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

    public String sortGarbage(String query) {
        if (query.toLowerCase().contains("paper") || query.toLowerCase().contains("cardboard") || query.toLowerCase().contains("box") || query.toLowerCase().contains("book") || query.toLowerCase().contains("Newspaper")){
            return "Paper";
        } else if (query.toLowerCase().contains("Cotton fabric") || query.toLowerCase().contains("Wood") || query.toLowerCase().contains("Leather") || query.toLowerCase().contains("Silk") || query.toLowerCase().contains("Wool")){
            return "Organic";
        } else if (query.toLowerCase().contains("Plastic bags") || query.toLowerCase().contains("Water bottles") || query.toLowerCase().contains("Food containers") || query.toLowerCase().contains("Toys") || query.toLowerCase().contains("Credit cards")){
            return "Plastic";
        } else {
            return "Landfill";
        }
    }
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private String getNextGarbageCollectionDay(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {

            case Calendar.MONDAY:
                paperImage.setVisibility(View.VISIBLE);
                calendar.add(Calendar.DATE, 1);
                return "TODAY\n8:30 AM\nPAPER";

            case Calendar.TUESDAY:
                organicImage.setVisibility(View.VISIBLE);
                calendar.add(Calendar.DATE, 2);
                return "WEDNESDAY\n9:30 AM\nORGANIC";

            case Calendar.WEDNESDAY:
                organicImage.setVisibility(View.VISIBLE);
                calendar.add(Calendar.DATE, 3);
                return "TODAY\n9:30 AM\nORGANIC";

            case Calendar.THURSDAY:
                plasticImage.setVisibility(View.VISIBLE);
                calendar.add(Calendar.DATE, 4);
                return "FRIDAY\n10:30 AM\nPLASTIC";

            case Calendar.FRIDAY:
                plasticImage.setVisibility(View.VISIBLE);
                calendar.add(Calendar.DATE, 5);
                return "TODAY\n10:30 AM\nPLASTIC";

            case Calendar.SATURDAY:
                paperImage.setVisibility(View.VISIBLE);
                calendar.add(Calendar.DATE, 6);
                return "MONDAY\n8:30 AM\nPAPER";

            case Calendar.SUNDAY:
                paperImage.setVisibility(View.VISIBLE);
                calendar.add(Calendar.DATE, 7);
                return "MONDAY\n8:30 AM\nPAPER";

            default:
                return "Unknown";
        }
    }
}
