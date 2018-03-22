package com.example.redoyahmed.bangladeshilivetv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redoyahmed.bangladeshilivetv.Model.LIVETVforSignIn;
import com.example.redoyahmed.bangladeshilivetv.Model.SignInResponse;
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

import static android.widget.Toast.LENGTH_LONG;

public class SignInActivity extends AppCompatActivity implements Validator.ValidationListener {

    String strEmail;
    String strMessage;
    String strName;
    String strPassengerId;
    String strPassword;
    private Validator validator;
    private CustomSharedPreference shared;

    @BindView(R.id.button)
    Button btnSingIn;

    @BindView(R.id.checkBox)
    CheckBox checkBox;

    @Email(message = "Please Check and Enter a valid Email Address")
    @NotEmpty
    @BindView(R.id.edt_email)
    EditText edtEmail;

    @Password(message = "Enter a Valid Password")
    @NotEmpty
    @BindView(R.id.edt_password)
    EditText edtPassword;

    @BindView(R.id.textView_forget)
    TextView textForgot;

    @BindView(R.id.txt_create)
    TextView textSignUp;

    private static final String TAG = SignInActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);

        shared = LiveTvApplication.getSharedPreference(getApplicationContext());

        btnSingIn.setOnClickListener(new SignInClass());
        textForgot.setOnClickListener(new ForgotPasswordClass());
        textSignUp.setOnClickListener(new SignUpClass());
        if (shared.getIsRemember()) {
            checkBox.setChecked(true);
            edtEmail.setText(shared.getRememberEmail());
            edtPassword.setText(shared.getRememberPassword());
        }
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    class SignInClass implements View.OnClickListener {
        SignInClass() {
        }

        public void onClick(View v) {
            validator.validate();
        }
    }

    class ForgotPasswordClass implements View.OnClickListener {
        ForgotPasswordClass() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
            startActivity(intent);
        }
    }

    class SignUpClass implements View.OnClickListener {
        SignUpClass() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onValidationSucceeded() {
        strEmail = edtEmail.getText().toString();
        strPassword = edtPassword.getText().toString();
        if (checkBox.isChecked()) {
            shared.saveIsRemember(true);
            shared.saveRemember(strEmail, strPassword);
        } else {
            shared.saveIsRemember(false);
        }
        if (ConnectionStatus.getInstance(this).isOnline()) {
            signIn();
        } else {
            showToast(getString(R.string.conne_msg1));
        }
    }

    private void signIn() {
        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(this, "Running...");
        dialog.show();

        ApiInterface apiService = ApiClient.getLiveTvClient().create(ApiInterface.class);

        Call<SignInResponse> call = apiService.signInOutput(strEmail, strPassword);
        call.enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if (response.code() == StatusCodes.OK) {
                    final SignInResponse signInResponse = response.body();

                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (signInResponse.getLIVETVforSignIn().length > 0) {
                                dialog.dismiss();

                                LIVETVforSignIn[] livetv = signInResponse.getLIVETVforSignIn();
                                for (int i = 0; i < livetv.length; i++) {
                                    if (livetv[0].getSuccess().equals("0")) {
                                        strMessage = "Login failed";
                                        Constants.GET_SUCCESS_MSG = 0;
                                    } else {
                                        strName = livetv[0].getName();
                                        strPassengerId = livetv[0].getUser_id();
                                        Constants.GET_SUCCESS_MSG = 1;
                                    }
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
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
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

    public void setResult() {
        if (Constants.GET_SUCCESS_MSG == 0) {
            showToast("Wrong email or password");
            return;
        }
        shared.saveIsLogin(true);
        shared.saveLogin(strPassengerId, strName, strEmail);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, LENGTH_LONG).show();
    }
}
