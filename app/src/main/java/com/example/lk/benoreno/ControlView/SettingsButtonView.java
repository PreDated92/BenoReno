package com.example.lk.benoreno.ControlView;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lk.benoreno.R;

public class SettingsButtonView extends ConstraintLayout {

    protected TextView _tvTitle;
    protected ImageView _btImage;

    public SettingsButtonView(Context context)
    {
        super(context);
        initControl(context);
    }

    public SettingsButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context);
    }

    public SettingsButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initControl(context);
    }

    protected void initControl(Context context)
    {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.control_settings_button, this);

        _btImage = findViewById(R.id.imageButton);
        _tvTitle = findViewById(R.id.tvTitle);
    }

    public void setDispTitle(String dispTitle) {
        _tvTitle.setText(dispTitle);
    }

    public void setDispImage(int resId) {
        _btImage.setImageResource(resId);
    }
}
