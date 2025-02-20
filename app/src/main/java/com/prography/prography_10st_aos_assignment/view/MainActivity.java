package com.prography.prography_10st_aos_assignment.view;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.prography.prography_10st_aos_assignment.R;
import com.prography.prography_10st_aos_assignment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    public String TAG = getClass().toString();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        initBottomNav();
        initStatusBar();
    }

    private void initBottomNav(){
        BottomNavigationView navView = binding.navView;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment != null) {
            NavController navController = navHostFragment.getNavController();
            NavigationUI.setupWithNavController(navView, navController);

            // BottomNavigationView 클릭 시 중복 클릭 방지
            navView.setOnItemSelectedListener(item -> {
                item.setEnabled(false);
                new Handler().postDelayed(() -> item.setEnabled(true), 500);
                return NavigationUI.onNavDestinationSelected(item, navController);
            });
        } else {
            // navHostFragment가 null인 경우에 대한 처리
            Log.e(TAG, "NavHostFragment not found");
        }
    }

    private void initStatusBar(){
        // 상태바 세팅
        Window window = getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white));
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}
