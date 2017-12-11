package com.example.louis.nursingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    Button btnConnect, btnDisconnect, btntemp, btnbp, btnpulse, btnhistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(this);
        btnDisconnect = (Button) findViewById(R.id.btnDisconnect);
        btnDisconnect.setOnClickListener(this);
        btntemp = (Button) findViewById(R.id.btntemp);
        btntemp.setOnClickListener(this);
        btnbp = (Button) findViewById(R.id.btnbp);
        btnbp.setOnClickListener(this);
        btnpulse = (Button) findViewById(R.id.btnpulse);
        btnpulse.setOnClickListener(this);
        btnhistory = (Button) findViewById(R.id.btnhistory);
        btnhistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConnect:
                startActivity(new Intent(MainActivity.this, BluetoothPage.class));
                break;
            case R.id.btnDisconnect:
                BluetoothPage.disconnect();
                Toast.makeText(getApplicationContext(), "No bluetooth detected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btntemp:
                Intent intent = new Intent(MainActivity.this, Temperature.class);
                startActivity(intent);
                break;
            case R.id.btnbp:
                intent = new Intent(MainActivity.this, BloodPressure.class);
                startActivity(intent);
                break;
            case R.id.btnpulse:
                intent = new Intent(MainActivity.this, Pulse.class);
                startActivity(intent);
                break;
            case R.id.btnhistory:
                intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
                break;

        }
    }

}
