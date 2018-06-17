package com.example.lk.benoreno;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lk.benoreno.ControlView.SettingsButtonView;
import com.example.lk.benoreno.FragmentSubSettings.*;

public class FragmentSettings extends Fragment {

    private FragmentSettingsBikeInfo _fragmentSettingsBikeInfo = new FragmentSettingsBikeInfo();
    private FragmentSettingsUserAccounts _fragmentSettingsUserAccounts = new FragmentSettingsUserAccounts();
    private FragmentSettingsAntiTheft _fragmentSettingsAntiTheft = new FragmentSettingsAntiTheft();
    private FragmentSettingsAutomation _fragmentSettingsAutomation = new FragmentSettingsAutomation();
    private FragmentSettingsRegional _fragmentSettingsRegional = new FragmentSettingsRegional();
    private FragmentSettingsLights _fragmentSettingsLights = new FragmentSettingsLights();

    private SettingsButtonView _btSettingsBikeInfo;
    private SettingsButtonView _btSettingsUserAcc;
    private SettingsButtonView _btSettingsAntiTheft;
    private SettingsButtonView _btSettingsAutomation;
    private SettingsButtonView _btSettingsRegional;
    private SettingsButtonView _btSettingsLights;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_settings, container, false);
        _btSettingsBikeInfo = fragView.findViewById(R.id.btSettingsBikeInfo);
        _btSettingsUserAcc = fragView.findViewById(R.id.btSettingsUserAcc);
        _btSettingsAntiTheft = fragView.findViewById(R.id.btSettingsAntiTheft);
        _btSettingsAutomation = fragView.findViewById(R.id.btSettingsAutomation);
        _btSettingsRegional = fragView.findViewById(R.id.btSettingsRegional);
        _btSettingsLights = fragView.findViewById(R.id.btSettingsLights);

        HookButtonEventHandler();

        _btSettingsBikeInfo.setDispTitle("Bike Info");
        _btSettingsBikeInfo.setDispImage(R.drawable.ic_bike);

        _btSettingsUserAcc.setDispTitle("Users");
        _btSettingsUserAcc.setDispImage(R.drawable.ic_user);

        _btSettingsAntiTheft.setDispTitle("Anti Theft");
        _btSettingsAntiTheft.setDispImage(R.drawable.ic_lock);

        _btSettingsAutomation.setDispTitle("Automation");
        _btSettingsAutomation.setDispImage(R.drawable.ic_android);

        _btSettingsRegional.setDispTitle("Regional");
        _btSettingsRegional.setDispImage(R.drawable.ic_world);

        _btSettingsLights.setDispTitle("Lights");
        _btSettingsLights.setDispImage(R.drawable.ic_light);

        return fragView;
    }

    private void HookButtonEventHandler() {

        _btSettingsBikeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsBikeInfo, true, false);
            }
        });

        _btSettingsUserAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsUserAccounts, true, false);
            }
        });

        _btSettingsAntiTheft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsAntiTheft, true, false);
            }
        });

        _btSettingsAutomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsAutomation, true, false);
            }
        });

        _btSettingsRegional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsRegional, true, false);
            }
        });

        _btSettingsLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsLights, true, false);
            }
        });
    }
}