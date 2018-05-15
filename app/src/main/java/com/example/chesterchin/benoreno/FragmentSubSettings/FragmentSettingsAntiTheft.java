package com.example.chesterchin.benoreno.FragmentSubSettings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.chesterchin.benoreno.R;

public class FragmentSettingsAntiTheft extends Fragment {

    Spinner _spGeoRadius;
    Spinner _spGeoRadiusUnits;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_settings_anti_theft, container, false);

        _spGeoRadius = fragView.findViewById(R.id.spGeoRadius);
        _spGeoRadiusUnits = fragView.findViewById(R.id.spGeoRadiusUnits);
        CreateSpinnerAdapter();

        return fragView;
    }

    private void CreateSpinnerAdapter()
    {
        String[] items = new String[]{"100", "200", "300"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spGeoRadius.setAdapter(adapter);

        items = new String[]{"m", "yards"};
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spGeoRadiusUnits.setAdapter(adapter);
    }
}