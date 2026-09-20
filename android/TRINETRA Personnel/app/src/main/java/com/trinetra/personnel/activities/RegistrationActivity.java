package com.trinetra.personnel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.trinetra.personnel.R;
import com.trinetra.personnel.repository.AuthRepository;

public class RegistrationActivity extends AppCompatActivity {

    private EditText etPersonnelId;
    private AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etPersonnelId = findViewById(R.id.etPersonnelId);

        authRepository = new AuthRepository(this);

        findViewById(R.id.btnContinue).setOnClickListener(v -> continueRegistration());
    }

    private void continueRegistration() {

        String personnelId =
                etPersonnelId.getText().toString().trim();

        // Temporary local registration flow.
        // Actual identity verification should be connected
        // to the organisation's authentication service in production.
        if (personnelId.length() < 3) {

            etPersonnelId.setError(
                    "Enter a valid personnel ID"
            );

            return;
        }

        Intent intent = new Intent(
                RegistrationActivity.this,
                OtpVerificationActivity.class
        );

        intent.putExtra(
                "personnel_id",
                personnelId
        );

        startActivity(intent);
    }
}