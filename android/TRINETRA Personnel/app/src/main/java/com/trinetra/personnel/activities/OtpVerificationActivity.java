package com.trinetra.personnel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.trinetra.personnel.R;
import com.trinetra.personnel.repository.AuthRepository;

public class OtpVerificationActivity extends AppCompatActivity {

    private EditText etOtp;
    private AuthRepository authRepository;
    private String personnelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        etOtp = findViewById(R.id.etOtp);

        personnelId = getIntent()
                .getStringExtra("personnel_id");

        authRepository = new AuthRepository(this);

        findViewById(R.id.btnVerify)
                .setOnClickListener(v -> verifyCode());
    }

    private void verifyCode() {

        String otp =
                etOtp.getText().toString().trim();

        if (!authRepository.verifyOtp(otp)) {

            etOtp.setError(
                    "Enter a valid 6-digit code"
            );

            return;
        }

        Intent intent = new Intent(
                OtpVerificationActivity.this,
                PersonalizationActivity.class
        );

        intent.putExtra(
                "personnel_id",
                personnelId
        );

        startActivity(intent);
        finish();
    }
}