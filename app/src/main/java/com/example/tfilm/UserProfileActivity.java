package com.example.tfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.tfilm.Adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

//        editUser = findViewById(R.id.user_email);
//        edtPassword = findViewById(R.id.user_name);

//        editUser.setText(getIntent().getStringExtra("Name"));
//        edtPassword.setText(getIntent().getStringExtra("Password"));

//        userName.setText(getIntent().getStringExtra("Name"));


    }
}