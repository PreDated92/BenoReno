package com.example.chesterchin.benoreno;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

public class HomeActivity extends Activity {

    private HomeFragment _homeFragment = new HomeFragment();
    private LockFragment _lockFragment = new LockFragment();
    private NavigationFragment _navigationFragment = new NavigationFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener _onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = _homeFragment;
                    break;
                case R.id.navigation_lock:
                    selectedFragment = _lockFragment;
                    break;
                case R.id.navigation_navigation:
                    selectedFragment = _navigationFragment;
                    break;
                case R.id.navigation_statistics:
                    break;
                case R.id.navigation_settings:
                    break;
            }

            if (selectedFragment != null)
            {
                commitFragmentTransaction(R.id.frame_layout, selectedFragment);
            }

            return true;
        }

    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        commitFragmentTransaction(R.id.frame_layout, _homeFragment);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(_onNavigationItemSelectedListener);
    }

    private void commitFragmentTransaction(@IdRes int id, Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }
}
