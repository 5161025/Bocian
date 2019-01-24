package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.FixturesListAdapter;
import com.websarva.wings.android.bocian.beans.BocianDBHelper;
import com.websarva.wings.android.bocian.beans.Constants;
import com.websarva.wings.android.bocian.data.EmployeeData;
import com.websarva.wings.android.bocian.data.EquipmentData;
import com.websarva.wings.android.bocian.fragment.AddFixturesDialogFragment;
import com.websarva.wings.android.bocian.listItem.AddEmployeeListItem;
import com.websarva.wings.android.bocian.listItem.FixturesListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.websarva.wings.android.bocian.beans.Constants.Num.*;

// 備品確認画面
public class FixturesActivity extends AppCompatActivity {
    // DBヘルパークラス
    private BocianDBHelper helper;
    private SQLiteDatabase db;

    private FixturesListAdapter adapter;
    private List<FixturesListItem> data;
    private HashMap<Integer, Integer> fixtureMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        // DBヘルパークラスの生成
        helper = new BocianDBHelper(this);
        db = helper.getReadableDatabase();

        // 前画面からデータの受け取り
        fixtureMap = (HashMap<Integer, Integer>) getIntent().getSerializableExtra("備品リスト"); // ID,個数

        List<EquipmentData> eqList = helper.getDataList(db, Constants.DB.tableEquipment, null,null, null);

        data = new ArrayList<>(); // アダプタのdata部分のリストを作成
        // アダプタデータの生成
        for (Map.Entry<Integer, Integer> fixture : fixtureMap.entrySet()) {
            FixturesListItem item = new FixturesListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setFixturesId(fixture.getKey());
            item.setName(eqList.parallelStream().filter(d -> d.getEqId() == fixture.getKey()).findAny().map(EquipmentData::getEqName).orElse("なし"));
            item.setCount(String.valueOf(fixture.getValue()) + "個");
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        adapter = new FixturesListAdapter(FixturesActivity.this, data, R.layout.fixtures_list_item);
        ListView listView = findViewById(R.id.Fixtures_list_v_fixtures); // レイアウト
        listView.setAdapter(adapter);

        // 現在の参加者人数の表示を更新
        TextView text = findViewById(R.id.Fixtures_tv_fixtures_choice);
        text.setText((data.size()) + "種選択中");

        findViewById(R.id.Fixtures_img_bt_plus).setOnClickListener(view -> {
            // 備品追加ダイアログの出現
            AddFixturesDialogFragment dialog = new AddFixturesDialogFragment();

            // ダイアログに値を渡す
            Bundle bundle = new Bundle();
            // キーと値の順で渡す
            bundle.putInt("id", _ONE);
            bundle.putInt("count", ONE);
            // 値をdialogにセット
            dialog.setArguments(bundle);

            dialog.show(getFragmentManager(), "AddFixturesDialogFragment");
        });

        // 社外のアイテムを選択したとき
        listView.setOnItemClickListener((parent, view, position, id) -> {
            switch (view.getId()){
                case R.id.delete:
                    fixtureMap.remove(data.get(position).getFixturesId());
                    data.remove(position);
                    text.setText(data.size() + "種選択中");
                    adapter.notifyDataSetChanged();
                    break;
                case R.id.edit:
                    // 備品追加ダイアログの出現
                    AddFixturesDialogFragment dialog = new AddFixturesDialogFragment();

                    // ダイアログに値を渡す
                    Bundle bundle = new Bundle();
                    // キーと値の順で渡す
                    bundle.putInt("id", data.get(position).getFixturesId());
                    bundle.putInt("count", Integer.valueOf(fixtureMap.get(data.get(position).getFixturesId())));
                    // 値をdialogにセット
                    dialog.setArguments(bundle);

                    dialog.show(getFragmentManager(), "AddFixturesDialogFragment");
            }
        });

        // この画面の終了（キャンセル）
        findViewById(R.id.Fixtures_bt_cancel).setOnClickListener(view -> { finish(); });
        // この画面の終了（確定）
        findViewById(R.id.Fixtures_bt_confirm).setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("備品リスト",fixtureMap);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
    public void fixtureAdd(String name, int count){
        List<EquipmentData> eqList = helper.getDataList(db, Constants.DB.tableEquipment, null,null, null);

        // データの追加
        EquipmentData fixture = eqList.parallelStream().filter(d -> d.getEqName().equals(name)).findAny().get();
        fixtureMap.put(fixture.getEqId(), count);

        // アダプタデータの追加
        FixturesListItem item = new FixturesListItem();
        item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
        item.setFixturesId(fixture.getEqId());
        item.setName(fixture.getEqName());
        item.setCount(String.valueOf(count) + "個");
        // 見つかった場合は上書きする
        int index = data.parallelStream().filter(d -> d.getFixturesId() == fixture.getEqId()).map(data::indexOf).findAny().orElse(_ONE);
        if (index == _ONE)
            data.add(item); // インスタンスをリストに挿入
        else
            data.set(index, item); // インスタンスをリストに上書き
        adapter.notifyDataSetChanged();

        // 現在の備品個数の表示を更新
        TextView text = findViewById(R.id.Fixtures_tv_fixtures_choice);
        text.setText((data.size()) + "種選択中");
    }
}
