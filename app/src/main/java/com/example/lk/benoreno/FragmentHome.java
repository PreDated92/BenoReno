package com.example.lk.benoreno;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.lk.benoreno.ControlView.DisplayTilePasView;
import com.example.lk.benoreno.ControlView.DisplayTileView;

public class FragmentHome extends Fragment {

    private DisplayTilePasView _dispPas;
    private DisplayTileView _dispTemperature;
    private DisplayTileView _dispBattery;
    private DisplayTileView _dispDistance;
    private DisplayTileView _dispTimer;
    private DisplayTileView _dispCalories;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_home, container, false);

        _dispPas = fragView.findViewById(R.id.dispPas);
        _dispTemperature = fragView.findViewById(R.id.dispTemperature);
        _dispBattery = fragView.findViewById(R.id.dispBattery);
        _dispDistance = fragView.findViewById(R.id.dispDistance);
        _dispTimer = fragView.findViewById(R.id.dispTimer);
        _dispCalories = fragView.findViewById(R.id.dispCalories);

        Init();

        return fragView;
    }

    private void Init(){
        _dispPas.setDispTitle("Pedal Assist/Throttle");
        _dispPas.setDispValue("0");
        _dispPas.setDispUnit("");

        _dispTemperature.setDispTitle("Temperature");
        _dispTemperature.setDispValue("27");
        _dispTemperature.setDispUnit("°C");

        _dispBattery.setDispTitle("Battery");
        _dispBattery.setDispValue("85");
        _dispBattery.setDispUnit("%");

        _dispDistance.setDispTitle("Distance");
        _dispDistance.setDispValue("12.5");
        _dispDistance.setDispUnit("km");

        _dispTimer.setDispTitle("Timer");
        _dispTimer.setDispValue("00:12:45");
        _dispTimer.setDispUnit("");

        _dispCalories.setDispTitle("Calories");
        _dispCalories.setDispValue("7.895");
        _dispCalories.setDispUnit("kcal");
    }


}