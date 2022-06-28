package com.project.read_pro.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.read_pro.R;
import com.project.read_pro.databinding.FragmentBoughtBinding;


public class BoughtFragment extends Fragment {
    private FragmentBoughtBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBoughtBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}