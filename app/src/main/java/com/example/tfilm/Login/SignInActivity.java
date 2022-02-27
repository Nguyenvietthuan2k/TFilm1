package com.example.tfilm.Login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfilm.MainActivity;
import com.example.tfilm.Otp.ForgotPasswordActivity;
import com.example.tfilm.R;
import com.example.tfilm.SharedPreferences.SessionManager;
import com.example.tfilm.SplashActivity;
import com.example.tfilm.UserProfileActivity;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.Arrays;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

public class SignInActivity extends AppCompatActivity {

    private EditText signInEmail, signInPassword;
    private Button signIn;
    private TextView  SignUp, forgotPassword;
    private FirebaseAuth firebaseAuth;
    private CallbackManager callbackManager;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        callSignIn();
        callSignUp();
        callForgetPassword();
        checkInternet();
    }
    // Function to call the Sign In
    private void callSignIn() {
        signInEmail = findViewById(R.id.edt_sign_in_email);
        signInPassword = findViewById(R.id.edt_sign_in_password);
        signIn = findViewById(R.id.btn_SignIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn();
            }
        });
        // Set soft input mode
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        // Open soft keyboard
        showKeyboard(signInEmail, signInPassword);
        signInEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                // Get value from editText
                String s = signInEmail.getText().toString().trim();
                // Check condition
                if (i == EditorInfo.IME_ACTION_DONE) {
                    // When action is equal to action done
                    // Hide keyboard
                    hideKeyboard(signInEmail);
                    // Display toast
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }
    // Login the user in app
    private void SignIn() {
        // Get data
        String email = signInEmail.getText().toString().trim();
        String password = signInPassword.getText().toString().trim();
        // Validate email and password
        if(email.isEmpty()) {
            signInPassword.setError("Email is required!");
            signInPassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signInEmail.setError("Please provide valid email");
            signInEmail.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            signInPassword.setError("");
            signInPassword.requestFocus();
            return;
        }
        if(password.length() < 5) {
            signInPassword.setError("");
            signInPassword.requestFocus();
            return;
        }

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.putExtra("Name", email);
                            intent.putExtra("Password", password);
                            startActivity(intent);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            finishAffinity();
                        }else {
                            Toast.makeText(SignInActivity.this, "Tên tài khoản hoặc mật khẩu không chính xác!!!! Vui lòng thử lại.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // Create a Session
//        SessionManager sessionManager = new SessionManager(SignInActivity.this);
//        sessionManager.createLoginSession(fullName, userName, email, phoneName, password, date, gender);
        // Get user data from firebase database
    }
    private void hideKeyboard(EditText signInEmail) {
        // Initialize input manager
        InputMethodManager methodManager = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE
        );
        // Hide soft keyboard
        methodManager.hideSoftInputFromWindow(signInEmail.getApplicationWindowToken(), 0);
    }
    // Function to call the SignUp Screen
    private void callSignUp() {
        SignUp = findViewById(R.id.sign_up);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    // Function to call the Forgot Password Screen
    private void callForgetPassword() {
        forgotPassword = findViewById(R.id.forgot_Password);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void showKeyboard(EditText signInEmail, EditText signInPassword) {
        // Initialize input manager
        InputMethodManager methodManager = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        // Show soft keyboard
        methodManager.showSoftInput(signInEmail.getRootView(),InputMethodManager.SHOW_IMPLICIT);
        //Focus on editText
        signInEmail.requestFocus();
        signInPassword.requestFocus();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Create a shared pref object
        // With a file name "MySharedPref"
        // In private mode
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // write all the data entered by the user in SharedPreference and apply -
        // Viết tất cả dữ liệu được nhập bởi người dùng trong SharedPreference và áp dụng
        editor.putString("name", signInEmail.getText().toString());
        editor.putString("password", signInPassword.getText().toString());
        editor.apply();

    }

    /*
    Check
    Internet
    Connection
     */
     
    private void checkInternet() {
        if (!isConnected(this)) {
            showCustomDialog();
        }
    }

    private boolean isConnected(SignInActivity signInActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) signInActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo networkMobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (networkWifi != null && networkWifi.isConnected() || (networkMobile != null && networkMobile.isConnected())) {
            return true;
        }else {
            return false;
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
        builder.setMessage("Please connect to the internet to proceed further")
                .setCancelable(false)
                .setPositiveButton("Connnect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), SplashActivity.class));
                        finish();
                    }
                });
    }
}