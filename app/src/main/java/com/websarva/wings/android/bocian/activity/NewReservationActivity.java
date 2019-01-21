package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.fragment.AddFixturesDialogFragment;

public class NewReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        // setTitle(R.string.edit_reservation);

        // 備品追加ダイアログの出現
        findViewById(R.id.button10).setOnClickListener(view -> {
            AddFixturesDialogFragment dialog = new AddFixturesDialogFragment();
            getSupportFragmentManager();
            dialog.show(getFragmentManager(), "AddFixturesDialogFragment");
        });

        // 参加者追加画面を起動
        findViewById(R.id.button5).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, AddMemberActivity.class);
            startActivity(intent);
        });

        // 備品確認画面を起動
        findViewById(R.id.button9).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, FixturesActivity.class);
            startActivity(intent);
        });

        // 参加者確認画面を起動
        findViewById(R.id.button7).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, ParticipantsActivity.class);
            startActivity(intent);
        });

        // この画面の終了（キャンセル）
        findViewById(R.id.button2).setOnClickListener(view -> { finish(); });

        // この画面の終了（確定）
        findViewById(R.id.button4).setOnClickListener(view -> { finish(); });
    }
}
