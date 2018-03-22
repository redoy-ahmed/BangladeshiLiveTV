package com.example.redoyahmed.bangladeshilivetv.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redoyahmed.bangladeshilivetv.Model.GetUserProfileResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.LIVETVforGetUserProfile;
import com.example.redoyahmed.bangladeshilivetv.Model.LIVETVforUpdateProfile;
import com.example.redoyahmed.bangladeshilivetv.Model.UpdateProfileResponse;
import com.example.redoyahmed.bangladeshilivetv.R;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiClient;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiInterface;
import com.example.redoyahmed.bangladeshilivetv.Services.LiveTvApplication;
import com.example.redoyahmed.bangladeshilivetv.Utils.ConnectionStatus;
import com.example.redoyahmed.bangladeshilivetv.Utils.Constants;
import com.example.redoyahmed.bangladeshilivetv.Utils.CustomSharedPreference;
import com.example.redoyahmed.bangladeshilivetv.Utils.CustomSweetAlertDialog;
import com.example.redoyahmed.bangladeshilivetv.Utils.StatusCodes;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.button)
    Button btnSignUp;

    @Email(message = "Please Check and Enter a valid Email Address")
    @NotEmpty
    @BindView(R.id.edt_email)
    EditText edtEmail;

    @NotEmpty
    @BindView(R.id.edt_name)
    EditText edtFullName;

    @NotEmpty
    @BindView(R.id.edt_contact_no)
    EditText edtMobile;

    @Password(message = "Enter a Valid Password")
    @NotEmpty
    @BindView(R.id.edt_password)
    EditText edtPassword;


    CustomSharedPreference shared;
    String strEmail;
    String strFullname;
    String strMobi;
    String strPassword;

    private Validator validator;

    private static final String TAG = ProfileActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        shared = LiveTvApplication.getSharedPreference(getApplicationContext());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        validator = new Validator(this);
        validator.setValidationListener(this);

        if (ConnectionStatus.getInstance(this).isOnline()) {
            if (!shared.getUserId().equals(null)) {
                getUserProfile();
            } else {
                showToast("You are not logged in!");
            }
        } else {
            showToast(getString(R.string.conne_msg1));
        }
        btnSignUp.setOnClickListener(new UpdateProfileClass());
    }

    @Override
    public void onValidationSucceeded() {
        strFullname = edtFullName.getText().toString().replace(" ", "%20");
        strEmail = edtEmail.getText().toString();
        strPassword = edtPassword.getText().toString();
        strMobi = edtMobile.getText().toString();

        if (ConnectionStatus.getInstance(this).isOnline()) {
            updateProfile();
        } else {
            showToast(getString(R.string.conne_msg1));
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    class UpdateProfileClass implements View.OnClickListener {
        UpdateProfileClass() {
        }

        public void onClick(View v) {
            ProfileActivity.this.validator.validate();
        }
    }

    private void updateProfile() {
        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(this, "Running...");
        dialog.show();

        ApiInterface apiService = ApiClient.getLiveTvClient().create(ApiInterface.class);

        Call<UpdateProfileResponse> call = apiService.updateProfileOutput(strFullname, strEmail, strPassword, strMobi, shared.getUserId());
        call.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                int statusCode = response.code();

                if (statusCode == StatusCodes.OK) {
                    final UpdateProfileResponse updateProfileResponse = response.body();

                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (updateProfileResponse.getLIVETVforUpdateProfile().length > 0) {
                                dialog.dismiss();

                                LIVETVforUpdateProfile[] LIVETVforUpdateProfile = updateProfileResponse.getLIVETVforUpdateProfile();
                                for (int i = 0; i < LIVETVforUpdateProfile.length; i++) {
                                    Constants.GET_SUCCESS_MSG = Integer.valueOf(LIVETVforUpdateProfile[0].getSuccess());
                                }
                                setResult();
                                handler.removeCallbacksAndMessages(true);
                            } else {
                                handler.postDelayed(this, 100);
                            }
                        }
                    };
                    handler.postDelayed(runnable, 100);
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void getUserProfile() {
        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(this, "Running...");
        dialog.show();

        ApiInterface apiService = ApiClient.getLiveTvClient().create(ApiInterface.class);

        Call<GetUserProfileResponse> call = apiService.getUserProfileOutput(shared.getUserId());
        call.enqueue(new Callback<GetUserProfileResponse>() {
            @Override
            public void onResponse(Call<GetUserProfileResponse> call, Response<GetUserProfileResponse> response) {
                int statusCode = response.code();

                if (statusCode == StatusCodes.OK) {
                    final GetUserProfileResponse getUserProfileResponse = response.body();

                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (getUserProfileResponse.getLIVETVforGetUserProfile().length > 0) {
                                dialog.dismiss();

                                LIVETVforGetUserProfile[] LIVETVforGetUserProfile = getUserProfileResponse.getLIVETVforGetUserProfile();
                                for (int i = 0; i < LIVETVforGetUserProfile.length; i++) {
                                    edtFullName.setText(LIVETVforGetUserProfile[0].getName());
                                    edtEmail.setText(LIVETVforGetUserProfile[0].getEmail());
                                    edtMobile.setText(LIVETVforGetUserProfile[0].getPhone());
                                }
                                handler.removeCallbacksAndMessages(true);
                            } else {
                                handler.postDelayed(this, 100);
                            }
                        }
                    };
                    handler.postDelayed(runnable, 100);
                }
            }

            @Override
            public void onFailure(Call<GetUserProfileResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void setResult() {
        if (Constants.GET_SUCCESS_MSG == 0) {
            showToast("Failed");
            return;
        }
        showToast("Your Profile Updated");
    }
}
