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

    public static final String ADD_USER_STACK_STRING = "ADD_USER_STACK_STRING";

    private FragmentSettingsUserAdd _fragmentSettingsUserAdd = new FragmentSettingsUserAdd();
    private FragmentSettingsUserEdit _fragmentSettingsUserEdit = new FragmentSettingsUserEdit();

    private List<UserData> _userDataList = new ArrayList<>();
    private UserTableArrayAdapter _adapter;

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

        ListView listView = fragView.findViewById(R.id.lvUserTable);
        listView.setAdapter(_adapter);

        _btAddUser = fragView.findViewById(R.id.btAddUser);
        _btEditUser = fragView.findViewById(R.id.btEditUser);
        _btRemoveUser = fragView.findViewById(R.id.btRemoveUser);
        HookEventHandler();

        return fragView;
    }

    private void CreateDataStructure() {
        _adapter = new UserTableArrayAdapter(getActivity(), _userDataList);
        ClearUsers();

        AddUser("User", true);
        AddUser("Test 1", true);
        AddUser("Test 2", false);
    }

    private void AddUser(String name, Boolean status) {
        UserData dm = new UserData();
        dm.Name = name;
        dm.Status = status;
        dm.IsChecked = false;
        _userDataList.add(dm);
        _adapter.notifyDataSetChanged();
    }

    private void ClearUsers()
    {
        _userDataList.clear();
        _adapter.notifyDataSetChanged();
    }

    private void HookEventHandler() {

        _btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).FragmentStackName = "ADD_USER_STACK_STRING";
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
                .setMessage("Do you really want to delete User X? This action cannot be undone!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Toast.makeText(getActivity(), "Deleted!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}

class UserData {
    String Name; // Name
    Boolean Status; // Admin or Normal
    Boolean IsChecked; // Is it checked?
}

class UserTableArrayAdapter extends ArrayAdapter<UserData> {
    private final Context context;
    private final List<UserData> values;

    UserTableArrayAdapter(Context context, List<UserData> values) {
        super(context, R.layout.listview_user_accounts, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_user_accounts, parent, false);
        TextView textView1 = rowView.findViewById(R.id.label1);
        TextView textView2 = rowView.findViewById(R.id.label2);

        UserData dm = values.get(position);

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