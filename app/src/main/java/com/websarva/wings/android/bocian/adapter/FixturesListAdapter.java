package com.websarva.wings.android.bocian.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.websarva.wings.android.bocian.listItem.FixturesListItem;
import com.websarva.wings.android.bocian.R;

import java.util.List;

public class FixturesListAdapter extends BaseAdapter {
    public static final int ZERO = 0;

    private int resource;
    private Context context;
    private List<FixturesListItem> data;

    public FixturesListAdapter(Context context, List<FixturesListItem> data, int resource) {
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
        FixturesListItem item = (FixturesListItem) getItem(position);
        if (convertView == null) {  // 初回のみ：次回からは前回のレイアウトが入っている
            convertView = activity.getLayoutInflater().inflate(resource, null);
        }
        ((TextView)convertView.findViewById(R.id.name)).setText(item.getName());
        ((TextView)convertView.findViewById(R.id.count)).setText(item.getCount());
        ImageButton edit = convertView.findViewById(R.id.edit);
        edit.setOnClickListener((buttonView) -> {
            if(parent != null && parent.getClass() == ListView.class) ((ListView) parent).performItemClick(buttonView, position, R.id.edit);
        });
        ImageButton delete = convertView.findViewById(R.id.delete);
        delete.setOnClickListener((buttonView) -> {
            if(parent != null && parent.getClass() == ListView.class) ((ListView) parent).performItemClick(buttonView, position, R.id.delete);
        });
        return convertView;
    }
}
