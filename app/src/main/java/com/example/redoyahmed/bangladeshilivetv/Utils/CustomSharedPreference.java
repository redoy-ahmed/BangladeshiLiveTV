package com.example.redoyahmed.bangladeshilivetv.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CustomSharedPreference {
    private SharedPreferences sharedPref;

    public CustomSharedPreference(Context context) {
        sharedPref = context.getSharedPreferences(Constants.SHARED_PREF, 0);
    }

    public SharedPreferences getInstanceOfSharedPreference() {
        return sharedPref;
    }

    public void setUserData(String userData) {
        sharedPref.edit().putString(Constants.USER_DATA, userData).apply();
    }

    public String getUserData() {
        return sharedPref.getString(Constants.USER_DATA, "");
    }

    public void saveSound(boolean sound) {
        sharedPref.edit().putBoolean(Constants.SOUND, sound).apply();
    }

    public boolean getSavedSound() {
        return sharedPref.getBoolean(Constants.SOUND, false);
    }

    public void saveMusic(boolean music) {
        sharedPref.edit().putBoolean(Constants.MUSIC, music).apply();
    }

    public boolean getSavedMusic() {
        return sharedPref.getBoolean(Constants.MUSIC, false);
    }

    public void saveLanguage(String language) {
        sharedPref.edit().putString(Constants.LANGUAGE, language).apply();
    }

    public String getSavedLanguage() {
        return sharedPref.getString(Constants.LANGUAGE, "English");
    }

    public void setCheckQuiz(String quiz) {
        sharedPref.edit().putString(Constants.CHECK_QUIZ, quiz).apply();
    }

    public String getCheckQuiz() {
        return sharedPref.getString(Constants.CHECK_QUIZ, "");
    }

    public void followQuiz(String quizzes) {
        sharedPref.edit().putString(Constants.FOLLOW_QUIZ, quizzes).apply();
    }

    public String getFollowedQuizzes() {
        return sharedPref.getString(Constants.FOLLOW_QUIZ, "");
    }

    public void saveIsUserLoggedIn(boolean isFirstTime) {
        sharedPref.edit().putBoolean(Constants.IS_USER_LOGGED_IN, isFirstTime).apply();
    }

    public boolean getSavedIsUserLoggedIn() {
        return sharedPref.getBoolean(Constants.IS_USER_LOGGED_IN, false);
    }

    public void saveIsFirstTimeOpening(boolean isFirstTime) {
        sharedPref.edit().putBoolean(Constants.FIRST_TIME_OPENING, isFirstTime).apply();
    }

    public boolean getSavedIsFirstTimeOpening() {
        return sharedPref.getBoolean(Constants.FIRST_TIME_OPENING, true);
    }
}
