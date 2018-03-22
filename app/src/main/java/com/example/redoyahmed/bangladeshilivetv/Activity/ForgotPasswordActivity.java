package com.example.redoyahmed.bangladeshilivetv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.redoyahmed.bangladeshilivetv.Model.ForgotPasswordResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.LIVETVforForgotPassword;
import com.example.redoyahmed.bangladeshilivetv.R;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiClient;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiInterface;
import com.example.redoyahmed.bangladeshilivetv.Utils.ConnectionStatus;
import com.example.redoyahmed.bangladeshilivetv.Utils.Constants;
import com.example.redoyahmed.bangladeshilivetv.Utils.CustomSweetAlertDialog;
import com.example.redoyahmed.bangladeshilivetv.Utils.StatusCodes;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class ForgotPasswordActivity extends AppCompatActivity implements Validator.ValidationListener {

    @BindView(R.id.button)
    Button btnSubmit;

    @Email(message = "Please Check and Enter a valid Email Address")
    @NotEmpty
    @BindView(R.id.edt_email)
    EditText edtEmail;

    String strEmail;
    String strMessage;

    private Validator validator;

    private static final String TAG = ForgotPasswordActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        btnSubmit.setOnClickListener(new forgotPasswordClass());
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        strEmail = edtEmail.getText().toString();
        if (ConnectionStatus.getInstance(this).isOnline()) {
            forgotPassword();
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
                Toast.makeText(this, message, LENGTH_LONG).show();
                Toast.makeText(this, "Record Not Saved", LENGTH_LONG).show();
            }
        }
    }

    private void forgotPassword() {
        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(this, "Running...");
        dialog.show();

        ApiInterface apiService = ApiClient.getLiveTvClient().create(ApiInterface.class);

        Call<ForgotPasswordResponse> call = apiService.forgotPasswordOutput(strEmail);
        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {
                int statusCode = response.code();

                if (statusCode == StatusCodes.OK) {
                    final ForgotPasswordResponse forgotPasswordResponse = response.body();

                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (forgotPasswordResponse.getLIVETVforForgotPassword().length > 0) {
                                dialog.dismiss();

                                LIVETVforForgotPassword[] livetv = forgotPasswordResponse.getLIVETVforForgotPassword();
                                for (int i = 0; i < livetv.length; i++) {
                                    strMessage = livetv[0].getMsg();
                                    Constants.GET_SUCCESS_MSG = Integer.valueOf(livetv[0].getSuccess());
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
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    class forgotPasswordClass implements View.OnClickListener {
        forgotPasswordClass() {
        }

        public void onClick(View v) {
            ForgotPasswordActivity.this.validator.validate();
        }
    }

    public void setResult() {
        if (Constants.GET_SUCCESS_MSG == 0) {
            showToast("Opps. \n" + strMessage);
            edtEmail.setText("");
            edtEmail.requestFocus();
            return;
        }
        Intent intentco = new Intent(this, SignInActivity.class);
        startActivity(intentco);
        finish();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
