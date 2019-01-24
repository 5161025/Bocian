package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Switch;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.beans.BocianDBHelper;
import com.websarva.wings.android.bocian.data.InParticipantData;
import com.websarva.wings.android.bocian.data.ReserveData;
import com.websarva.wings.android.bocian.fragment.AddFixturesDialogFragment;
import com.websarva.wings.android.bocian.listItem.AddEmployeeListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.websarva.wings.android.bocian.beans.Constants.Num.ONE;
import static com.websarva.wings.android.bocian.beans.Constants.Num.ZERO;

// 新規予約画面
public class NewReservationActivity extends AppCompatActivity {
    private ArrayList<Integer> empIdList; // 社内参加者リスト
    private ArrayList<Integer> epIdList;  // 社外参加者リスト
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        // setTitle(R.string.edit_reservation);

        // nullにしておく
        empIdList = null;
        epIdList = null;

        // 備品確認画面を起動
        findViewById(R.id.newReservation_bt_fixturesConfirmation).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, FixturesActivity.class);
            startActivity(intent);
        });

        // 備品追加ダイアログの出現
        findViewById(R.id.newReservation_bt_fixturesAdd).setOnClickListener(view -> {
            AddFixturesDialogFragment dialog = new AddFixturesDialogFragment();
            getSupportFragmentManager();
            dialog.show(getFragmentManager(), "AddFixturesDialogFragment");
        });

        // 参加者確認画面を起動
        findViewById(R.id.newReservation_bt_paticipantConfirmation).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, ParticipantsActivity.class);
            startActivity(intent);
        });

        // 参加者追加画面を起動
        findViewById(R.id.newReservation_bt_paticipantAdd).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, AddMemberActivity.class);
            startActivity(intent);
        });


        // この画面の終了（キャンセル）
        findViewById(R.id.newReservation_bt_Cancel).setOnClickListener(view -> { finish(); });

        // この画面の終了（確定）
        findViewById(R.id.newReservation_bt_Confirm).setOnClickListener(view -> {
            if (epIdList == null) {
            /*
            予約ID
            integer		予約ID			resId			（主キー）※1
            integer		予約者端末番号	resTerminalNumber	（外部キー）
            integer		目的ID			pupId			（外部キー）
            integer		会議室ID		confRoomId		（外部キー）
            integer		定期会議ID	 	regId			（外部キー）
            text		利用日 		useDay
            text		開始時刻 		startTime
            text		終了時刻 		endTime
            integer		社外者人数 		numExPersons
            integer		予約状態フラグ 	flg
            text		備考 			remarks
            */

                // 定期会議　トグルの取得
                Switch sw = findViewById(R.id.newReservation_sw_reservation);
                // 目的ID　スピナーの取得
                Spinner pup = findViewById(R.id.newReservation_spinar_porposeSpinner);
                // 会議室　スピナーの取得
                Spinner reg = findViewById(R.id.newReservation_spinar_conferencePos);


                int resId = 0; // 予約ID
                int resTerminalNumber = 0; // 予約者端末番号
                int pupId = 0; // 目的ID
                int confRoomId = 0; // 会議室ID
                int regId = 0; // 定期会議ID
                String useDay = new String(""); // 利用日
                String startTime = new String(""); // 開始時刻
                String endTime = new String(""); // 終了時刻
                int numExPersons = epIdList.size(); // 社外者人数
                int flg = 1; // 予約状態フラグ
                String remarks = new String(""); // 備考
                //new ReserveData();
            /*
            定期会議テーブル 			RegularMeeting
            integer		定期会議ID		regId			（主キー）※1
            text		曜日 			regDay
            */

                regId = sw.isChecked() ? ONE : ZERO;
                String regDay;
            /*
            社外参加者テーブル 			ExternalParticipant
            integer		社外参加者ID		exParticipantId	（主キー）
            integer		予約ID			reserveId		（外部キー）
            integer		社外者ID		exPersonsId		（外部キー）
            */
            /*
            社内参加者テーブル			InParticipant
            integer		社内参加者ID		inParticipantId		（主キー）
            integer		予約ID			reserveId		（外部キー）
            integer		社員ID			employeeId		（外部キー）
            integer		参加フラグ		flg
            */

                List<InParticipantData> InParticipantList = new ArrayList<>();
                empIdList.stream().forEach(t ->
                        InParticipantList.add(new InParticipantData(0, resId, t, 0))
                );
                for (int i = 0; i < empIdList.size(); i++) {

                }

                //finish();
            }
        });
    }

    // startActivityForResult で起動させたアクティビティが
    // finish() により破棄されたときにコールされる
    // requestCode : startActivityForResult の第二引数で指定した値が渡される
    // resultCode : 起動先のActivity.setResult の第一引数が渡される
    // Intent intent : 起動先Activityから送られてくる Intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        ArrayList<Integer> hoge = (ArrayList<Integer>) intent.getSerializableExtra("社内参加者リスト");
        ArrayList<Integer> huga = (ArrayList<Integer>) intent.getSerializableExtra("社外参加者リスト");
    }
}
