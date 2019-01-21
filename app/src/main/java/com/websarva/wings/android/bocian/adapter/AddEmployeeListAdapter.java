package com.websarva.wings.android.bocian.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.websarva.wings.android.bocian.listItem.AddEmployeeListItem;
import com.websarva.wings.android.bocian.R;

import java.util.List;

public class AddEmployeeListAdapter extends BaseAdapter {    // 自作のadapterを作成する

    public static final int ZERO = 0;

    private int resource;
    private Context context;
    private List<AddEmployeeListItem> data;

    public AddEmployeeListAdapter(Context context, List<AddEmployeeListItem> data, int resource) {
        this.context    = context;
        this.data       = data;
        this.resource   = resource;
    }

    @Override
    public int getCount() {
        return data.size();
    }   // 今回は誰からも呼ばれない

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }   // getView()から呼ばれる

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }   // 今回は誰からも呼ばれない

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Activity activity = (Activity) context;
        AddEmployeeListItem item = (AddEmployeeListItem) getItem(position);
        if (convertView == null) {  // 初回のみ：次回からは前回のレイアウトが入っている
            convertView = activity.getLayoutInflater().inflate(resource, null);
        }
        ((TextView)convertView.findViewById(R.id.name)).setText(item.getName());
        ((TextView)convertView.findViewById(R.id.division)).setText(item.getDivision());
        ((TextView)convertView.findViewById(R.id.section)).setText(item.getSection());
        ((TextView)convertView.findViewById(R.id.post)).setText(item.getPost());
        CheckBox checkBox = convertView.findViewById(R.id.checkbox);
        final AddEmployeeListItem fitem = item;
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                fitem.setChecked(isChecked);
            }
        });
        checkBox.setChecked(item.isChecked());
        return convertView;
    }
}
