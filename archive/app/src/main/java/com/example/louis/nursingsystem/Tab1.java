package com.example.louis.nursingsystem;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;

/**
 * Created by Louis on 11/25/2015.
 */
public class Tab1 extends Activity {
    Button updtemp;
    TextView texttemp;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            BluetoothPage.gethandler(mHandler);
            setContentView(R.layout.tab_temp);
            texttemp = (TextView) findViewById(R.id.textView4);

        updtemp = (Button)findViewById(R.id.updtemp);
        updtemp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (BluetoothPage.connectedThread != null)
                    BluetoothPage.connectedThread.write("A");
            }

        });
    }

    @Override
    public void onBackPressed() {
        if (BluetoothPage.connectedThread != null) BluetoothPage.connectedThread.write("Q");
        super.onBackPressed();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

                case BluetoothPage.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    String string = new String(readBuf);

                    if(string.indexOf('F') == 0) {
                        string = string.replace("F", "");
                        texttemp.setText(string + "C");
                    }
                    break;

            }
        }
    };
}

