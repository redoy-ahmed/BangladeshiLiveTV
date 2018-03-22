package com.example.redoyahmed.bangladeshilivetv.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class UpdateProfileResponse {

    @SerializedName("LIVETV")
    private LIVETVforUpdateProfile[] LIVETVforUpdateProfile;

    public LIVETVforUpdateProfile[] getLIVETVforUpdateProfile() {
        return LIVETVforUpdateProfile;
    }

    public void setLIVETVforUpdateProfile(LIVETVforUpdateProfile[] LIVETVforUpdateProfile) {
        this.LIVETVforUpdateProfile = LIVETVforUpdateProfile;
    }

    @Override
    public String toString() {
        return "ClassPojo [LIVETV = " + LIVETVforUpdateProfile + "]";
    }
}
