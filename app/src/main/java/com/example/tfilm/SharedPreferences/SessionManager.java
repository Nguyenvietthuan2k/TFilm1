package com.example.tfilm.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    // Variables
    SharedPreferences sharedPreferences;
    // Creating an Editor object to edit(write to the file) -
    // Tạo một đối tượng trình soạn thảo để chỉnh sửa (ghi vào tệp)
    SharedPreferences.Editor editor;
    Context context;

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_FULLNAME = "fullname";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONENUMBER = "phonenumber";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DATE = "date";
    public static final String KEY_GENDER= "gender";

    public SessionManager(Context context1) {
        context = context1;
        sharedPreferences = context.getSharedPreferences("userLoginSession", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    // Create Session
    public void createLoginSession(String fullName, String userName, String email, String phoneNumber, String password, String age, String gender) {
        editor.putBoolean(IS_LOGIN, true);
        // Save editor
        editor.putString(KEY_FULLNAME, fullName);
        editor.putString(KEY_USERNAME, userName);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PHONENUMBER, phoneNumber);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_DATE, age);
        editor.putString(KEY_GENDER, gender);
        // Save
        editor.commit();
    }

    public HashMap<String, String> stringStringHashMap() {
        HashMap<String, String> userData = new HashMap<String, String>();

        userData.put(KEY_FULLNAME, sharedPreferences.getString(KEY_FULLNAME, null));
        userData.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));
        userData.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, null));
        userData.put(KEY_PHONENUMBER, sharedPreferences.getString(KEY_PHONENUMBER, null));
        userData.put(KEY_DATE, sharedPreferences.getString(KEY_DATE, null));
        userData.put(KEY_GENDER, sharedPreferences.getString(KEY_GENDER, null));

        return userData;
    }

    public boolean checkLogin() {
        if (sharedPreferences.getBoolean(IS_LOGIN, false)) {
            return true;
        }else {
            return false;
        }
    }

    public void logoutUserFromSession() {
        editor.clear();
        editor.commit();
    }


}
