package com.trinetra.personnel.models;

public class WellnessCheckInRequest {

    private String personnel_id;

    private String role_category;
    private String posting_type;

    private double service_tenure_months;

    private double duty_hours_week;
    private double overtime_hours;
    private double night_shifts;
    private double consecutive_duty_days;
    private double avg_rest_hours;
    private double training_hours;
    private double current_deployment_days;
    private double days_since_last_deployment;
    private double recent_deployment_count;
    private double leave_days_taken;
    private double days_since_last_leave;

    private double avg_sleep_hours;
    private double sleep_quality;
    private double self_reported_stress;
    private double fatigue_level;
    private double mood_score;
    private double recovery_feeling;

    public WellnessCheckInRequest(
            String personnel_id,
            String role_category,
            String posting_type,
            double service_tenure_months,
            double duty_hours_week,
            double overtime_hours,
            double night_shifts,
            double consecutive_duty_days,
            double avg_rest_hours,
            double training_hours,
            double current_deployment_days,
            double days_since_last_deployment,
            double recent_deployment_count,
            double leave_days_taken,
            double days_since_last_leave,
            double avg_sleep_hours,
            double sleep_quality,
            double self_reported_stress,
            double fatigue_level,
            double mood_score,
            double recovery_feeling
    ) {
        this.personnel_id = personnel_id;
        this.role_category = role_category;
        this.posting_type = posting_type;
        this.service_tenure_months = service_tenure_months;
        this.duty_hours_week = duty_hours_week;
        this.overtime_hours = overtime_hours;
        this.night_shifts = night_shifts;
        this.consecutive_duty_days = consecutive_duty_days;
        this.avg_rest_hours = avg_rest_hours;
        this.training_hours = training_hours;
        this.current_deployment_days = current_deployment_days;
        this.days_since_last_deployment = days_since_last_deployment;
        this.recent_deployment_count = recent_deployment_count;
        this.leave_days_taken = leave_days_taken;
        this.days_since_last_leave = days_since_last_leave;
        this.avg_sleep_hours = avg_sleep_hours;
        this.sleep_quality = sleep_quality;
        this.self_reported_stress = self_reported_stress;
        this.fatigue_level = fatigue_level;
        this.mood_score = mood_score;
        this.recovery_feeling = recovery_feeling;
    }
}