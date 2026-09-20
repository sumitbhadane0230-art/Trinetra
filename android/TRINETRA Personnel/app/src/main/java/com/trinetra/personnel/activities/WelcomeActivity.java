package com.trinetra.personnel.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.trinetra.personnel.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.btnGetStarted).setOnClickListener(v -> {
            Intent intent = new Intent(
                    WelcomeActivity.this,
                    RegistrationActivity.class
            );
            startActivity(intent);
        });
    }
}