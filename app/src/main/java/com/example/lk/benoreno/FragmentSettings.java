package com.example.lk.benoreno;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lk.benoreno.FragmentSubSettings.*;

public class FragmentSettings extends Fragment {

    private FragmentSettingsBikeInfo _fragmentSettingsBikeInfo = new FragmentSettingsBikeInfo();
    private FragmentSettingsUserAccounts _fragmentSettingsUserAccounts = new FragmentSettingsUserAccounts();
    private FragmentSettingsAntiTheft _fragmentSettingsAntiTheft = new FragmentSettingsAntiTheft();
    private FragmentSettingsAutomation _fragmentSettingsAutomation = new FragmentSettingsAutomation();
    private FragmentSettingsRegional _fragmentSettingsRegional = new FragmentSettingsRegional();
    private FragmentSettingsLights _fragmentSettingsLights = new FragmentSettingsLights();

    private Button _btSettingsBikeInfo;
    private Button _btSettingsUserAcc;
    private Button _btSettingsAntiTheft;
    private Button _btSettingsAutomation;
    private Button _btSettingsRegional;
    private Button _btSettingsLights;

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

        return fragView;
    }

    private void HookButtonEventHandler() {

        _btSettingsBikeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsBikeInfo);
            }
        });

        _btSettingsUserAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsUserAccounts);
            }
        });

        _btSettingsAntiTheft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsAntiTheft);
            }
        });

        _btSettingsAutomation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsAutomation);
            }
        });

        _btSettingsRegional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsRegional);
            }
        });

        _btSettingsLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsLights);
            }
        });
    }
}