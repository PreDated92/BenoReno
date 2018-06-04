package com.example.lk.benoreno;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class FragmentHome extends Fragment {

    private Switch _switch1;
    private TextView _tvPasTitle;
    private TextView _tvPasLevel;
    private ImageButton _btPasUp;
    private ImageButton _btPasDown;
    private int _pasLevel;
    final int PAS_MAX_VALUE = 6;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_home, container, false);
        _switch1 = fragView.findViewById(R.id.switch1);
        _tvPasTitle = fragView.findViewById(R.id.tvPasTitle);
        _tvPasLevel = fragView.findViewById(R.id.tvPasLevel);
        _btPasUp = fragView.findViewById(R.id.btPasUp);
        _btPasDown = fragView.findViewById(R.id.btPasDown);

        HookEventHandlers();

        Init();

        return fragView;
    }

    private void Init(){
        _pasLevel = 0;
    }

    private void HookEventHandlers() {

        _switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    _tvPasTitle.setText("Throttle");
                } else {
                    _tvPasTitle.setText("Pedal Assist");
                    _tvPasLevel.setText((String.valueOf(_pasLevel)));
                }
            }
        });

        _btPasUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_pasLevel < PAS_MAX_VALUE) {
                    _pasLevel++;
                }

                _tvPasLevel.setText((String.valueOf(_pasLevel)));
                _btPasUp.setEnabled(_pasLevel != PAS_MAX_VALUE);
                _btPasDown.setEnabled(_pasLevel != 0);
            }
        });

        _btPasDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_pasLevel > 0) {
                    _pasLevel--;
                }

                _tvPasLevel.setText((String.valueOf(_pasLevel)));
                _btPasUp.setEnabled(_pasLevel != PAS_MAX_VALUE);
                _btPasDown.setEnabled(_pasLevel != 0);
            }
        });
    }
}