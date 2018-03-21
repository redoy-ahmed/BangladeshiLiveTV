package com.example.redoyahmed.bangladeshilivetv.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by redoy.ahmed on 21-Mar-2018.
 */

public class ForgotPasswordResponse {

    @SerializedName("LIVETV")
    private LIVETVforForgotPassword[] LIVETVforForgotPassword;

    public LIVETVforForgotPassword[] getLIVETVforForgotPassword() {
        return LIVETVforForgotPassword;
    }

    public void setLIVETVforForgotPassword(LIVETVforForgotPassword[] LIVETVforForgotPassword) {
        this.LIVETVforForgotPassword = LIVETVforForgotPassword;
    }

    @Override
    public String toString() {
        return "ClassPojo [LIVETV = " + LIVETVforForgotPassword + "]";
    }
}

