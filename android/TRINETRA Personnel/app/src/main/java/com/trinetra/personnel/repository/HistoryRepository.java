package com.trinetra.personnel.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.trinetra.personnel.models.HistoryRecord;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryRepository {

    private static final String PREF_NAME = "trinetra_history";
    private static final String KEY_HISTORY = "records";

    private final SharedPreferences preferences;

    public HistoryRepository(Context context) {
        preferences = context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
        );
    }

    public void saveRecord(HistoryRecord record) {
        try {
            JSONArray array = new JSONArray(
                    preferences.getString(KEY_HISTORY, "[]")
            );

            JSONObject object = new JSONObject();

            object.put("date", record.getDate());
            object.put("sleepHours", record.getSleepHours());
            object.put("sleepQuality", record.getSleepQuality());
            object.put("stressLevel", record.getStressLevel());
            object.put("fatigueLevel", record.getFatigueLevel());
            object.put("moodScore", record.getMoodScore());
            object.put("recoveryFeeling", record.getRecoveryFeeling());
            object.put("note", record.getNote());

            array.put(object);

            preferences.edit()
                    .putString(KEY_HISTORY, array.toString())
                    .apply();

        } catch (Exception ignored) {
        }
    }

    public List<HistoryRecord> getRecords() {
        List<HistoryRecord> records = new ArrayList<>();

        try {
            JSONArray array = new JSONArray(
                    preferences.getString(KEY_HISTORY, "[]")
            );

            for (int i = array.length() - 1; i >= 0; i--) {
                JSONObject object = array.getJSONObject(i);

                records.add(
                        new HistoryRecord(
                                object.optString("date", ""),
                                (float) object.optDouble("sleepHours", 0),
                                (float) object.optDouble("sleepQuality", 0),
                                (float) object.optDouble("stressLevel", 0),
                                (float) object.optDouble("fatigueLevel", 0),
                                (float) object.optDouble("moodScore", 0),
                                (float) object.optDouble("recoveryFeeling", 0),
                                object.optString("note", "")
                        )
                );
            }

        } catch (Exception ignored) {
        }

        return records;
    }

    public void clearHistory() {
        preferences.edit()
                .remove(KEY_HISTORY)
                .apply();
    }
}