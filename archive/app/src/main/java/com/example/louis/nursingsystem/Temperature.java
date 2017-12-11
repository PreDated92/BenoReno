package com.example.louis.nursingsystem;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jjoe64.graphview.GraphView;


import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

/**
 * Created by Louis on 11/25/2015.
 */
public class Temperature extends Activity implements View.OnClickListener {

    Button bXminus, bXplus;
    ToggleButton tbLock, tbStream, tbScroll;
    static boolean Lock, Stream, AutoScrollX;
    TextView text,indi;
    static LinearLayout GraphView;
    static com.jjoe64.graphview.GraphView graphView;
    static GraphViewSeries Series;
    private static double graph2LastXValue = 0;
    private static int Xview = 10;

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
                case BluetoothPage.SUCCESS_CONNECT:
                    BluetoothPage.connectedThread = new BluetoothPage.ConnectedThread((BluetoothSocket) msg.obj);
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                    String s = "Successfully Connected";
                    BluetoothPage.connectedThread.start();
                    break;
                case BluetoothPage.MESSAGE_READ:
                    byte[] readBuf = (byte[]) msg.obj;
                    String strIncom = new String(readBuf, 0, 5);


                    if (strIncom.indexOf('s') == 0 && strIncom.indexOf('.') == 2) {
                        strIncom = strIncom.replace("s", "");
                        if (isFloatNumber(strIncom)) {
                            Series.appendData(new GraphView.GraphViewData(graph2LastXValue, Double.parseDouble(strIncom)), AutoScrollX);

                            if (graph2LastXValue >= Xview && Lock == true) {
                                Series.resetData(new GraphView.GraphViewData[]{});
                                graph2LastXValue = 0;
                            } else graph2LastXValue += 0.1;
                            if (Lock == true) graphView.setViewPort(0, Xview);
                            else graphView.setViewPort(graph2LastXValue - Xview, Xview);

                            GraphView.removeView(graphView);
                            GraphView.addView(graphView);
                        }
                    }

                    else if(strIncom.indexOf('C')==0 ){
                        strIncom = strIncom.replace("C", "");
                        TextView text=(TextView)findViewById(R.id.textView3);
                        text.setText(strIncom + " C");
                        indi.setText("Cold");
                    }
                    else if(strIncom.indexOf('N')==0){
                        strIncom = strIncom.replace("N", "");
                        TextView text=(TextView)findViewById(R.id.textView3);
                        text.setText(strIncom + " C");
                        indi.setText("Normal");
                    }
                    else if(strIncom.indexOf('H')==0){
                        strIncom = strIncom.replace("H", "");
                        TextView text=(TextView)findViewById(R.id.textView3);
                        text.setText(strIncom + " C");
                        indi.setText("Hot");
                    }
                    break;
            }
        }

        public boolean isFloatNumber(String num) {
            try {
                Double.parseDouble(num);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_temperature);

        LinearLayout background = (LinearLayout) findViewById(R.id.bg);
        background.setBackgroundColor(Color.BLACK);
        init();
        Buttoninit();

    }

    private void init() {

        BluetoothPage.gethandler(mHandler);
        GraphView = (LinearLayout) findViewById(R.id.GraphTemp);
        Series = new GraphViewSeries("Signal",
                new GraphViewSeries.GraphViewStyle(Color.YELLOW, 2),
                new GraphView.GraphViewData[]{new GraphView.GraphViewData(0, 0)});
        graphView = new LineGraphView(this, "Temperature");

        graphView.setViewPort(0, Xview);
        graphView.setScrollable(true);
        graphView.setScalable(true);
        graphView.setShowLegend(true);
        graphView.setLegendAlign(com.jjoe64.graphview.GraphView.LegendAlign.BOTTOM);
        graphView.setManualYAxis(true);
        graphView.setManualYAxisBounds(5, 0);
        graphView.addSeries(Series);
        GraphView.addView(graphView);
    }

    private void Buttoninit() {
        bXminus = (Button) findViewById(R.id.bXminus);
        bXminus.setOnClickListener(this);
        bXplus = (Button) findViewById(R.id.bXplus);
        bXplus.setOnClickListener(this);

        tbStream = (ToggleButton) findViewById(R.id.tbStream);
        tbStream.setOnClickListener(this);
        tbScroll = (ToggleButton) findViewById(R.id.tbScroll);
        tbScroll.setOnClickListener(this);
        tbLock = (ToggleButton) findViewById(R.id.tbLock);
        tbLock.setOnClickListener(this);

        text = (TextView) findViewById(R.id.textView3);
        indi = (TextView) findViewById(R.id.textView5);
        Lock = true;
        Stream = false;
        AutoScrollX = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bXminus:
                if (Xview > 1) Xview--;
                break;

            case R.id.bXplus:
                if (Xview > 1) Xview++;
                break;

            case R.id.tbStream:
                if (tbStream.isChecked()) {
                    if (BluetoothPage.connectedThread != null)
                        BluetoothPage.connectedThread.write("T");
                } else {
                    if (BluetoothPage.connectedThread != null)
                        BluetoothPage.connectedThread.write("Q");
                }
                break;

            case R.id.tbScroll:
                if (tbScroll.isChecked()) {
                    AutoScrollX = true;
                } else {
                    AutoScrollX = false;
                }
                break;

            case R.id.tbLock:
                if (tbLock.isChecked()) {
                    Lock = true;
                } else {
                    Lock = false;
                }
                break;
        }
    }
}


