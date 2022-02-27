package com.example.tfilm.Fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfilm.Login.SignInActivity;
import com.example.tfilm.PersonalInformationFragment;
import com.example.tfilm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingFragment extends Fragment {

    private TextView userInformation, accountManager, language;
    Button btn_logOut;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    public SettingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        TextView userInformation = view.findViewById(R.id.personal_Information);
        accountManager = view.findViewById(R.id.account_Management);
        language = view.findViewById(R.id.language);
        btn_logOut = view.findViewById(R.id.log_out);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        return view;

    }

//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(getActivity(), SignInActivity.class);
//        startActivity(intent);
//    }

}