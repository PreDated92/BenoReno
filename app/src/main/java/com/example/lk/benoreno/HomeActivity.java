package com.example.lk.benoreno;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
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
    private FragmentSettings _fragmentSettings = new FragmentSettings();
    private BottomNavigationView navigation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        commitFragmentTransaction(R.id.frame_layout, _fragmentHome);

        navigation = findViewById(R.id.navigation);

        HookButtonEventHandler();
    }

    private void HookButtonEventHandler() {
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                        selectedFragment = _fragmentSettings;
                        break;
                }

                if (selectedFragment != null) {
                    commitFragmentTransaction(R.id.frame_layout, selectedFragment, false, true);
                }

                return true;
            }
        });
    }

    public void commitFragmentTransaction(@IdRes int id, Fragment fragment, Boolean stack, Boolean clear) {

        if (clear){
            // Pop all stacks
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        if (stack){
            // Add to stack
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public void commitFragmentTransaction(@IdRes int id, Fragment fragment) {
        commitFragmentTransaction(id,fragment,false,false);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
