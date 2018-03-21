package com.example.redoyahmed.bangladeshilivetv.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.redoyahmed.bangladeshilivetv.R;
import com.example.redoyahmed.bangladeshilivetv.Services.LiveTvApplication;
import com.example.redoyahmed.bangladeshilivetv.Utils.CustomSharedPreference;
import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    private CustomSharedPreference shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.onboarding_main_layout);

        shared = LiveTvApplication.getSharedPreference(getApplicationContext());

        if (shared.getSavedIsFirstTimeOpening() == true) {
            initializeWidgets();
            loadData();
        } else {
            startActivity(new Intent(getApplicationContext(), IntroActivity.class));
            finish();
        }
    }

    private void initializeWidgets() {
        ButterKnife.bind(this);
    }

    private void loadData() {
        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(R.id.onboardingRootView), getDataForOnBoarding(), getApplicationContext());
        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                shared.saveIsFirstTimeOpening(false);
                startActivity(new Intent(getApplicationContext(), IntroActivity.class));
                finish();
            }
        });
    }

    private ArrayList<PaperOnboardingPage> getDataForOnBoarding() {
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Live Sports", "A simple way to watch Live Sports", Color.parseColor("#9B90BC"), R.mipmap.app_logo, R.drawable.ic_menu_send);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Live News", "A simple way to watch Live News", Color.parseColor("#678FB4"), R.mipmap.app_logo, R.drawable.ic_menu_send);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Live Entertainment", "A simple way to watch Live Entertainment", Color.parseColor("#65B0B4"), R.mipmap.app_logo, R.drawable.ic_menu_send);

        ArrayList<PaperOnboardingPage> elements = new ArrayList();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }
}
