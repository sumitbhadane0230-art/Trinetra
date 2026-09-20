package com.trinetra.personnel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.trinetra.personnel.R;
import com.trinetra.personnel.preferences.UserPreferences;

public class ProfileActivity extends AppCompatActivity {

    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userPreferences = new UserPreferences(this);

        TextView tvPersonnelId =
                findViewById(R.id.tvPersonnelId);

        TextView tvRole =
                findViewById(R.id.tvRole);

        TextView tvPosting =
                findViewById(R.id.tvPosting);

        TextView tvTenure =
                findViewById(R.id.tvTenure);

        tvPersonnelId.setText(
                userPreferences.getPersonnelId()
        );

        tvRole.setText(
                userPreferences.getRoleCategory()
        );

        tvPosting.setText(
                userPreferences.getPostingType()
        );

        tvTenure.setText(
                String.valueOf(
                        (int) userPreferences.getServiceTenureMonths()
                ) + " months"
        );

        findViewById(R.id.btnPrivacy)
                .setOnClickListener(v ->
                        startActivity(
                                new Intent(
                                        ProfileActivity.this,
                                        PrivacyActivity.class
                                )
                        ));
    }
}