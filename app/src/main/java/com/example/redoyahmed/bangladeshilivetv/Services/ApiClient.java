package com.example.redoyahmed.bangladeshilivetv.Services;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String SERVER_URL = "http://rootnews24.com/apps/sports/eboxlive/";
    public static final String REGISTER_URL = SERVER_URL + "user_register_api.php?name=";
    public static final String LOGIN_URL = (SERVER_URL + "user_login_api.php?email=");
    public static final String CLIENT_ID = "e961d05b4710df1545223cb83822edca";
    public static final String CLIENT_SECRET = "290208e794df2b5f13163f0098bff459eac5c486f31b09ef237d4c7639100cb5";
    public static final String IMAGE_PATH = SERVER_URL + "images/";
    private static Retrofit retrofit = null;

    public static Retrofit getLiveTvClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
