package com.trinetra.personnel.models;

public class PersonnelProfile {

    private final String personnelId;
    private final String roleCategory;
    private final String postingType;
    private final float serviceTenureMonths;

    public PersonnelProfile(
            String personnelId,
            String roleCategory,
            String postingType,
            float serviceTenureMonths
    ) {
        this.personnelId = personnelId;
        this.roleCategory = roleCategory;
        this.postingType = postingType;
        this.serviceTenureMonths = serviceTenureMonths;
    }

    public String getPersonnelId() {
        return personnelId;
    }

    public String getRoleCategory() {
        return roleCategory;
    }

    public String getPostingType() {
        return postingType;
    }

    public float getServiceTenureMonths() {
        return serviceTenureMonths;
    }
}