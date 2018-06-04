package com.example.lk.benoreno.FragmentSubSettings;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lk.benoreno.HomeActivity;
import com.example.lk.benoreno.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentSettingsUserAccounts extends Fragment {

    private FragmentSettingsUserAdd _fragmentSettingsUserAdd = new FragmentSettingsUserAdd();
    private FragmentSettingsUserEdit _fragmentSettingsUserEdit = new FragmentSettingsUserEdit();

    private List<DataMember> mDataMemberList = new ArrayList<>();
    private MySimpleArrayAdapter mDataMemberAdapter;

    private ListView mListView1;

    private Button _btAddUser;
    private Button _btEditUser;
    private Button _btRemoveUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CreateDataStructure();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_settings_user_accounts, container, false);

        mListView1 = fragView.findViewById(R.id.listview);
        mListView1.setAdapter(mDataMemberAdapter);

        _btAddUser = fragView.findViewById(R.id.btAddUser);
        _btEditUser = fragView.findViewById(R.id.btEditUser);
        _btRemoveUser = fragView.findViewById(R.id.btRemoveUser);
        HookButtonEventHandler();

        return fragView;
    }

    private void CreateDataStructure() {
        mDataMemberAdapter = new MySimpleArrayAdapter(getActivity(), mDataMemberList);

        AddUser("User", true);
        AddUser("Test 1", true);
        AddUser("Test 2", false);
    }

    private void AddUser(String name, Boolean status) {
        DataMember dm = new DataMember();
        dm.Name = name;
        dm.Status = status;
        dm.IsChecked = false;
        mDataMemberList.add(dm);
        mDataMemberAdapter.notifyDataSetChanged();
    }

    private void HookButtonEventHandler() {

        _btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsUserAdd, true,false);
            }
        });

        _btEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).commitFragmentTransaction(R.id.frame_layout, _fragmentSettingsUserEdit, true,false);
            }
        });

        _btRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert();
            }
        });
    }

    private void Alert() {
        new AlertDialog.Builder(getActivity())
                .setTitle("Confirm Delete")
                .setMessage("Do you really want to delete User X?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(getActivity(), "Deleted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}

class DataMember {
    String Name; // Name
    Boolean Status; // Admin or Normal
    Boolean IsChecked; // Is it checked?
}

class MySimpleArrayAdapter extends ArrayAdapter<DataMember> {
    private final Context context;
    private final List<DataMember> values;

    MySimpleArrayAdapter(Context context, List<DataMember> values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView1 = rowView.findViewById(R.id.label1);
        TextView textView2 = rowView.findViewById(R.id.label2);

        DataMember dm = values.get(position);

        textView1.setText(dm.Name);

        String status = "Normal"; //Fallback to normal
        if (dm.Status) {
            status = "Admin";
        }
        textView2.setText(status);

        if (dm.IsChecked) {
        } else {
        }

        return rowView;
    }
}