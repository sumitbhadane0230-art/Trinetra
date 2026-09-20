package com.trinetra.personnel.repository;

import android.content.Context;

import com.trinetra.personnel.preferences.UserPreferences;
import com.trinetra.personnel.utils.ValidationUtils;

public class AuthRepository {

    private final UserPreferences userPreferences;

    public AuthRepository(Context context) {
        userPreferences = new UserPreferences(context);
    }

    public boolean saveRegistration(
            String personnelId,
            String roleCategory,
            String postingType,
            float serviceTenureMonths
    ) {

        if (!ValidationUtils.isValidPersonnelId(personnelId)) {
            return false;
        }

        if (ValidationUtils.isEmpty(roleCategory)) {
            return false;
        }

        if (ValidationUtils.isEmpty(postingType)) {
            return false;
        }

        if (serviceTenureMonths < 0) {
            return false;
        }

        userPreferences.savePersonnelProfile(
                personnelId.trim(),
                roleCategory.trim(),
                postingType.trim(),
                serviceTenureMonths
        );

        return true;
    }

    public boolean verifyOtp(String otp) {
        return ValidationUtils.isValidOtp(otp);
    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }
}