package com.example.lk.benoreno.FragmentSubSettings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lk.benoreno.ControlView.TextSpinnerComboView;
import com.example.lk.benoreno.R;

public class FragmentSettingsLights extends Fragment {

    private TextSpinnerComboView _spLights;
    private TextSpinnerComboView _spLogoLights;
    private TextSpinnerComboView _spAutoLightsMethod;
    private TextSpinnerComboView _spAutoLightsTimeOn;
    private TextSpinnerComboView _spAutoLightsTimeOff;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_settings_lights, container, false);

        _spLights = fragView.findViewById(R.id.spLights);
        _spLogoLights = fragView.findViewById(R.id.spLogoLights);
        _spAutoLightsMethod = fragView.findViewById(R.id.spAutoLightsMethod);
        _spAutoLightsTimeOn = fragView.findViewById(R.id.spAutoLightsTimeOn);
        _spAutoLightsTimeOff = fragView.findViewById(R.id.spAutoLightsTimeOff);
        CreateSpinnerAdapter();

        return fragView;
    }

    private void CreateSpinnerAdapter() {
        String[] items = new String[]{"On", "Off", "Auto"};
        _spLights.setDropdownItems(items);
        _spLights.setDispTitle("Lights");

        items = new String[]{"On", "Off", "Auto"};
        _spLogoLights.setDropdownItems(items);
        _spLogoLights.setDispTitle("Logo Lights");

        items = new String[]{"Light Sensor", "Time"};
        _spAutoLightsMethod.setDropdownItems(items);
        _spAutoLightsMethod.setDispTitle("Auto Lights Method");

        // This is weird, maybe need to consider a different control~
        items = new String[]{"6:00 a.m.", "7:00 a.m."};
        _spAutoLightsTimeOn.setDropdownItems(items);
        _spAutoLightsTimeOn.setDispTitle("Time to turn on lights");

        items = new String[]{"6:00 a.m.", "7:00 a.m."};
        _spAutoLightsTimeOff.setDropdownItems(items);
        _spAutoLightsTimeOff.setDispTitle("Time to turn off lights");
    }
}
