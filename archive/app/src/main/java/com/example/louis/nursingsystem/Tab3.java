package com.example.louis.nursingsystem;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Louis on 11/25/2015.
 */
public class Tab3 extends Activity {
    Button updpulse;
    TextView textpulse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BluetoothPage.gethandler(mHandler);
        setContentView(R.layout.tab_pulse);
        textpulse = (TextView) findViewById(R.id.textView13);

        updpulse = (Button)findViewById(R.id.updpulse);
        updpulse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (BluetoothPage.connectedThread != null)
                    BluetoothPage.connectedThread.write("E");
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

                    if(string.indexOf('E') == 0) {
                        string = string.replace("E", "");
                        textpulse.setText(string +"bpm");
                    }
                    break;

            }
        }
    };
}

