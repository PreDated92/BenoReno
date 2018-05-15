package com.example.chesterchin.benoreno;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chesterchin.benoreno.FragmentSubSettings.*;

public class FragmentSettings extends Fragment {

    private FragmentSettingsBikeInfo _fragmentSettingsBikeInfo = new FragmentSettingsBikeInfo();
    private FragmentSettingsUserAccounts _fragmentSettingsUserAccounts = new FragmentSettingsUserAccounts();
    private FragmentSettingsAntiTheft _fragmentSettingsAntiTheft = new FragmentSettingsAntiTheft();
    private Button _btSettingsBikeInfo;
    private Button _btSettingsUserAcc;
    private Button _btSettingsAntiTheft;

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
        HookButtonEventHandler();

        return fragView;
    }

    private void HookButtonEventHandler() {

        _btSettingsBikeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout , _fragmentSettingsBikeInfo);
            }
        });

        _btSettingsUserAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout , _fragmentSettingsUserAccounts);
            }
        });

        _btSettingsAntiTheft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout , _fragmentSettingsAntiTheft);
            }
        });
    }
}
