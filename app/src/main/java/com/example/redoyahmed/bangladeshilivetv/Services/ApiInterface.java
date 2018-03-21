package com.example.redoyahmed.bangladeshilivetv.Services;

import com.example.redoyahmed.bangladeshilivetv.Model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("user_register_api.php?")
    Call<RegisterResponse> requestOutput(@Query("name") String name,
                                         @Query("email") String email,
                                         @Query("password") String password,
                                         @Query("phone") String phone);
}
