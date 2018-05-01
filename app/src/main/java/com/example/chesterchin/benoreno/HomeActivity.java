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

    private FragmentHome _fragmentHome = new FragmentHome();
    private FragmentLock _fragmentLock = new FragmentLock();
    private FragmentNavigation _fragmentNavigation = new FragmentNavigation();
    private FragmentStatistics _fragmentStatistics = new FragmentStatistics();

    private BottomNavigationView.OnNavigationItemSelectedListener _onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = _fragmentHome;
                    break;
                case R.id.navigation_lock:
                    selectedFragment = _fragmentLock;
                    break;
                case R.id.navigation_navigation:
                    selectedFragment = _fragmentNavigation;
                    break;
                case R.id.navigation_statistics:
                    selectedFragment = _fragmentStatistics;
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
        commitFragmentTransaction(R.id.frame_layout, _fragmentHome);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(_onNavigationItemSelectedListener);
    }

    private void commitFragmentTransaction(@IdRes int id, Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }
}
