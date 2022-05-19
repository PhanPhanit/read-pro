package com.project.read_pro.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.read_pro.R;
import com.project.read_pro.databinding.FragmentNotificationBinding;


public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_notification, container, false);

        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}