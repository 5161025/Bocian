package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.AddEmployeeListAdapter;
import com.websarva.wings.android.bocian.adapter.ParticipantsListAdapter;
import com.websarva.wings.android.bocian.beans.BocianDBHelper;
import com.websarva.wings.android.bocian.beans.Constants;
import com.websarva.wings.android.bocian.data.CompanyData;
import com.websarva.wings.android.bocian.data.DepartmentData;
import com.websarva.wings.android.bocian.data.EmployeeData;
import com.websarva.wings.android.bocian.data.ExternalPersonsData;
import com.websarva.wings.android.bocian.data.PositionData;
import com.websarva.wings.android.bocian.listItem.AddEmployeeListItem;
import com.websarva.wings.android.bocian.listItem.ParticipantsListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.websarva.wings.android.bocian.beans.Constants.Num.*;

// 参加者確認画面
public class ParticipantsActivity extends AppCompatActivity {
    private List<ParticipantsListItem> data;
    private ParticipantsListAdapter adapter;
    BocianDBHelper helper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);
        // DBヘルパークラスの生成
        helper = new BocianDBHelper(this);
        db = helper.getReadableDatabase();

        // 前画面からデータの受け取り
        ArrayList<Integer> empIdList = (ArrayList<Integer>) getIntent().getSerializableExtra("社内参加者リスト");
        ArrayList<Integer> epIdList = (ArrayList<Integer>) getIntent().getSerializableExtra("社外参加者リスト");

        // 社内
        List<EmployeeData> empList = helper.getDataList(db, Constants.DB.tableEmployee, null,null, null);
        List<DepartmentData> depList = helper.getDataList(db, Constants.DB.tableDepartment,null,null,null);
        List<PositionData> posList = helper.getDataList(db, Constants.DB.tablePosition,null,null,null);

        // 社員リストの作成
        empList = empList.parallelStream().filter(d -> empIdList.contains(d.getEmpId())).collect(Collectors.toList()); // 参加者のみ取得
        data = new ArrayList<>(); // アダプタのdata部分のリストを作成

        for (EmployeeData emp : empList) {
            String depName = depList.parallelStream().filter(d -> d.getDepId() == emp.getDepId()).findAny().map(DepartmentData::getDepName).orElse("なし");
            String secName = depList.parallelStream().filter(d -> d.getDepId() == emp.getDepId()).findAny().map(DepartmentData::getSecName).orElse("なし");
            String posName = posList.parallelStream().filter(d -> d.getPosId() == emp.getPosId()).findAny().map(PositionData::getPosName).orElse("なし");

            ParticipantsListItem item = new ParticipantsListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setParticipantsId(emp.getEmpId());
            item.setName(emp.getEmpName());
            item.setPost(posName);
            item.setInParticipant(true);
            data.add(item); // インスタンスをリストに挿入
        }
        // 会社
        List<CompanyData> cmpList = helper.getDataList(db, Constants.DB.tableCompany, null, null,null);
        List<ExternalPersonsData> epList = helper.getDataList(db, Constants.DB.tableExternalPersons, null, null,null);
        epList = epList.parallelStream().filter(d -> epIdList.contains(d.getExPersonsId())).collect(Collectors.toList()); // 参加者のみ取得
        for (ExternalPersonsData ep : epList) {
            ParticipantsListItem item = new ParticipantsListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setParticipantsId(ep.getExPersonsId());
            item.setPost(ep.getExPersonsPosition());
            item.setName(ep.getExPersonsName());
            item.setInParticipant(false);
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        adapter = new ParticipantsListAdapter(ParticipantsActivity.this, data, R.layout.member_list_item);
        ListView listView = findViewById(R.id.Participants_list_v_fixtures); // レイアウト
        listView.setAdapter(adapter);

        // 現在の参加者人数の表示を更新
        TextView text = findViewById(R.id.Participants_tv_fixtures_choice);
        text.setText(data.size() + "人選択中");

        // 社外のアイテムを選択したとき
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // 企業が表示されているときに限定する
            // 社内のアイテムを選択したとき
            // 現在の参加者人数の表示を更新
            switch (view.getId()){
                case R.id.delete:
                    data.remove(position);
                    text.setText(data.size() + "人選択中");
                    adapter.notifyDataSetChanged();
            }
        });

        // 参加者追加画面を起動
        findViewById(R.id.Participants_img_bt_plus).setOnClickListener(view -> {
            Intent intent = new Intent(ParticipantsActivity.this, AddMemberActivity.class);
            // データのクリア
            empIdList.clear();
            epIdList.clear();
            // 再セット
            for (Iterator<ParticipantsListItem> i = data.iterator(); i.hasNext();) {
                ParticipantsListItem item = i.next();
                if (item.isInParticipant())
                    empIdList.add(item.getParticipantsId());
                else
                    epIdList.add(item.getParticipantsId());
            }
            intent.putExtra("社内参加者リスト",empIdList);
            intent.putExtra("社外参加者リスト",epIdList);
            startActivityForResult(intent, ZERO);
        });

        // この画面の終了（キャンセル）
        findViewById(R.id.Participants_bt_cancel).setOnClickListener(view -> { finish(); });

        // この画面の終了（確定）
        findViewById(R.id.Participants_bt_confirm).setOnClickListener(view -> {
            Intent intent = new Intent();
            // データのクリア
            empIdList.clear();
            epIdList.clear();
            // 再セット
            for (Iterator<ParticipantsListItem> i = data.iterator(); i.hasNext();) {
                ParticipantsListItem item = i.next();
                if (item.isInParticipant())
                    empIdList.add(item.getParticipantsId());
                else
                    epIdList.add(item.getParticipantsId());
            }
            intent.putExtra("社内参加者リスト", empIdList);
            intent.putExtra("社外参加者リスト", epIdList);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            ArrayList<Integer> empIdList = (ArrayList<Integer>) intent.getSerializableExtra("社内参加者リスト");
            ArrayList<Integer> epIdList = (ArrayList<Integer>) intent.getSerializableExtra("社外参加者リスト");

            // 社内
            List<EmployeeData> empList = helper.getDataList(db, Constants.DB.tableEmployee, null,null, null);
            List<DepartmentData> depList = helper.getDataList(db, Constants.DB.tableDepartment,null,null,null);
            List<PositionData> posList = helper.getDataList(db, Constants.DB.tablePosition,null,null,null);

            // 社員リストの作成
            empList = empList.parallelStream().filter(d -> empIdList.contains(d.getEmpId())).collect(Collectors.toList()); // 参加者のみ取得
            data.clear();

            for (EmployeeData emp : empList) {
                String depName = depList.parallelStream().filter(d -> d.getDepId() == emp.getDepId()).findAny().map(DepartmentData::getDepName).orElse("なし");
                String secName = depList.parallelStream().filter(d -> d.getDepId() == emp.getDepId()).findAny().map(DepartmentData::getSecName).orElse("なし");
                String posName = posList.parallelStream().filter(d -> d.getPosId() == emp.getPosId()).findAny().map(PositionData::getPosName).orElse("なし");

                ParticipantsListItem item = new ParticipantsListItem();
                item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
                item.setParticipantsId(emp.getEmpId());
                item.setName(emp.getEmpName());
                item.setPost(posName);
                item.setInParticipant(true);
                data.add(item); // インスタンスをリストに挿入
            }
            // 会社
            List<CompanyData> cmpList = helper.getDataList(db, Constants.DB.tableCompany, null, null,null);
            List<ExternalPersonsData> epList = helper.getDataList(db, Constants.DB.tableExternalPersons, null, null,null);
            epList = epList.parallelStream().filter(d -> epIdList.contains(d.getExPersonsId())).collect(Collectors.toList()); // 参加者のみ取得
            for (ExternalPersonsData ep : epList) {
                ParticipantsListItem item = new ParticipantsListItem();
                item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
                item.setParticipantsId(ep.getExPersonsId());
                item.setPost(ep.getExPersonsPosition());
                item.setName(ep.getExPersonsName());
                item.setInParticipant(false);
                data.add(item); // インスタンスをリストに挿入
            }
            // 現在の参加者人数の表示を更新
            TextView text = findViewById(R.id.Participants_tv_fixtures_choice);
            text.setText(data.size() + "人選択中");
            adapter.notifyDataSetChanged();

            Log.d("empIdList", empIdList.toString());
            Log.d("epIdList", epIdList.toString());
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
        helper.close();
    }
}
