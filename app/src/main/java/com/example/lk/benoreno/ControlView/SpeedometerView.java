package com.example.lk.benoreno.ControlView;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.lk.benoreno.R;

public class SpeedometerView extends ConstraintLayout {

    protected TextView _tvValue;
    protected TextView _tvUnit;

    public String dispValue;
    public String dispUnit;

    public SpeedometerView(Context context)
    {
        super(context);
        initControl(context);
    }

    public SpeedometerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public SpeedometerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initControl(context);
    }

    /**
     * Load component XML layout
     */
    protected void initControl(Context context)
    {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.control_speedometer, this);

        // layout is inflated, assign local variables to components
        _tvValue = findViewById(R.id.tvValue);
        _tvUnit = findViewById(R.id.tvUnit);
    }

    public void setDispValue(String dispValue) {
        this.dispValue = dispValue;
        _tvValue.setText(dispValue);
    }

    public void setDispUnit(String dispUnit) {
        this.dispUnit = dispUnit;
        _tvUnit.setText(dispUnit);
    }

    public String getDispUnit() {
        return dispUnit;
    }

    public String getDispValue() {
        return dispValue;
    }
}


