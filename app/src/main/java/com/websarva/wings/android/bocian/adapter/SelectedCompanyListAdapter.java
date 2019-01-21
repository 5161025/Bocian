package com.websarva.wings.android.bocian.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.listItem.SelectedCompanyListItem;

import java.util.List;

public class SelectedCompanyListAdapter extends BaseAdapter {
    public static final int ZERO = 0;

    private int resource;
    private Context context;
    private List<SelectedCompanyListItem> data;

    public SelectedCompanyListAdapter(Context context, List<SelectedCompanyListItem> data, int resource) {
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
        SelectedCompanyListItem item = (SelectedCompanyListItem) getItem(position);
        if (convertView == null) {  // 初回のみ：次回からは前回のレイアウトが入っている
            convertView = activity.getLayoutInflater().inflate(resource, null);
        }
        ((TextView)convertView.findViewById(R.id.name)).setText(item.getName());
        return convertView;
    }
}
