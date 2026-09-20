package com.trinetra.personnel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.trinetra.personnel.R;
import com.trinetra.personnel.preferences.UserPreferences;

public class PersonalizationActivity extends AppCompatActivity {

    private EditText etRole;
    private EditText etPosting;
    private EditText etTenure;

    private EditText etDuty;
    private EditText etOvertime;
    private EditText etNight;
    private EditText etConsecutive;
    private EditText etRest;
    private EditText etTraining;
    private EditText etDeployment;
    private EditText etDaysSinceDeployment;
    private EditText etDeploymentCount;
    private EditText etLeave;
    private EditText etDaysSinceLeave;

    private UserPreferences userPreferences;

    private String personnelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalization);

        personnelId = getIntent()
                .getStringExtra("personnel_id");

        userPreferences =
                new UserPreferences(this);

        etRole = findViewById(R.id.etRole);
        etPosting = findViewById(R.id.etPosting);
        etTenure = findViewById(R.id.etTenure);

        etDuty = findViewById(R.id.etDuty);
        etOvertime = findViewById(R.id.etOvertime);
        etNight = findViewById(R.id.etNight);
        etConsecutive = findViewById(R.id.etConsecutive);
        etRest = findViewById(R.id.etRest);
        etTraining = findViewById(R.id.etTraining);
        etDeployment = findViewById(R.id.etDeployment);
        etDaysSinceDeployment =
                findViewById(R.id.etDaysSinceDeployment);
        etDeploymentCount =
                findViewById(R.id.etDeploymentCount);
        etLeave = findViewById(R.id.etLeave);
        etDaysSinceLeave =
                findViewById(R.id.etDaysSinceLeave);

        findViewById(R.id.btnFinish)
                .setOnClickListener(v -> saveProfile());
    }

    private void saveProfile() {

        String role = getText(etRole);
        String posting = getText(etPosting);

        Float tenure = getNumber(etTenure);
        Float duty = getNumber(etDuty);
        Float overtime = getNumber(etOvertime);
        Float night = getNumber(etNight);
        Float consecutive = getNumber(etConsecutive);
        Float rest = getNumber(etRest);
        Float training = getNumber(etTraining);
        Float deployment = getNumber(etDeployment);
        Float daysSinceDeployment =
                getNumber(etDaysSinceDeployment);
        Float deploymentCount =
                getNumber(etDeploymentCount);
        Float leave = getNumber(etLeave);
        Float daysSinceLeave =
                getNumber(etDaysSinceLeave);

        if (role.isEmpty() || posting.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please complete role and posting.",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }

        if (tenure == null ||
                duty == null ||
                overtime == null ||
                night == null ||
                consecutive == null ||
                rest == null ||
                training == null ||
                deployment == null ||
                daysSinceDeployment == null ||
                deploymentCount == null ||
                leave == null ||
                daysSinceLeave == null) {

            Toast.makeText(
                    this,
                    "Please complete all service details.",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        if (personnelId == null ||
                personnelId.trim().length() < 3) {

            Toast.makeText(
                    this,
                    "Personnel ID is missing.",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        userPreferences.savePersonnelProfile(
                personnelId.trim(),
                role,
                posting,
                tenure
        );

        userPreferences.saveServiceContext(
                duty,
                overtime,
                night,
                consecutive,
                rest,
                training,
                deployment,
                daysSinceDeployment,
                deploymentCount,
                leave,
                daysSinceLeave
        );

        Intent intent = new Intent(
                PersonalizationActivity.this,
                HomeActivity.class
        );

        startActivity(intent);
        finish();
    }

    private String getText(EditText editText) {
        return editText
                .getText()
                .toString()
                .trim();
    }

    private Float getNumber(EditText editText) {

        String value = getText(editText);

        if (value.isEmpty()) {
            editText.setError("Required");
            return null;
        }

        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException exception) {
            editText.setError("Enter a valid number");
            return null;
        }
    }
}