package com.example.lk.benoreno;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lk.benoreno.FragmentSubSettings.FragmentSettingsUserAccounts;

public class FragmentRegisterFingerprint extends Fragment {

    private Button _btRegisterAgain;
    private Button _btNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.activity_register_fingerprint, container, false);

        _btRegisterAgain = fragView.findViewById(R.id.btnRegisterAnotherFingerprint);
        _btNext = fragView.findViewById(R.id.btnRegisterFingerprintNext);
        HookButtonEventHandler();

        return  fragView;
    }

    private void HookButtonEventHandler() {

        _btRegisterAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do nothing for now
            }
        });

        _btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "User Successfully Added!", Toast.LENGTH_SHORT).show();
                getActivity().getFragmentManager().popBackStack(FragmentSettingsUserAccounts.ADD_USER_STACK_STRING, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });
    }
}