package com.project.read_pro.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.project.read_pro.Fragment.HomeFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.project.read_pro.Fragment.MyLibraryFragment;
import com.project.read_pro.Fragment.NotificationFragment;
import com.project.read_pro.Fragment.ProfileFragment;
import com.project.read_pro.Fragment.SearchFragment;
import com.project.read_pro.R;
import com.project.read_pro.databinding.ActivityMainBinding;
import com.project.read_pro.storage.LoginUtils;
import com.project.read_pro.view_model.ShowCurrentUserViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ShowCurrentUserViewModel showCurrentUserViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        showCurrentUserViewModel = new ViewModelProvider(this).get(ShowCurrentUserViewModel.class);

        showCurrentUser();


        showFragment(new HomeFragment());
        BadgeDrawable badgeDrawable = binding.bnvMain.getOrCreateBadge(R.id.menu_notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        setUpListener();


    }

    private void showCurrentUser() {
        //String token = "Bearer " + LoginUtils.getInstance(this).getUserToken();
        String token = "Bearer 2|AUDBZv4W7wATk3Cu2qxKZ7Ws3d3gTxSC8ALhf4Ml";
        showCurrentUserViewModel.ShowCurrentUser(token).observe(this, showCurrentUserResponse -> {
            if(showCurrentUserResponse != null){
                if(showCurrentUserResponse.getCode() == 200){
                    LoginUtils.getInstance(this).saveUserInfo(showCurrentUserResponse.getUser());
                }else{
                    LoginUtils.getInstance(this).clearAll();
                }
                binding.loadingScreen.setVisibility(View.GONE);
            }
        });

    }

    private void setUpListener() {
        binding.bnvMain.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.menu_home:
                    showFragment(new HomeFragment());
                    break;
                case R.id.menu_my_library:
                    showFragment(new MyLibraryFragment());
                    break;
                case R.id.menu_search:
                    showFragment(new SearchFragment());
                    break;
                case R.id.menu_notification:
                    showFragment(new NotificationFragment());
                    break;
                case R.id.menu_profile:
                    if(!LoginUtils.getInstance(this).isLoggedIn()){
                        startActivity(new Intent(this,LoginActivity.class));
                    }else{
                        showFragment(new ProfileFragment());
                    }
                    break;
                default:
            }
            return true;
        });
    }
    private  void message(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    private void showFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fgMain, fragment);
        fragmentTransaction.commit();
    }
}