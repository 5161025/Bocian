package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.beans.BocianDBHelper;
import com.websarva.wings.android.bocian.beans.Constants;
import com.websarva.wings.android.bocian.data.EquipmentData;
import com.websarva.wings.android.bocian.data.InParticipantData;
import com.websarva.wings.android.bocian.data.ReserveData;
import com.websarva.wings.android.bocian.fragment.AddFixturesDialogFragment;
import com.websarva.wings.android.bocian.listItem.AddCompanyListItem;
import com.websarva.wings.android.bocian.listItem.AddEmployeeListItem;
import com.websarva.wings.android.bocian.listItem.FixturesListItem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static com.websarva.wings.android.bocian.beans.Constants.Num.ONE;
import static com.websarva.wings.android.bocian.beans.Constants.Num.ZERO;
import static com.websarva.wings.android.bocian.beans.Constants.Num._ONE;

// 新規予約画面
public class NewReservationActivity extends AppCompatActivity {
    // DBヘルパークラス
    private BocianDBHelper helper;
    private SQLiteDatabase db;

    private ArrayList<Integer> empIdList; // 社内参加者リスト
    private ArrayList<Integer> epIdList;  // 社外参加者リスト
    private HashMap<Integer, Integer> fixtureMap;  // 備品　ID,個数のリスト
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        // setTitle(R.string.edit_reservation);

        // DBヘルパークラスの生成
        helper = new BocianDBHelper(this);
        db = helper.getReadableDatabase();

        // 空のデータを生成
        empIdList = new ArrayList<>();
        epIdList = new ArrayList<>();
        fixtureMap = new HashMap<>(); // ID,個数

        // 備品確認画面を起動
        findViewById(R.id.newReservation_bt_fixturesConfirmation).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, FixturesActivity.class);
            intent.putExtra("備品リスト", fixtureMap);
            startActivityForResult(intent, ONE);
        });

        // 備品追加ダイアログの出現
        findViewById(R.id.newReservation_bt_fixturesAdd).setOnClickListener(view -> {
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

        // 参加者確認画面を起動
        findViewById(R.id.newReservation_bt_paticipantConfirmation).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, ParticipantsActivity.class);
            intent.putExtra("社内参加者リスト",empIdList);
            intent.putExtra("社外参加者リスト",epIdList);
            startActivityForResult(intent, ZERO);
        });

        // 参加者追加画面を起動
        findViewById(R.id.newReservation_bt_paticipantAdd).setOnClickListener(view -> {
            Intent intent = new Intent(NewReservationActivity.this, AddMemberActivity.class);
            intent.putExtra("社内参加者リスト",empIdList);
            intent.putExtra("社外参加者リスト",epIdList);
            startActivityForResult(intent, ZERO);
        });


        // この画面の終了（キャンセル）
        findViewById(R.id.newReservation_bt_Cancel).setOnClickListener(view -> { finish(); });

        // この画面の終了（確定）
        findViewById(R.id.newReservation_bt_Confirm).setOnClickListener(view -> {
            if (false) {
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
                empIdList.stream().forEach(d ->
                        InParticipantList.add(new InParticipantData(0, resId, d, 0))
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
        if (resultCode == RESULT_OK) {
            TextView text;
            switch (requestCode){
            case ZERO:
                empIdList = (ArrayList<Integer>) intent.getSerializableExtra("社内参加者リスト");
                epIdList = (ArrayList<Integer>) intent.getSerializableExtra("社外参加者リスト");

                // 現在の参加者人数の表示を更新
                text = findViewById(R.id.newReservation_tv_paticipantSelecting);
                text.setText((empIdList.size() + epIdList.size()) + "人選択中");
                break;
            case ONE:
                fixtureMap = (HashMap<Integer, Integer>) intent.getSerializableExtra("備品リスト");

                // 現在の備品個数の表示を更新
                text = findViewById(R.id.newReservation_tv_fixturesSelecting);
                text.setText((fixtureMap.size()) + "種選択中");
                break;
            }
        }
    }
    public void fixtureAdd(String name, int count){

        // データの取得
        List<EquipmentData> eqList = helper.getDataList(db, Constants.DB.tableEquipment, null, null, null);
        // データの追加
        EquipmentData fixture = eqList.parallelStream().filter(d -> d.getEqName().equals(name)).findAny().get();
        fixtureMap.put(fixture.getEqId(), count);

        // 現在の備品個数の表示を更新
        TextView text = findViewById(R.id.newReservation_tv_fixturesSelecting);
        text.setText((fixtureMap.size()) + "種選択中");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        helper.close();
    }
}
