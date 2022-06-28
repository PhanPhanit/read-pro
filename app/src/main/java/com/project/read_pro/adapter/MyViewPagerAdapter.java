package com.project.read_pro.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.project.read_pro.Fragment.BoughtFragment;
import com.project.read_pro.Fragment.SaveFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new SaveFragment();
            default:
                return new BoughtFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
