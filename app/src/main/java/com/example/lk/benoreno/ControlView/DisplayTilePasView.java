package com.example.lk.benoreno.ControlView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.lk.benoreno.R;

public class DisplayTilePasView extends DisplayTileView {

    private Switch _switch1;
    private ImageButton _btPasUp;
    private ImageButton _btPasDown;

    private int _pasLevel;
    final int PAS_MAX_VALUE = 6;

    public DisplayTilePasView(Context context)
    {
        super(context);
    }

    public DisplayTilePasView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DisplayTilePasView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Load component XML layout
     */
    @Override
    protected void initControl(Context context)
    {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.control_pas_tile, this);

        // layout is inflated, assign local variables to components
        _tvValue = findViewById(R.id.tvValue);
        _tvUnit = findViewById(R.id.tvUnit);
        _tvTitle = findViewById(R.id.tvTitle);
        _switch1 = findViewById(R.id.switch1);
        _btPasUp = findViewById(R.id.btPasUp);
        _btPasDown = findViewById(R.id.btPasDown);

        _pasLevel = 0;
        HookEventHandlers();
    }

    private void HookEventHandlers() {

        _switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    _tvTitle.setText("Throttle");
                    _tvValue.setText("--");
                    _btPasDown.setVisibility(View.INVISIBLE);
                    _btPasUp.setVisibility(View.INVISIBLE);
                } else {
                    _tvTitle.setText("Pedal Assist");
                    _tvValue.setText((String.valueOf(_pasLevel)));
                    _btPasDown.setVisibility(View.VISIBLE);
                    _btPasUp.setVisibility(View.VISIBLE);
                }
            }
        });

        _btPasUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_pasLevel < PAS_MAX_VALUE) {
                    _pasLevel++;
                }

                _tvValue.setText((String.valueOf(_pasLevel)));
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

                _tvValue.setText((String.valueOf(_pasLevel)));
                _btPasUp.setEnabled(_pasLevel != PAS_MAX_VALUE);
                _btPasDown.setEnabled(_pasLevel != 0);
            }
        });
    }



}





