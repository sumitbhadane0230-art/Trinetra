package com.trinetra.personnel.models;

public class HistoryRecord {

    private String date;
    private float sleepHours;
    private float sleepQuality;
    private float stressLevel;
    private float fatigueLevel;
    private float moodScore;
    private float recoveryFeeling;
    private String note;

    public HistoryRecord(
            String date,
            float sleepHours,
            float sleepQuality,
            float stressLevel,
            float fatigueLevel,
            float moodScore,
            float recoveryFeeling,
            String note
    ) {
        this.date = date;
        this.sleepHours = sleepHours;
        this.sleepQuality = sleepQuality;
        this.stressLevel = stressLevel;
        this.fatigueLevel = fatigueLevel;
        this.moodScore = moodScore;
        this.recoveryFeeling = recoveryFeeling;
        this.note = note;
    }

    public String getDate() {
        return date;
    }

    public float getSleepHours() {
        return sleepHours;
    }

    public float getSleepQuality() {
        return sleepQuality;
    }

    public float getStressLevel() {
        return stressLevel;
    }

    public float getFatigueLevel() {
        return fatigueLevel;
    }

    public float getMoodScore() {
        return moodScore;
    }

    public float getRecoveryFeeling() {
        return recoveryFeeling;
    }

    public String getNote() {
        return note;
    }
}