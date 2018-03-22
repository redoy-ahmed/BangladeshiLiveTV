package com.example.redoyahmed.bangladeshilivetv.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class GetUserProfileResponse
{
    @SerializedName("LIVETV")
    private LIVETVforGetUserProfile[] LIVETVforGetUserProfile;

    public LIVETVforGetUserProfile[] getLIVETVforGetUserProfile () {
        return LIVETVforGetUserProfile;
    }

    public void setLIVETV (LIVETVforGetUserProfile[] LIVETVforGetUserProfile) {
        this.LIVETVforGetUserProfile = LIVETVforGetUserProfile;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [LIVETV = "+LIVETVforGetUserProfile+"]";
    }
}