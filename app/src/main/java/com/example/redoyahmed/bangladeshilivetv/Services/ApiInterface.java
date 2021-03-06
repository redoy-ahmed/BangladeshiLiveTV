package com.example.redoyahmed.bangladeshilivetv.Services;

import com.example.redoyahmed.bangladeshilivetv.Model.ForgotPasswordResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.GetUserProfileResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.HomeResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.SignInResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.SignUpResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.UpdateProfileResponse;

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

    @GET("user_forgot_pass_api.php?")
    Call<ForgotPasswordResponse> forgotPasswordOutput(@Query("email") String email);

    @GET("user_profile_api.php?")
    Call<GetUserProfileResponse> getUserProfileOutput(@Query("id") String id);

    @GET("user_profile_update_api.php?")
    Call<UpdateProfileResponse> updateProfileOutput(@Query("name") String name,
                                                    @Query("email") String email,
                                                    @Query("password") String password,
                                                    @Query("phone") String phone,
                                                    @Query("user_id") String user_id);

    @GET("api.php?home")
    Call<HomeResponse> homeOutput();

}
