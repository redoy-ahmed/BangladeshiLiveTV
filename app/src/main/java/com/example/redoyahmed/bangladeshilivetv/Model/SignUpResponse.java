package com.example.redoyahmed.bangladeshilivetv.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by redoy.ahmed on 20-Mar-2018.
 */

public class SignUpResponse {

    @SerializedName("LIVETV")
    private LIVETVforSignUp[] LIVETVforSignUp;

    public LIVETVforSignUp[] getLIVETVforSignUp() {
        return LIVETVforSignUp;
    }

    public void setLIVETVforSignUp(LIVETVforSignUp[] LIVETVforSignUp) {
        this.LIVETVforSignUp = LIVETVforSignUp;
    }

    @Override
    public String toString() {
        return "ClassPojo [LIVETVforSignUp = " + LIVETVforSignUp + "]";
    }
}
