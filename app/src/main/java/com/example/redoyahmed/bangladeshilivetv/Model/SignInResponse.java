package com.example.redoyahmed.bangladeshilivetv.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by redoy.ahmed on 21-Mar-2018.
 */

public class SignInResponse {

    @SerializedName("LIVETV")
    private LIVETVforSignIn[] LIVETVforSignIn;

    public LIVETVforSignIn[] getLIVETVforSignIn() {
        return LIVETVforSignIn;
    }

    public void setLIVETVforSignIn(LIVETVforSignIn[] LIVETVforSignIn) {
        this.LIVETVforSignIn = LIVETVforSignIn;
    }

    @Override
    public String toString() {
        return "ClassPojo [LIVETVforSignUp = " + LIVETVforSignIn + "]";
    }
}
