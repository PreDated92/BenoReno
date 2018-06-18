package com.example.lk.benoreno;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FragmentStatistics extends Fragment {

    private FragmentHistory _fragmentHistory = new FragmentHistory();

    private GraphView _graph;
    private ImageButton _btHistory;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);

        _graph = rootView.findViewById(R.id.graph);
        _btHistory = rootView.findViewById(R.id.btHistory);

        HookEventHandler();

        // generate Timescale
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
        _graph.addSeries(series);

        // set custom label formatter
        _graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
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
//        _graph.getViewport().setMinX(t1);
//        _graph.getViewport().setMaxX(t5);
//        _graph.getViewport().setXAxisBoundsManual(true);

        // set manual y bounds to have nice steps
        _graph.getViewport().setMinY(0.0);
        _graph.getViewport().setMaxY(80.0);
        _graph.getViewport().setYAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not necessary
        _graph.getGridLabelRenderer().setHumanRounding(false);

        return rootView;
    }

    private void HookEventHandler()
    {
        _btHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentHistory, true, false);
            }
        });
    }
}