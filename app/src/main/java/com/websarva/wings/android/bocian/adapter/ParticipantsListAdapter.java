package com.websarva.wings.android.bocian.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.websarva.wings.android.bocian.listItem.ParticipantsListItem;
import com.websarva.wings.android.bocian.R;

import java.util.List;

public class ParticipantsListAdapter extends BaseAdapter {    // 自作のadapterを作成する

    public static final int ZERO = 0;

    private int resource;
    private Context context;
    private List<ParticipantsListItem> data;

    public ParticipantsListAdapter(Context context, List<ParticipantsListItem> data, int resource) {
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
        ParticipantsListItem item = (ParticipantsListItem) getItem(position);
        if (convertView == null) {  // 初回のみ：次回からは前回のレイアウトが入っている
            convertView = activity.getLayoutInflater().inflate(resource, null);
        }
        ((TextView)convertView.findViewById(R.id.name)).setText(item.getName());
        ((TextView)convertView.findViewById(R.id.post)).setText(item.getPost());
        return convertView;
    }
}
