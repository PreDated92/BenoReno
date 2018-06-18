package com.example.lk.benoreno;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentHistory extends Fragment {

    private List<HistoryData> _historyDataList = new ArrayList<>();
    private HistoryListArrayAdapter _adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CreateDataStructure();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_history, container, false);

        ListView listView = fragView.findViewById(R.id.lvHistoryTable);
        listView.setAdapter(_adapter);

        HookEventHandler();

        return fragView;
    }

    private void CreateDataStructure() {
        _adapter = new HistoryListArrayAdapter(getActivity(), _historyDataList);
        ClearHistoryUi();

        AddHistoryUi("Ride 1", R.drawable.ic_android);
        AddHistoryUi("Ride 2", R.drawable.ic_android);
        AddHistoryUi("Ride 3", R.drawable.ic_android);
    }

    private void AddHistoryUi(String name, int resId) {
        HistoryData dm = new HistoryData();
        dm.Name = name;
        dm.ResId = resId;
        _historyDataList.add(dm);
        _adapter.notifyDataSetChanged();
    }

    private void ClearHistoryUi()
    {
        _historyDataList.clear();
        _adapter.notifyDataSetChanged();
    }

    private void HookEventHandler() {

    }
}

class HistoryData {
    String Name; // Name
    int ResId; // Image ResId
}

class HistoryListArrayAdapter extends ArrayAdapter<HistoryData> {
    private final List<HistoryData> values;

    private static class ViewHolder {
        private TextView Text;
        private ImageView Image;
    }

    HistoryListArrayAdapter(Context context, List<HistoryData> values) {
        super(context, R.layout.listview_user_accounts, values);
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;

        if (convertView == null) {
            mViewHolder = new ViewHolder();

            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = vi.inflate(R.layout.listview_history, parent, false);
            mViewHolder.Text = convertView.findViewById(R.id.textView); // title
            mViewHolder.Image = convertView.findViewById(R.id.imageView); // thumb image

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.Text.setText(values.get(position).Name);
        mViewHolder.Image.setImageResource(values.get(position).ResId);

        return convertView;
    }

}
