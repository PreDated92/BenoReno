package com.example.lk.benoreno;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ToggleButton;

public class FragmentLock extends Fragment {
    private ImageButton _btAlert;
    private ImageButton _btLock;
    private ImageButton _btPowerSaving;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_lock, container, false);

        _btAlert = fragView.findViewById(R.id.btAlert);
        _btLock = fragView.findViewById(R.id.btLock);
        _btPowerSaving = fragView.findViewById(R.id.btPowerSaving);

        HookEvents();

        return fragView;
    }

    private void HookEvents()
    {
        _btAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _btAlert.setSelected(!_btAlert.isSelected());
            }
        });

        _btLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _btLock.setSelected(!_btLock.isSelected());
            }
        });

        _btPowerSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _btPowerSaving.setSelected(!_btPowerSaving.isSelected());
            }
        });
    }
}