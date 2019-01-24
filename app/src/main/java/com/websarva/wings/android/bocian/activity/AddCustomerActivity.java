package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.AddCustomerListAdapter;
import com.websarva.wings.android.bocian.beans.BocianDBHelper;
import com.websarva.wings.android.bocian.beans.Constants;
import com.websarva.wings.android.bocian.data.CompanyData;
import com.websarva.wings.android.bocian.data.EmployeeData;
import com.websarva.wings.android.bocian.data.ExternalParticipantData;
import com.websarva.wings.android.bocian.data.ExternalPersonsData;
import com.websarva.wings.android.bocian.listItem.AddCompanyListItem;
import com.websarva.wings.android.bocian.listItem.AddCustomerListItem;
import com.websarva.wings.android.bocian.listItem.AddEmployeeListItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.websarva.wings.android.bocian.beans.Constants.Num.*;

// 参加者追加画面（社外者）
public class AddCustomerActivity extends AppCompatActivity {
    // DBヘルパークラス
    private BocianDBHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DBヘルパークラスの生成
        helper = new BocianDBHelper(this);
        db = helper.getReadableDatabase();

        setContentView(R.layout.activity_add_customer);

        ImageButton imageButton = (ImageButton)findViewById(R.id.addCustomer_img_bt_add_to);
        imageButton.setEnabled(false);
        imageButton.setColorFilter(0xaaB0B0B0);

        // 前画面からデータの受け取り
        int cmpId = getIntent().getIntExtra("会社ID", _ONE);
        ArrayList<Integer> idList = (ArrayList<Integer>) getIntent().getSerializableExtra("参加者リスト");
        // 会社
        // List<CompanyData> cmpList = helper.getDataList(db, Constants.DB.tableCompany, "compId=?", new String[]{Integer.toString(cmpId)}, null);

        // 社外者リストの作成
        List<ExternalPersonsData> epList = helper.getDataList(db, Constants.DB.tableExternalPersons, "companyId=?", new String[]{Integer.toString(cmpId)}, null);

        List<AddCustomerListItem> addCustomerList = new ArrayList<>(); // アダプタのdata部分のリストを作成
        for (ExternalPersonsData ep : epList){
            AddCustomerListItem item = new AddCustomerListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setCustomerId(ep.getExPersonsId());
            item.setName(ep.getExPersonsName());
            item.setPost(ep.getExPersonsPosition());
            if (idList.contains(item.getCustomerId())) item.setChecked(true); // すでに入っているならチェックする
            addCustomerList.add(item); // インスタンスをリストに挿入
        }

        // 自身のアクティビティ、データ、レイアウトを指定
        AddCustomerListAdapter adapter = new AddCustomerListAdapter(AddCustomerActivity.this, addCustomerList, R.layout.add_customer_list_item);
        ListView listView = findViewById(R.id.addCustomer_list_vi_participant); // レイアウト

        listView.setAdapter(adapter);
        // 初期チェック
        /*for (int i = ZERO; i < listView.getCount(); i += 2) {
            View c_listView = adapter.getView(i, null, null);
            CheckBox check = (CheckBox) c_listView.findViewById(R.id.checkbox);
            check.setChecked(true);
        }*/

        // 会社参加人数の表示を更新
        TextView text = findViewById(R.id.addCustomer_tv_selecting);
        text.setText(addCustomerList.parallelStream().filter(AddCustomerListItem::isChecked).count() + "人選択中");

        // リストのアイテムを選択したとき
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // 現在の参加者人数の表示を更新
            switch (view.getId()){
                case R.id.checkbox:
                    text.setText(addCustomerList.parallelStream().filter(AddCustomerListItem::isChecked).count() + "人選択中");
            }
        });
        // リスナー
        // 企業登録画面の起動
        findViewById(R.id.addCustomer_img_bt_add_to).setOnClickListener(view -> {
            Intent intent = new Intent(AddCustomerActivity.this, EditCompanyActivity.class);
            intent.putExtra("会社ID", cmpId);
            startActivity(intent);
        });
        // この画面の終了（確定）
        findViewById(R.id.addCustomer_bt_confirm).setOnClickListener(view -> {
            Intent intent = new Intent();
            ArrayList<Integer> epIdList = addCustomerList.parallelStream().filter(AddCustomerListItem::isChecked).map(AddCustomerListItem::getCustomerId).collect(Collectors.toCollection(ArrayList::new));
            Log.d("eee", String.valueOf(epIdList.size()));
            intent.putExtra("社外参加者リスト", epIdList);
            intent.putExtra("会社ID", cmpId);
            setResult(RESULT_OK, intent);
            finish();
        });
        // この画面の終了（キャンセル）
        findViewById(R.id.addCustomer_bt_cancel).setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        helper.close();
    }
}
