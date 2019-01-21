package com.websarva.wings.android.bocian.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.EditCompanyListAdapter;
import com.websarva.wings.android.bocian.fragment.EditCompanyDialogFragment;
import com.websarva.wings.android.bocian.listItem.EditCompanyListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EditCompanyActivity extends AppCompatActivity {

    public static final int ZERO = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_company);

        // 画面名変更
        if(true){
            //setTitle(R.string.register_company);
            //TextView button = findViewById(R.id.button4);
        }
        // ここは繰り返しに利用しているだけ
        String names[]      = getResources().getStringArray(R.array.customerNameList);
        String posts[]      = getResources().getStringArray(R.array.customerPostList);

        List<EditCompanyListItem> data = new ArrayList<>(); // アダプタのdata部分のリストを作成
        // インスタンス生成してセットしている
        for (int i = ZERO; i < names.length; i++) {
            EditCompanyListItem item = new EditCompanyListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setName(names[i]);
            item.setPost(posts[i]);
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        EditCompanyListAdapter adapter = new EditCompanyListAdapter(EditCompanyActivity.this, data, R.layout.register_custmer_list_item);
        ListView listView = findViewById(R.id.list); // レイアウト
        listView.setAdapter(adapter);

        // 社外者追加ダイアログの出現
        findViewById(R.id.button8).setOnClickListener(view ->{
            EditCompanyDialogFragment dialog = new EditCompanyDialogFragment();
            getSupportFragmentManager();
            dialog.show(getFragmentManager(), "EditCompanyDialogFragment");
        });

        // この画面の終了（確定）
        findViewById(R.id.button4).setOnClickListener(view -> { finish(); });

        // この画面の終了（キャンセル）
        findViewById(R.id.button2).setOnClickListener(view -> { finish(); });
    }
}
