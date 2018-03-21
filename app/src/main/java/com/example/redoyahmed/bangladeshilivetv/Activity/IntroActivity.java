package com.example.redoyahmed.bangladeshilivetv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.redoyahmed.bangladeshilivetv.R;
import com.example.redoyahmed.bangladeshilivetv.Services.LiveTvApplication;
import com.example.redoyahmed.bangladeshilivetv.Utils.CustomSharedPreference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {

    @BindView(R.id.button_sign_in)
    public Button btnSignIn;

    @BindView(R.id.button_sign_up)
    public Button btnSignUp;

    @BindView(R.id.button_skip)
    public Button btnSkip;

    public CustomSharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        initializeWidgets();
    }

    private void initializeWidgets() {
        sharedPreference = LiveTvApplication.getSharedPreference(getApplicationContext());
        if (sharedPreference.getSavedIsUserLoggedIn() == false) {
            btnSignUp.setOnClickListener(new ForSignUp());
            btnSignIn.setOnClickListener(new ForSignIn());
            btnSkip.setOnClickListener(new ForSkip());
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    class ForSignUp implements View.OnClickListener {
        ForSignUp() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(IntroActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        }
    }

    class ForSignIn implements View.OnClickListener {
        ForSignIn() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(IntroActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
    }

    class ForSkip implements View.OnClickListener {
        ForSkip() {
        }

        public void onClick(View v) {
            Intent intent = new Intent(IntroActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
