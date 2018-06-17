package com.example.lk.benoreno.ControlView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lk.benoreno.R;

public class TextSpinnerComboView extends LinearLayout {

    protected Spinner _spValues;
    protected TextView _tvTitle;

    public TextSpinnerComboView(Context context)
    {
        super(context);
        initControl(context);
    }

    public TextSpinnerComboView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public TextSpinnerComboView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initControl(context);
    }

    protected void initControl(Context context)
    {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.control_text_spinner_combo, this);

        _spValues = findViewById(R.id.spValues);
        _tvTitle = findViewById(R.id.tvTitle);
    }

    public void setDropdownItems(String[] items)
    {
        ArrayAdapter<String> valuesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        _spValues.setAdapter(valuesAdapter);
    }

    public void setDispTitle(String dispTitle) {
        _tvTitle.setText(dispTitle);
    }

    public String getDispTitle() {
        return _tvTitle.getText().toString();
    }
}
