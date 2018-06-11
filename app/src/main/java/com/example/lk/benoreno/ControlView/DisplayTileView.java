package com.example.lk.benoreno.ControlView;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.lk.benoreno.R;

public class DisplayTileView extends ConstraintLayout {

    protected TextView _tvValue;
    protected TextView _tvUnit;
    protected TextView _tvTitle;

    public String dispValue;
    public String dispUnit;
    public String dispTitle;

    public DisplayTileView(Context context)
    {
        super(context);
        initControl(context);
    }

    public DisplayTileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public DisplayTileView(Context context, AttributeSet attrs, int defStyle) {
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

        inflater.inflate(R.layout.control_display_tile, this);

        // layout is inflated, assign local variables to components
        _tvValue = (TextView)findViewById(R.id.tvValue);
        _tvUnit = (TextView)findViewById(R.id.tvUnit);
        _tvTitle = (TextView)findViewById(R.id.tvTitle);
    }

    public void setDispTitle(String dispTitle) {
        this.dispTitle = dispTitle;
        _tvTitle.setText(dispTitle);
    }

    public void setDispValue(String dispValue) {
        this.dispValue = dispValue;
        _tvValue.setText(dispValue);
    }

    public void setDispUnit(String dispUnit) {
        this.dispUnit = dispUnit;
        _tvUnit.setText(dispUnit);
    }

    public String getDispTitle() {
        return dispTitle;
    }

    public String getDispUnit() {
        return dispUnit;
    }

    public String getDispValue() {
        return dispValue;
    }
}
