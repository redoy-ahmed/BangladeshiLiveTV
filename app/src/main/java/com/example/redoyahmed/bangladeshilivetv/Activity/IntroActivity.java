package com.example.redoyahmed.bangladeshilivetv.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.redoyahmed.bangladeshilivetv.Fragment.IntroFragment;
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

    @BindView(R.id.circle1)
    public View circle1;

    @BindView(R.id.circle2)
    public View circle2;

    @BindView(R.id.circle3)
    public View circle3;

    @BindView(R.id.circle4)
    public View circle4;

    @BindView(R.id.view_pager)
    public ViewPager pager;

    public PagerAdapter pagerAdapter;
    public CustomSharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        initializeWidgets();
    }

    private void initializeWidgets() {
        sharedPreference = LiveTvApplication.getSharedPreference(getApplicationContext());
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        setIndicator(0);

        pager.addOnPageChangeListener(new ForPager());
        btnSignUp.setOnClickListener(new ForSignUp());
        btnSignIn.setOnClickListener(new ForSignIn());
        btnSkip.setOnClickListener(new ForSkip());
    }

    private void setIndicator(int index) {
        switch (index) {
            case 0:
                this.circle1.setBackgroundResource(R.drawable.circle_fill);
                this.circle2.setBackgroundResource(R.drawable.circle);
                this.circle3.setBackgroundResource(R.drawable.circle);
                this.circle4.setBackgroundResource(R.drawable.circle);
                return;
            case 1:
                this.circle2.setBackgroundResource(R.drawable.circle_fill);
                this.circle1.setBackgroundResource(R.drawable.circle);
                this.circle3.setBackgroundResource(R.drawable.circle);
                this.circle4.setBackgroundResource(R.drawable.circle);
                return;
            case 2:
                this.circle3.setBackgroundResource(R.drawable.circle_fill);
                this.circle1.setBackgroundResource(R.drawable.circle);
                this.circle2.setBackgroundResource(R.drawable.circle);
                this.circle4.setBackgroundResource(R.drawable.circle);
                return;
            case 3:
                this.circle4.setBackgroundResource(R.drawable.circle_fill);
                this.circle1.setBackgroundResource(R.drawable.circle);
                this.circle3.setBackgroundResource(R.drawable.circle);
                this.circle2.setBackgroundResource(R.drawable.circle);
                return;
            default:
                return;
        }
    }

    class ForPager implements ViewPager.OnPageChangeListener {
        ForPager() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            IntroActivity.this.setIndicator(position);
        }

        public void onPageScrollStateChanged(int state) {
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

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return IntroFragment.newInstance(position);
        }

        public int getCount() {
            return 4;
        }
    }
}
