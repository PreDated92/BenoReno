package com.example.lk.benoreno.FragmentSubSettings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lk.benoreno.R;

public class FragmentSettingsLights extends Fragment {

    private Spinner _spLights;
    private Spinner _spLogoLights;
    private Spinner _spAutoLightsMethod;
    private Spinner _spAutoLightsTimeOn;
    private Spinner _spAutoLightsTimeOff;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_settings_lights, container, false);

        _spLights =  fragView.findViewById(R.id.spLights);
        _spLogoLights = fragView.findViewById(R.id.spLogoLights);
        _spAutoLightsMethod =  fragView.findViewById(R.id.spAutoLightsMethod);
        _spAutoLightsTimeOn = fragView.findViewById(R.id.spAutoLightsTimeOn);
        _spAutoLightsTimeOff =  fragView.findViewById(R.id.spAutoLightsTimeOff);
        CreateSpinnerAdapter();

        return fragView;
    }

    private void CreateSpinnerAdapter()
    {
        String[] items = new String[]{"On", "Off", "Auto"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spLights.setAdapter(adapter);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spLogoLights.setAdapter(adapter);

        items = new String[]{"Light Sensor", "Time"};
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spAutoLightsMethod.setAdapter(adapter);

        // This is weird, maybe need to consider a different control~
        items = new String[]{"6:00 p.m.", "7:00 p.m."};
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spAutoLightsTimeOn.setAdapter(adapter);

        items = new String[]{"6:00 a.m.", "7:00 a.m."};
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spAutoLightsTimeOff.setAdapter(adapter);
    }
}
