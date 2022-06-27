package com.project.read_pro.Fragment;

import static com.project.read_pro.utils.ProgressDialog.createAlertDialog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.project.read_pro.R;
import com.project.read_pro.databinding.FragmentProfileBinding;
import com.project.read_pro.model.User;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.utils.CartUtils;
import com.project.read_pro.view.MainActivity;
import com.project.read_pro.view_model.LogoutViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private User user;
    private LogoutViewModel logoutViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_profile, container, false);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = LoginUtils.getInstance(getActivity()).getUserInfo();
        logoutViewModel = new ViewModelProvider(getActivity()).get(LogoutViewModel.class);

        setUpView();

        setUpListener();


    }

    private void setUpListener() {
        binding.logout.setOnClickListener(view -> logoutUser());
    }

    private void setUpView() {
        binding.textName.setText(user.getName());
    }

    private void logoutUser(){
        AlertDialog alert = createAlertDialog(getActivity());
        String token = "Bearer " + LoginUtils.getInstance(getActivity()).getUserToken();
        logoutViewModel.logoutUser(token).observe(getActivity(), responseBody -> {
            if(responseBody != null){
                LoginUtils.getInstance(getActivity()).clearAll();
                alert.dismiss();
                Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                CartUtils.getInstance().clearProductInCart();
                gotoMainActivity();
            }
        });

    }

    private void gotoMainActivity(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}