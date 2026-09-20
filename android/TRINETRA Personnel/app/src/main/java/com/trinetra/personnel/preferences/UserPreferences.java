package com.trinetra.personnel.preferences;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {

    private static final String PREF_NAME =
            "trinetra_personnel_preferences";

    private static final String KEY_PERSONNEL_ID =
            "personnel_id";

    private static final String KEY_ROLE =
            "role_category";

    private static final String KEY_POSTING =
            "posting_type";

    private static final String KEY_TENURE =
            "service_tenure_months";

    private static final String KEY_DUTY =
            "duty_hours_week";

    private static final String KEY_OVERTIME =
            "overtime_hours";

    private static final String KEY_NIGHT =
            "night_shifts";

    private static final String KEY_CONSECUTIVE =
            "consecutive_duty_days";

    private static final String KEY_REST =
            "avg_rest_hours";

    private static final String KEY_TRAINING =
            "training_hours";

    private static final String KEY_DEPLOYMENT =
            "current_deployment_days";

    private static final String KEY_DAYS_SINCE_DEPLOYMENT =
            "days_since_last_deployment";

    private static final String KEY_DEPLOYMENT_COUNT =
            "recent_deployment_count";

    private static final String KEY_LEAVE =
            "leave_days_taken";

    private static final String KEY_DAYS_SINCE_LEAVE =
            "days_since_last_leave";

    private static final String KEY_PROFILE_COMPLETE =
            "profile_complete";

    private final SharedPreferences preferences;

    public UserPreferences(Context context) {
        preferences = context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
        );
    }

    public void savePersonnelProfile(
            String personnelId,
            String roleCategory,
            String postingType,
            float serviceTenureMonths
    ) {

        preferences.edit()
                .putString(KEY_PERSONNEL_ID, personnelId)
                .putString(KEY_ROLE, roleCategory)
                .putString(KEY_POSTING, postingType)
                .putFloat(KEY_TENURE, serviceTenureMonths)
                .putBoolean(KEY_PROFILE_COMPLETE, true)
                .apply();
    }

    public void saveServiceContext(
            float dutyHours,
            float overtimeHours,
            float nightShifts,
            float consecutiveDutyDays,
            float avgRestHours,
            float trainingHours,
            float currentDeploymentDays,
            float daysSinceLastDeployment,
            float recentDeploymentCount,
            float leaveDaysTaken,
            float daysSinceLastLeave
    ) {

        preferences.edit()
                .putFloat(KEY_DUTY, dutyHours)
                .putFloat(KEY_OVERTIME, overtimeHours)
                .putFloat(KEY_NIGHT, nightShifts)
                .putFloat(KEY_CONSECUTIVE, consecutiveDutyDays)
                .putFloat(KEY_REST, avgRestHours)
                .putFloat(KEY_TRAINING, trainingHours)
                .putFloat(KEY_DEPLOYMENT, currentDeploymentDays)
                .putFloat(KEY_DAYS_SINCE_DEPLOYMENT, daysSinceLastDeployment)
                .putFloat(KEY_DEPLOYMENT_COUNT, recentDeploymentCount)
                .putFloat(KEY_LEAVE, leaveDaysTaken)
                .putFloat(KEY_DAYS_SINCE_LEAVE, daysSinceLastLeave)
                .apply();
    }

    public String getPersonnelId() {
        return preferences.getString(
                KEY_PERSONNEL_ID,
                ""
        );
    }

    public String getRoleCategory() {
        return preferences.getString(
                KEY_ROLE,
                ""
        );
    }

    public String getPostingType() {
        return preferences.getString(
                KEY_POSTING,
                ""
        );
    }

    public float getServiceTenureMonths() {
        return preferences.getFloat(
                KEY_TENURE,
                0f
        );
    }

    public float getDutyHours() {
        return preferences.getFloat(KEY_DUTY, 40f);
    }

    public float getOvertimeHours() {
        return preferences.getFloat(KEY_OVERTIME, 0f);
    }

    public float getNightShifts() {
        return preferences.getFloat(KEY_NIGHT, 0f);
    }

    public float getConsecutiveDutyDays() {
        return preferences.getFloat(KEY_CONSECUTIVE, 1f);
    }

    public float getAvgRestHours() {
        return preferences.getFloat(KEY_REST, 8f);
    }

    public float getTrainingHours() {
        return preferences.getFloat(KEY_TRAINING, 0f);
    }

    public float getCurrentDeploymentDays() {
        return preferences.getFloat(KEY_DEPLOYMENT, 0f);
    }

    public float getDaysSinceLastDeployment() {
        return preferences.getFloat(
                KEY_DAYS_SINCE_DEPLOYMENT,
                30f
        );
    }

    public float getRecentDeploymentCount() {
        return preferences.getFloat(
                KEY_DEPLOYMENT_COUNT,
                0f
        );
    }

    public float getLeaveDaysTaken() {
        return preferences.getFloat(
                KEY_LEAVE,
                0f
        );
    }

    public float getDaysSinceLastLeave() {
        return preferences.getFloat(
                KEY_DAYS_SINCE_LEAVE,
                30f
        );
    }

    public boolean isProfileComplete() {
        return preferences.getBoolean(
                KEY_PROFILE_COMPLETE,
                false
        );
    }

    public void clearProfile() {
        preferences.edit()
                .clear()
                .apply();
    }
}