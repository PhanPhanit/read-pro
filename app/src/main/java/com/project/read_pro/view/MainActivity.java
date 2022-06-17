package com.project.read_pro.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showFragment(new HomeFragment());
        BadgeDrawable badgeDrawable = binding.bnvMain.getOrCreateBadge(R.id.menu_notification);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        setUpListener();


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