package com.example.lk.benoreno.FragmentSubSettings;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.lk.benoreno.R;

public class FragmentSettingsUserEdit extends Fragment {
    private Button _btBack;
    private Button _btNext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_settings_user_edit, container, false);

        _btBack = fragView.findViewById(R.id.btBack);
        _btNext = fragView.findViewById(R.id.btNext);
        HookButtonEventHandler();

        return fragView;
    }

    private void HookButtonEventHandler() {

        _btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Changes Not Saved!", Toast.LENGTH_SHORT).show();
                getActivity().getFragmentManager().popBackStackImmediate();
            }
        });

        _btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Changes Saved!", Toast.LENGTH_SHORT).show();
                getActivity().getFragmentManager().popBackStackImmediate();
            }
        });
    }
}