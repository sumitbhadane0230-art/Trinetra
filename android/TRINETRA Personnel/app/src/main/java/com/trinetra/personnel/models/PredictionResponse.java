package com.trinetra.personnel.models;

public class PredictionResponse {

    private String status;
    private String personnel_id;
    private Result result;

    public String getStatus() {
        return status;
    }

    public String getPersonnelId() {
        return personnel_id;
    }

    public Result getResult() {
        return result;
    }

    public static class Result {

        private String stress_risk;
        private String fatigue_risk;
        private String burnout_risk;

        private String overall_risk;
        private String welfare_priority;
        private String severity_trajectory;

        private boolean persistent_high;

        public String getStressRisk() {
            return stress_risk;
        }

        public String getFatigueRisk() {
            return fatigue_risk;
        }

        public String getBurnoutRisk() {
            return burnout_risk;
        }

        public String getOverallRisk() {
            return overall_risk;
        }

        public String getWelfarePriority() {
            return welfare_priority;
        }

        public String getSeverityTrajectory() {
            return severity_trajectory;
        }

        public boolean isPersistentHigh() {
            return persistent_high;
        }
    }
}