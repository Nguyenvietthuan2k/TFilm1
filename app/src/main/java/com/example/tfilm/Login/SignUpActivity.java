package com.example.tfilm.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfilm.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
///// Đây là màn hình đăng kí
public class SignUpActivity extends AppCompatActivity {

    private EditText signUpName, signUpEmail, signUpPassword;
    private Button signUp;
    private TextView signIn;
    private ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initUi();
        initListener();
        initSignIn();
        mAuth = FirebaseAuth.getInstance();

//        isValidEmail();
        // Get all the values in String

    }

    private void initSignIn() {
        signIn = findViewById(R.id.sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUi() {

        signUpName = findViewById(R.id.edt_name);
        signUpEmail = findViewById(R.id.edt_email);
        signUpPassword = findViewById(R.id.edt_password);
        signUp = findViewById(R.id.btn_Sign_Up);


        progressDialog = new ProgressDialog(this);
    }

    private void initListener() {
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignUp();
            }
        });
    }

    private void onClickSignUp() {
        mAuth = FirebaseAuth.getInstance();
        String email = signUpEmail.getText().toString().trim();
        String password = signUpPassword.getText().toString().trim();

        if(email.isEmpty()) {
            signUpPassword.setError("Email is required!");
            signUpPassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signUpEmail.setError("Please provide valid email");
            signUpEmail.requestFocus();
            return;
        }
        if(password.isEmpty()) {
            signUpPassword.setError("");
            signUpPassword.requestFocus();
            return;
        }
        if(password.length() < 7) {
            signUpPassword.setError("");
            signUpPassword.requestFocus();
            return;
        }

        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
                            progressDialog.dismiss();
                            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private boolean isValidName() {
        String name = signUpName.getText().toString().trim();
        if (name.isEmpty()) {
            signUpName.setError("Field cannot be empty");
            return false;
        }else {
            signUpName.setError(null);
            return true;
        }
    }
    private boolean isValidEmail() {
        String email = signUpEmail.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (email.isEmpty()) {
            signUpName.setError("Field cannot be empty");
            return false;
        }else if (!email.matches(emailPattern)) {
            signUpName.setError("Invalid email address");
            return false;
        }else {
            signUpEmail.setError(null);
            return true;
        }
    }
    private boolean isValidPassword() {
        String password = signUpPassword.getText().toString().trim();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" +     // any letter
                "(?=.*[@#$%^&+=])" +  // at least 1 special character
                "(?=\\s+$)" +        // no white spaces
                ".{4,}" +           // at least 4 characters
                "$";
        if (password.isEmpty()) {
            signUpPassword.setError("Field cannot be empty");
            return false;
        }
        else if (!password.matches(passwordVal)) {
            signUpPassword.setError("Password is too weak");
            return false;
        }else {
            signUpPassword.setError(null);
            return true;
        }
    }

}