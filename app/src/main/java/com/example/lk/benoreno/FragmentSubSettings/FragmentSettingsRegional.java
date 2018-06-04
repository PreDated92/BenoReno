package com.example.lk.benoreno.FragmentSubSettings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lk.benoreno.R;

public class FragmentSettingsRegional extends Fragment {

    private Spinner _spLanguage;
    private Spinner _spDistanceUnits;
    private Spinner _spWeightUnits;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_settings_regional, container, false);

        _spLanguage =  fragView.findViewById(R.id.spLanguage);
        _spDistanceUnits = fragView.findViewById(R.id.spDistanceUnits);
        _spWeightUnits =  fragView.findViewById(R.id.spWeightUnits);
        CreateSpinnerAdapter();

        return fragView;
    }

    private void CreateSpinnerAdapter()
    {
        String[] items = new String[]{"English", "Language 2", "Language 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spLanguage.setAdapter(adapter);

        items = new String[]{"km", "mi"};
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spDistanceUnits.setAdapter(adapter);

        items = new String[]{"kg", "lbs"};
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        _spWeightUnits.setAdapter(adapter);
    }
}