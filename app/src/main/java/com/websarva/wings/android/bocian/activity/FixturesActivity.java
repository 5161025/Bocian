package com.websarva.wings.android.bocian.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.FixturesListAdapter;
import com.websarva.wings.android.bocian.beans.Constants;
import com.websarva.wings.android.bocian.fragment.AddFixturesDialogFragment;
import com.websarva.wings.android.bocian.listItem.FixturesListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// 備品確認画面
public class FixturesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        // ここは繰り返しに利用しているだけ
        String names[]      = getResources().getStringArray(R.array.fixtures2);
        String counts[]      = getResources().getStringArray(R.array.fixturesNum);

        List<FixturesListItem> data = new ArrayList<>(); // アダプタのdata部分のリストを作成
        // インスタンス生成してセットしている
        for (int i = Constants.Num.ZERO; i < names.length; i++) {
            FixturesListItem item = new FixturesListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setName(names[i]);
            item.setCount(counts[i]);
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        FixturesListAdapter adapter = new FixturesListAdapter(FixturesActivity.this, data, R.layout.fixtures_list_item);
        ListView listView = findViewById(R.id.Fixtures_list_v_fixtures); // レイアウト
        listView.setAdapter(adapter);

        findViewById(R.id.Fixtures_img_bt_plus).setOnClickListener(view -> {
            // 備品追加ダイアログの出現
            AddFixturesDialogFragment dialog = new AddFixturesDialogFragment();
            getSupportFragmentManager();
            dialog.show(getFragmentManager(), "AddFixturesDialogFragment");
        });

        // この画面の終了（キャンセル）
        findViewById(R.id.Fixtures_bt_cancel).setOnClickListener(view -> { finish(); });
        // この画面の終了（確定）
        findViewById(R.id.Fixtures_bt_confirm).setOnClickListener(view -> { finish(); });
    }
}
