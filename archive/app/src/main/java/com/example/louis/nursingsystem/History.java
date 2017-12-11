package com.example.louis.nursingsystem;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by Louis on 11/25/2015.
 */
public class History extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tid1");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tid2");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("tid3");

        tab1.setIndicator("Temp").setContent(new Intent(getApplicationContext(),Tab1.class));
        tab2.setIndicator("BP").setContent(new Intent(getApplicationContext(),Tab2.class));
        tab3.setIndicator("Pulse").setContent(new Intent(getApplicationContext(),Tab3.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
    }
}

