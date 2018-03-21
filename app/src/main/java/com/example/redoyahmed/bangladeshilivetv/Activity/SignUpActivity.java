package com.example.redoyahmed.bangladeshilivetv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redoyahmed.bangladeshilivetv.Model.LIVETV;
import com.example.redoyahmed.bangladeshilivetv.Model.RegisterResponse;
import com.example.redoyahmed.bangladeshilivetv.R;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiClient;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiInterface;
import com.example.redoyahmed.bangladeshilivetv.Utils.ConnectionStatus;
import com.example.redoyahmed.bangladeshilivetv.Utils.Constants;
import com.example.redoyahmed.bangladeshilivetv.Utils.CustomSweetAlertDialog;
import com.example.redoyahmed.bangladeshilivetv.Utils.StatusCodes;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
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

public class SignUpActivity extends AppCompatActivity implements ValidationListener {

    @BindView(R.id.button)
    Button btnSignUp;

    @Email(message = "Please Check and Enter a valid Email Address")
    @NotEmpty
    @BindView(R.id.edt_email)
    EditText edtEmail;

    @NotEmpty
    @BindView(R.id.edt_name)
    EditText edtFullName;

    @BindView(R.id.edt_contact_no)
    EditText edtMobile;

    @Password(message = "Enter a Valid Password")
    @NotEmpty
    @BindView(R.id.edt_password)
    EditText edtPassword;

    String strEmail;
    String strFullname;
    String strMessage;
    String strMobi;
    String strPassword;

    @BindView(R.id.txt_create)
    TextView txtLogin;

    private Validator validator;

    private static final String TAG = SignUpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        btnSignUp.setOnClickListener(new SignUpClass());
        txtLogin.setOnClickListener(new LogInClass());

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void signUp() {
        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(this, "Running...");
        dialog.show();

        ApiInterface apiService = ApiClient.getSignUpClient().create(ApiInterface.class);

        Call<RegisterResponse> call = apiService.requestOutput(strFullname, strEmail, strPassword, strMobi);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                int statusCode = response.code();

                if (statusCode == StatusCodes.OK) {
                    final RegisterResponse registerResponse = response.body();

                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (registerResponse.getLIVETV().length > 0) {
                                dialog.dismiss();

                                LIVETV[] livetv = registerResponse.getLIVETV();
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
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    class SignUpClass implements View.OnClickListener {
        SignUpClass() {
        }

        public void onClick(View v) {
            SignUpActivity.this.validator.validate();
        }
    }

    class LogInClass implements View.OnClickListener {
        LogInClass() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(SignUpActivity.this.getApplicationContext(), SignInActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onValidationSucceeded() {
        strFullname = edtFullName.getText().toString().replace(" ", "%20");
        strEmail = edtEmail.getText().toString();
        strPassword = edtPassword.getText().toString();
        strMobi = this.edtMobile.getText().toString();

        if (ConnectionStatus.getInstance(this).isOnline()) {
            signUp();
        } else {
            showToast(getString(R.string.conne_msg1));
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setResult() {
        if (Constants.GET_SUCCESS_MSG == 0) {
            edtEmail.setText("");
            edtEmail.requestFocus();
            showToast(strMessage);
            return;
        }
        showToast(strMessage);
        Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivity(intent);
        finish();
    }

}
