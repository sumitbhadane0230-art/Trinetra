package com.trinetra.personnel.utils;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isValidPersonnelId(String value) {
        if (isEmpty(value)) {
            return false;
        }

        return value.trim().length() >= 3;
    }

    public static boolean isValidOtp(String value) {
        if (isEmpty(value)) {
            return false;
        }

        String otp = value.trim();

        if (otp.length() != 6) {
            return false;
        }

        for (char character : otp.toCharArray()) {
            if (!Character.isDigit(character)) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidRating(float value) {
        return value >= 1f && value <= 5f;
    }

    public static boolean isValidSleepHours(float value) {
        return value >= 0f && value <= 24f;
    }
}