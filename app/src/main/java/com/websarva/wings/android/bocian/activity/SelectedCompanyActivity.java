package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.SelectedCompanyListAdapter;
import com.websarva.wings.android.bocian.listItem.SelectedCompanyListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SelectedCompanyActivity extends AppCompatActivity {
    public static final int ZERO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_company);
        // ここは繰り返しに利用しているだけ
        String names[]      = getResources().getStringArray(R.array.companyList);

        List<SelectedCompanyListItem> data = new ArrayList<>(); // アダプタのdata部分のリストを作成
        // インスタンス生成してセットしている
        for (int i = ZERO; i < names.length; i++) {
            SelectedCompanyListItem item = new SelectedCompanyListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setName(names[i]);
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        SelectedCompanyListAdapter adapter = new SelectedCompanyListAdapter(SelectedCompanyActivity.this, data, R.layout.selected_company_list_item);
        ListView listView = findViewById(R.id.list); // レイアウト
        listView.setAdapter(adapter);

        // 企業登録画面を起動
        findViewById(R.id.button4).setOnClickListener(view -> {
            Intent intent = new Intent(SelectedCompanyActivity.this, EditCompanyActivity.class);
            startActivity(intent);
        });

        // 企業編集画面を起動（リスト）

        // 削除処理を起動（リスト）

        // この画面の終了（戻る）
        findViewById(R.id.button3).setOnClickListener( view -> { finish(); });
    }
}
