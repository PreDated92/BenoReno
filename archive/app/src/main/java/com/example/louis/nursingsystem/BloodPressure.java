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

public class BloodPressure extends Activity implements View.OnClickListener {

    Button bXminus1, bXplus1,tbstart;
    ToggleButton tbLock1, tbStream1, tbScroll1;
    static boolean Lock1, Stream1, AutoScrollX1;
    TextView text1,text2,text3;
    static LinearLayout GraphView1;
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
                            Series.appendData(new GraphView.GraphViewData(graph2LastXValue, Double.parseDouble(strIncom)), AutoScrollX1);

                            if (graph2LastXValue >= Xview && Lock1 == true) {
                                Series.resetData(new GraphView.GraphViewData[]{});
                                graph2LastXValue = 0;
                            } else graph2LastXValue += 0.1;
                            if (Lock1 == true) graphView.setViewPort(0, Xview);
                            else graphView.setViewPort(graph2LastXValue - Xview, Xview);

                            GraphView1.removeView(graphView);
                            GraphView1.addView(graphView);
                        }
                    } else if (strIncom.indexOf('A') == 0) {
                        strIncom = strIncom.replace("A", "");
                        TextView text = (TextView) findViewById(R.id.textView31);
                        text.setText("MAP: " + strIncom + " mmHg");

                    } else if (strIncom.indexOf('B') == 0) {
                        strIncom = strIncom.replace("B", "");
                        TextView text = (TextView) findViewById(R.id.textView32);
                        text.setText("Sys: " + strIncom + " mmHg");

                    } else if (strIncom.indexOf('C') == 0) {
                        strIncom = strIncom.replace("C", "");
                        TextView text = (TextView) findViewById(R.id.textView33);
                        text.setText("Dia: " + strIncom + " mmHg");



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
        setContentView(R.layout.activity_bloodpressure);

        LinearLayout background = (LinearLayout) findViewById(R.id.bg1);
        background.setBackgroundColor(Color.BLACK);
        init();
        Buttoninit();

    }

    private void init() {

        BluetoothPage.gethandler(mHandler);
        GraphView1 = (LinearLayout) findViewById(R.id.GraphTemp1);
        Series = new GraphViewSeries("Signal",
                new GraphViewSeries.GraphViewStyle(Color.YELLOW, 2),
                new GraphView.GraphViewData[]{new GraphView.GraphViewData(0, 0)});
        graphView = new LineGraphView(this, "BloodPressure");

        graphView.setViewPort(0, Xview);
        graphView.setScrollable(true);
        graphView.setScalable(true);
        graphView.setShowLegend(true);
        graphView.setLegendAlign(com.jjoe64.graphview.GraphView.LegendAlign.BOTTOM);
        graphView.setManualYAxis(true);
        graphView.setManualYAxisBounds(4, 3);
        graphView.addSeries(Series);
        GraphView1.addView(graphView);
    }

    private void Buttoninit() {
        bXminus1 = (Button) findViewById(R.id.bXminus1);
        bXminus1.setOnClickListener(this);
        bXplus1 = (Button) findViewById(R.id.bXplus1);
        bXplus1.setOnClickListener(this);
        tbstart = (Button) findViewById(R.id.tbstart);
        tbstart.setOnClickListener(this);


        text1 = (TextView) findViewById(R.id.textView31);
        text2 = (TextView) findViewById(R.id.textView32);
        text3 = (TextView) findViewById(R.id.textView33);

        Lock1 = true;
        Stream1 = false;
        AutoScrollX1 = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bXminus1:
                if (Xview > 1) Xview--;
                break;

            case R.id.bXplus1:
                if (Xview > 1) Xview++;
                break;

            case R.id.tbstart:
                    if (BluetoothPage.connectedThread != null)
                        BluetoothPage.connectedThread.write("D");

                break;

        }
    }
}

