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

    public void saveIsFirstTimeOpening(boolean isFirstTime) {
        sharedPref.edit().putBoolean(Constants.FIRST_TIME_OPENING, isFirstTime).apply();
    }

    public void saveIsLogin(boolean flag) {
        sharedPref.edit().putBoolean(Constants.IS_LOGGED_IN, flag).apply();
    }

    public boolean getIsLogin() {
        return sharedPref.getBoolean(Constants.IS_LOGGED_IN, false);
    }

    public boolean getSavedIsFirstTimeOpening() {
        return sharedPref.getBoolean(Constants.FIRST_TIME_OPENING, true);
    }

    public void saveIsRemember(boolean flag) {
        sharedPref.edit().putBoolean(Constants.IS_LOGGED_REMEMBER, flag).apply();
    }

    public boolean getIsRemember() {
        return sharedPref.getBoolean(Constants.IS_LOGGED_REMEMBER, false);
    }

    public void saveRemember(String email, String password) {
        sharedPref.edit().putString(Constants.REMEMBER_EMAIL, email);
        sharedPref.edit().putString(Constants.REMEMBER_PASSWORD, password);
    }

    public String getRememberEmail() {
        return sharedPref.getString(Constants.REMEMBER_EMAIL, "");
    }

    public String getRememberPassword() {
        return sharedPref.getString(Constants.REMEMBER_PASSWORD, "");
    }

    public void saveLogin(String user_id, String user_name, String email) {
        sharedPref.edit().putString(Constants.USER_ID, user_id);
        sharedPref.edit().putString(Constants.USER_NAME, user_name);
        sharedPref.edit().putString(Constants.EMAIL, email);
    }

    public String getUserId() {
        return sharedPref.getString(Constants.USER_ID, "");
    }

    public String getUserName() {
        return sharedPref.getString(Constants.USER_NAME, "");
    }

    public String getUserEmail() {
        return sharedPref.getString(Constants.EMAIL, "");
    }
}
