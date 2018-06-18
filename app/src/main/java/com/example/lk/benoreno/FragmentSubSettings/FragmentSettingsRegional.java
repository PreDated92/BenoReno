package com.example.lk.benoreno.FragmentSubSettings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lk.benoreno.ControlView.TextSpinnerComboView;
import com.example.lk.benoreno.R;

public class FragmentSettingsRegional extends Fragment {

    private TextSpinnerComboView _spLanguage;
    private TextSpinnerComboView _spDistanceUnits;
    private TextSpinnerComboView _spWeightUnits;

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
        _spLanguage.setDropdownItems(items);
        _spLanguage.setDispTitle("Language");

        items = new String[]{"km", "mi"};
        _spDistanceUnits.setDropdownItems(items);
        _spDistanceUnits.setDispTitle("Distance Units");

        items = new String[]{"kg", "lbs"};
        _spWeightUnits.setDropdownItems(items);
        _spWeightUnits.setDispTitle("Weight Units");
    }
}