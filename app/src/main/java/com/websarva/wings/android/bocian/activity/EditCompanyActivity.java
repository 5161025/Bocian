package com.websarva.wings.android.bocian.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.EditCompanyListAdapter;
import com.websarva.wings.android.bocian.beans.BocianDBHelper;
import com.websarva.wings.android.bocian.beans.Constants;
import com.websarva.wings.android.bocian.data.CompanyData;
import com.websarva.wings.android.bocian.data.ExternalPersonsData;
import com.websarva.wings.android.bocian.fragment.EditCompanyDialogFragment;
import com.websarva.wings.android.bocian.listItem.EditCompanyListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.websarva.wings.android.bocian.beans.Constants.Num.*;

// 会社編集画面
public class EditCompanyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_company);

        // DBヘルパークラスの生成
        BocianDBHelper helper = new BocianDBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        // 前画面からデータの受け取り
        setContentView(R.layout.activity_add_customer);
        int cmpId = getIntent().getIntExtra("会社ID", _ONE);


        // 画面名変更
        if(cmpId != _ONE){
            //setTitle(R.string.register_company);
            //TextView button = findViewById(R.id.button4);

            // 会社
            List<CompanyData> cmpList = helper.getDataList(db, Constants.DB.tableCompany, "compId = ?" , new String[]{Integer.toString(cmpId)}, null);

            // 社外者リストの作成
            List<ExternalPersonsData> epList = helper.getDataList(db, Constants.DB.tableExternalPersons, "companyId = ?", new String[]{Integer.toString(cmpId)}, null);
        }
        // ここは繰り返しに利用しているだけ
        String names[]      = getResources().getStringArray(R.array.customerNameList);
        String posts[]      = getResources().getStringArray(R.array.customerPostList);

        List<EditCompanyListItem> data = new ArrayList<>(); // アダプタのdata部分のリストを作成
        // インスタンス生成してセットしている
        for (int i = Constants.Num.ZERO; i < names.length; i++) {
            EditCompanyListItem item = new EditCompanyListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setName(names[i]);
            item.setPost(posts[i]);
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        EditCompanyListAdapter adapter = new EditCompanyListAdapter(EditCompanyActivity.this, data, R.layout.register_custmer_list_item);
        ListView listView = findViewById(R.id.editCompany_list_vi_company); // レイアウト
        listView.setAdapter(adapter);

        // 社外者追加ダイアログの出現
        findViewById(R.id.editCompany_img_tv_plus).setOnClickListener(view ->{
            EditCompanyDialogFragment dialog = new EditCompanyDialogFragment();
            getSupportFragmentManager();
            dialog.show(getFragmentManager(), "EditCompanyDialogFragment");
        });

        // この画面の終了（確定）
        findViewById(R.id.editCompany_bt_Confirm).setOnClickListener(view -> { finish(); });

        // この画面の終了（キャンセル）
        findViewById(R.id.editCompany_bt_Cancel).setOnClickListener(view -> { finish(); });
    }
}
