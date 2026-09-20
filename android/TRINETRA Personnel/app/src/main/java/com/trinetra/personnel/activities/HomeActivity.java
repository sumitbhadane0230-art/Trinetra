package com.trinetra.personnel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.trinetra.personnel.R;
import com.trinetra.personnel.preferences.UserPreferences;

public class HomeActivity extends AppCompatActivity {

    private UserPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = new UserPreferences(this);

        TextView tvGreeting = findViewById(R.id.tvGreeting);
        TextView tvPersonnelId = findViewById(R.id.tvPersonnelIdHome);

        String personnelId = preferences.getPersonnelId();

        if (personnelId.isEmpty()) {
            tvGreeting.setText("Welcome");
        } else {
            tvGreeting.setText("Welcome back");
            tvPersonnelId.setText(personnelId);
        }

        findViewById(R.id.cardCheckIn).setOnClickListener(v ->
                startActivity(new Intent(this, CheckInActivity.class)));

        findViewById(R.id.cardHistory).setOnClickListener(v ->
                startActivity(new Intent(this, HistoryActivity.class)));

        findViewById(R.id.cardChat).setOnClickListener(v ->
                startActivity(new Intent(this, ChatActivity.class)));

        findViewById(R.id.cardSupport).setOnClickListener(v ->
                startActivity(new Intent(this, SupportActivity.class)));

        findViewById(R.id.cardProfile).setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));
    }
}