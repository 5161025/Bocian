package com.websarva.wings.android.bocian.activity;
/*

********参加者追加画面(社外)*********

 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.AddCustomerListAdapter;
import com.websarva.wings.android.bocian.listItem.AddCustomerListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddCustomerActivity extends AppCompatActivity {

    public static final int ZERO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        // ここは繰り返しに利用しているだけ
        String names[]      = getResources().getStringArray(R.array.customerNameList);
        String posts[]      = getResources().getStringArray(R.array.customerPostList);

        List<AddCustomerListItem> data = new ArrayList<>(); // アダプタのdata部分のリストを作成
        // インスタンス生成してセットしている
        for (int i = ZERO; i < names.length; i++) {
            AddCustomerListItem item = new AddCustomerListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setName(names[i]);
            item.setPost(posts[i]);
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        AddCustomerListAdapter adapter = new AddCustomerListAdapter(AddCustomerActivity.this, data, R.layout.add_customer_list_item);
        ListView listView = findViewById(R.id.list); // レイアウト

        listView.setAdapter(adapter);
        // 初期チェック
        for (int i = 0; i < listView.getCount(); i += 2) {
            View c_listView = adapter.getView(i, null, null);
            CheckBox check = (CheckBox) c_listView.findViewById(R.id.checkbox);
            check.setChecked(true);
        }
        // リスナー
        // 企業登録画面の起動
        findViewById(R.id.button2).setOnClickListener(view -> {
            Intent intent = new Intent(AddCustomerActivity.this, EditCompanyActivity.class);
            startActivity(intent);
        });
        // この画面の終了（確定）
        findViewById(R.id.button11).setOnClickListener(view -> { finish(); });
        // この画面の終了（キャンセル）
        findViewById(R.id.button6).setOnClickListener(view -> { finish(); });
    }
}
