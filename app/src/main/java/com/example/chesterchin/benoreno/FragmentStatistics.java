package com.example.chesterchin.benoreno;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FragmentStatistics extends Fragment {

    GraphView mGraph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);

        mGraph = rootView.findViewById(R.id.graph);

        // generate Dates
//        Calendar calendar = Calendar.getInstance();
//        Date d1 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d2 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d3 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d4 = calendar.getTime();
//        calendar.add(Calendar.DATE, 1);
//        Date d5 = calendar.getTime();

        Calendar cal = new GregorianCalendar(2018, 6, 12, 9, 30, 0);
        long t1 = cal.getTimeInMillis();
        cal.add(Calendar.MINUTE, 1);
        long t2 = cal.getTimeInMillis();
        cal.add(Calendar.MINUTE, 1);
        long t3 = cal.getTimeInMillis();
        cal.add(Calendar.MINUTE, 1);
        long t4 = cal.getTimeInMillis();
        cal.add(Calendar.MINUTE, 1);
        long t5 = cal.getTimeInMillis();

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(t1, 10),
                new DataPoint(t2, 20),
                new DataPoint(t3, 30),
                new DataPoint(t4, 20),
                new DataPoint(t5, 60)
        });
        mGraph.addSeries(series);

        // set custom label formatter
        mGraph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {

                if (isValueX) {
                    Date d = new Date((long) (value));
                    DateFormat dateFormat = android.text.format.DateFormat.getTimeFormat(getActivity());
                    return (dateFormat.format(d));
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        // set manual x bounds to have nice steps
//        mGraph.getViewport().setMinX(t1);
//        mGraph.getViewport().setMaxX(t5);
//        mGraph.getViewport().setXAxisBoundsManual(true);

        // set manual y bounds to have nice steps
        mGraph.getViewport().setMinY(0.0);
        mGraph.getViewport().setMaxY(80.0);
        mGraph.getViewport().setYAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not necessary
        mGraph.getGridLabelRenderer().setHumanRounding(false);


        return rootView;
    }
}