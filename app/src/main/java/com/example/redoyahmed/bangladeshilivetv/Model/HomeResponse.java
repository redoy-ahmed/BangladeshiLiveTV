package com.example.redoyahmed.bangladeshilivetv.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class HomeResponse {
    @SerializedName("LIVETV")
    private LIVETVforHome LIVETVforHome;

    public LIVETVforHome getLIVETVforHome() {
        return LIVETVforHome;
    }

    public void setLIVETVforHome(LIVETVforHome LIVETVforHome) {
        this.LIVETVforHome = LIVETVforHome;
    }

    @Override
    public String toString() {
        return "ClassPojo [LIVETVforHome = " + LIVETVforHome + "]";
    }
}
