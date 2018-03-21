package com.example.redoyahmed.bangladeshilivetv.Services;

import com.example.redoyahmed.bangladeshilivetv.Model.SignInResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("user_register_api.php?")
    Call<SignUpResponse> signUpOutput(@Query("name") String name,
                                      @Query("email") String email,
                                      @Query("password") String password,
                                      @Query("phone") String phone);

    @GET("user_login_api.php?")
    Call<SignInResponse> signInOutput(@Query("email") String email,
                                      @Query("password") String password);
}
