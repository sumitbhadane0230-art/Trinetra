package com.trinetra.personnel.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.slider.Slider;
import com.trinetra.personnel.R;
import com.trinetra.personnel.models.HistoryRecord;
import com.trinetra.personnel.models.PredictionResponse;
import com.trinetra.personnel.models.WellnessCheckInRequest;
import com.trinetra.personnel.preferences.UserPreferences;
import com.trinetra.personnel.repository.HistoryRepository;
import com.trinetra.personnel.repository.WellnessRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckInActivity extends AppCompatActivity {

    private Slider sliderSleep;
    private Slider sliderSleepQuality;
    private Slider sliderStress;
    private Slider sliderFatigue;
    private Slider sliderMood;
    private Slider sliderRecovery;

    private EditText etNote;

    private WellnessRepository wellnessRepository;
    private UserPreferences userPreferences;
    private HistoryRepository historyRepository;

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_in);

        sliderSleep = findViewById(R.id.sliderSleep);
        sliderSleepQuality = findViewById(R.id.sliderSleepQuality);
        sliderStress = findViewById(R.id.sliderStress);
        sliderFatigue = findViewById(R.id.sliderFatigue);
        sliderMood = findViewById(R.id.sliderMood);
        sliderRecovery = findViewById(R.id.sliderRecovery);

        etNote = findViewById(R.id.etNote);

        submitButton =
                findViewById(R.id.btnSubmitCheckIn);

        userPreferences =
                new UserPreferences(this);

        historyRepository =
                new HistoryRepository(this);

        wellnessRepository =
                new WellnessRepository();

        submitButton.setOnClickListener(
                v -> submitCheckIn()
        );
    }

    private void submitCheckIn() {

        if (!userPreferences.isProfileComplete()) {

            Toast.makeText(
                    this,
                    "Please complete your profile first.",
                    Toast.LENGTH_LONG
            ).show();

            return;
        }

        float sleep = sliderSleep.getValue();
        float sleepQuality = sliderSleepQuality.getValue();
        float stress = sliderStress.getValue();
        float fatigue = sliderFatigue.getValue();
        float mood = sliderMood.getValue();
        float recovery = sliderRecovery.getValue();

        String note =
                etNote.getText()
                        .toString()
                        .trim();

        WellnessCheckInRequest request =
                new WellnessCheckInRequest(

                        userPreferences.getPersonnelId(),

                        userPreferences.getRoleCategory(),

                        userPreferences.getPostingType(),

                        userPreferences.getServiceTenureMonths(),

                        userPreferences.getDutyHours(),

                        userPreferences.getOvertimeHours(),

                        userPreferences.getNightShifts(),

                        userPreferences.getConsecutiveDutyDays(),

                        userPreferences.getAvgRestHours(),

                        userPreferences.getTrainingHours(),

                        userPreferences.getCurrentDeploymentDays(),

                        userPreferences.getDaysSinceLastDeployment(),

                        userPreferences.getRecentDeploymentCount(),

                        userPreferences.getLeaveDaysTaken(),

                        userPreferences.getDaysSinceLastLeave(),

                        sleep,
                        sleepQuality,
                        stress,
                        fatigue,
                        mood,
                        recovery
                );

        setSubmitButtonEnabled(false);

        wellnessRepository.submitCheckIn(
                request,
                new Callback<PredictionResponse>() {

                    @Override
                    public void onResponse(
                            Call<PredictionResponse> call,
                            Response<PredictionResponse> response
                    ) {

                        setSubmitButtonEnabled(true);

                        if (!response.isSuccessful()
                                || response.body() == null) {

                            Toast.makeText(
                                    CheckInActivity.this,
                                    "Server error: " + response.code(),
                                    Toast.LENGTH_LONG
                            ).show();

                            return;
                        }

                        PredictionResponse result =
                                response.body();

                        saveLocalHistory(
                                sleep,
                                sleepQuality,
                                stress,
                                fatigue,
                                mood,
                                recovery,
                                note
                        );

                        if ("history_required".equals(
                                result.getStatus()
                        )) {

                            Toast.makeText(
                                    CheckInActivity.this,
                                    "Check-in saved. More check-in history is needed before the model can provide a longitudinal prediction.",
                                    Toast.LENGTH_LONG
                            ).show();

                            return;
                        }

                        if ("prediction_available".equals(
                                result.getStatus()
                        )) {

                            Toast.makeText(
                                    CheckInActivity.this,
                                    "Check-in processed successfully.",
                                    Toast.LENGTH_LONG
                            ).show();

                            return;
                        }

                        Toast.makeText(
                                CheckInActivity.this,
                                "Unexpected server response.",
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    @Override
                    public void onFailure(
                            Call<PredictionResponse> call,
                            Throwable t
                    ) {

                        setSubmitButtonEnabled(true);

                        Toast.makeText(
                                CheckInActivity.this,
                                "Unable to connect to TRINETRA server.",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
    }

    private void saveLocalHistory(
            float sleep,
            float sleepQuality,
            float stress,
            float fatigue,
            float mood,
            float recovery,
            String note
    ) {

        String date = new SimpleDateFormat(
                "dd MMM yyyy, HH:mm",
                Locale.getDefault()
        ).format(new Date());

        HistoryRecord record =
                new HistoryRecord(
                        date,
                        sleep,
                        sleepQuality,
                        stress,
                        fatigue,
                        mood,
                        recovery,
                        note
                );

        historyRepository.saveRecord(record);
    }

    private void setSubmitButtonEnabled(
            boolean enabled
    ) {
        submitButton.setEnabled(enabled);
    }
}