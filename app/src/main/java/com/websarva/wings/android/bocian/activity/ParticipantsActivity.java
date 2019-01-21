package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.ParticipantsListAdapter;
import com.websarva.wings.android.bocian.listItem.ParticipantsListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParticipantsActivity extends AppCompatActivity {

    public static final int ZERO = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);
        // ここは繰り返しに利用しているだけ
        String names[]      = getResources().getStringArray(R.array.nameList);
        String posts[]      = getResources().getStringArray(R.array.postList);

        List<ParticipantsListItem> data = new ArrayList<>(); // アダプタのdata部分のリストを作成
        // インスタンス生成してセットしている
        for (int i = ZERO; i < names.length; i++) {
            ParticipantsListItem item = new ParticipantsListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setName(names[i]);
            item.setPost(posts[i]);
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        ParticipantsListAdapter adapter = new ParticipantsListAdapter(ParticipantsActivity.this, data, R.layout.member_list_item);
        ListView listView = findViewById(R.id.list); // レイアウト
        listView.setAdapter(adapter);

        // 参加者追加画面を起動
        findViewById(R.id.imageView3).setOnClickListener(view -> {
            Intent intent = new Intent(ParticipantsActivity.this, AddMemberActivity.class);
            startActivity(intent);
        });

        // この画面の終了（キャンセル）
        findViewById(R.id.button2).setOnClickListener(view -> { finish(); });

        // この画面の終了（確定）
        findViewById(R.id.button4).setOnClickListener(view -> { finish(); });
    }
}
