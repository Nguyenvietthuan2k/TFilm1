package com.example.tfilm.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.tfilm.Fragment.SettingFragment;
import com.example.tfilm.Fragment.VipFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new VipFragment();
            case 1:
                return new SettingFragment();
            default:
                return new VipFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String tittle= "";
        switch (position) {
            case 0:
                tittle = "ĐƠN HÀNG";
                break;
            case 1:
                tittle = "CÀI ĐẶT";
        }
        return tittle;
    }
}
