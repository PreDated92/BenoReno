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
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;

public class Pulse extends Activity implements View.OnClickListener {

    Button bXminus2, bXplus2;
    ToggleButton tbLock2, tbStream2, tbScroll2;
    static boolean Lock2, Stream2, AutoScrollX2;
    TextView textt;
    static LinearLayout GraphView2;
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
                            Series.appendData(new GraphView.GraphViewData(graph2LastXValue, Double.parseDouble(strIncom)), AutoScrollX2);

                            if (graph2LastXValue >= Xview && Lock2 == true) {
                                Series.resetData(new GraphView.GraphViewData[]{});
                                graph2LastXValue = 0;
                            } else graph2LastXValue += 0.1;
                            if (Lock2 == true) graphView.setViewPort(0, Xview);
                            else graphView.setViewPort(graph2LastXValue - Xview, Xview);

                            GraphView2.removeView(graphView);
                            GraphView2.addView(graphView);
                        }
                    } else if (strIncom.indexOf('E') == 0) {
                        strIncom = strIncom.replace("E", "");
                        TextView text = (TextView) findViewById(R.id.textView322);
                        text.setText(strIncom + " bpm");
                        break;
                    }
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
        setContentView(R.layout.activity_pulse);

        LinearLayout background = (LinearLayout) findViewById(R.id.bg2);
        background.setBackgroundColor(Color.BLACK);
        init();
        Buttoninit();

    }

    private void init() {

        BluetoothPage.gethandler(mHandler);
        GraphView2 = (LinearLayout) findViewById(R.id.GraphTemp2);
        Series = new GraphViewSeries("Signal",
                new GraphViewSeries.GraphViewStyle(Color.YELLOW, 2),
                new GraphView.GraphViewData[]{new GraphView.GraphViewData(0, 0)});
        graphView = new LineGraphView(this, "Pulse");

        graphView.setViewPort(0, Xview);
        graphView.setScrollable(true);
        graphView.setScalable(true);
        graphView.setShowLegend(true);
        graphView.setLegendAlign(com.jjoe64.graphview.GraphView.LegendAlign.BOTTOM);
        graphView.setManualYAxis(true);
        graphView.setManualYAxisBounds(5, 0);
        graphView.addSeries(Series);
        GraphView2.addView(graphView);
    }

    private void Buttoninit() {
        bXminus2 = (Button) findViewById(R.id.bXminus2);
        bXminus2.setOnClickListener(this);
        bXplus2 = (Button) findViewById(R.id.bXplus2);
        bXplus2.setOnClickListener(this);

        tbStream2 = (ToggleButton) findViewById(R.id.tbStream2);
        tbStream2.setOnClickListener(this);
        tbScroll2 = (ToggleButton) findViewById(R.id.tbScroll2);
        tbScroll2.setOnClickListener(this);
        tbLock2 = (ToggleButton) findViewById(R.id.tbLock2);
        tbLock2.setOnClickListener(this);

        textt = (TextView) findViewById(R.id.textView322);

        Lock2 = true;
        Stream2 = false;
        AutoScrollX2 = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bXminus2:
                if (Xview > 1) Xview--;
                break;

            case R.id.bXplus2:
                if (Xview > 1) Xview++;
                break;

            case R.id.tbStream2:
                if (tbStream2.isChecked()) {
                    if (BluetoothPage.connectedThread != null)
                        BluetoothPage.connectedThread.write("P");
                } else {
                    if (BluetoothPage.connectedThread != null)
                        BluetoothPage.connectedThread.write("Q");
                }
                break;

            case R.id.tbScroll2:
                if (tbScroll2.isChecked()) {
                    AutoScrollX2 = true;
                } else {
                    AutoScrollX2 = false;
                }
                break;

            case R.id.tbLock2:
                if (tbLock2.isChecked()) {
                    Lock2 = true;
                } else {
                    Lock2 = false;
                }
                break;
        }
    }
}

