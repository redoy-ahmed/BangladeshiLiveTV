package com.example.redoyahmed.bangladeshilivetv.Services;

import android.content.Context;

import com.example.redoyahmed.bangladeshilivetv.Utils.CustomSharedPreference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by redoy.ahmed on 20-Mar-2018.
 */

public class LiveTvApplication {

    private static GsonBuilder builder;
    private static Gson gson;
    private static CustomSharedPreference sharedPreference;

    public static CustomSharedPreference getSharedPreference(Context context) {
        if (sharedPreference == null) {
            sharedPreference = new CustomSharedPreference(context);
        }
        return sharedPreference;
    }

    public static Gson getGsonObject() {
        if (gson == null) {
            builder = new GsonBuilder();
            gson = builder.create();
        }
        return gson;
    }
}
