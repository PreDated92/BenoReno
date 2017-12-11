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
public class Tab2 extends Activity {
    Button updbp;
    TextView textbp,textbp1,textbp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BluetoothPage.gethandler(mHandler);
        setContentView(R.layout.tab_bp);
        textbp = (TextView) findViewById(R.id.textView41);
        textbp1 = (TextView) findViewById(R.id.textView11);
        textbp2 = (TextView) findViewById(R.id.textView12);

        updbp = (Button)findViewById(R.id.updbp);
        updbp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (BluetoothPage.connectedThread != null)
                    BluetoothPage.connectedThread.write("C");
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

                    if(string.indexOf('A') == 0) {
                    string = string.replace("A", "");
                    textbp.setText("MAP: " + string + " mmHg");
                }else if(string.indexOf('B') == 0) {
                        string = string.replace("B", "");
                        textbp1.setText("Sys: " + string + " mmHg");
                    }else  if(string.indexOf('C') == 0) {
                        string = string.replace("C", "");
                        textbp2.setText("Dia: " + string + " mmHg");
                    }
                    break;

            }
        }
    };
}