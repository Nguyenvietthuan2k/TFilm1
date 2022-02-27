package com.example.tfilm.Firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tfilm.Login.SignInActivity;
import com.example.tfilm.R;

public class NewPasswordActivity extends AppCompatActivity {

    private EditText edtNewPassword, edtEnterNewPassword;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        edtNewPassword = findViewById(R.id.input_new_password);
        edtEnterNewPassword = findViewById(R.id.input_reset_new_password);
        btnCreate = findViewById(R.id.create_new_password);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}