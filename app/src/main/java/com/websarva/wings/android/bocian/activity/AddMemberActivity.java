package com.websarva.wings.android.bocian.activity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.adapter.AddCompanyListAdapter;
import com.websarva.wings.android.bocian.adapter.AddEmployeeListAdapter;
import com.websarva.wings.android.bocian.beans.BocianDBHelper;
import com.websarva.wings.android.bocian.beans.Constants;
import com.websarva.wings.android.bocian.data.CompanyData;
import com.websarva.wings.android.bocian.data.DepartmentData;
import com.websarva.wings.android.bocian.data.EmployeeData;
import com.websarva.wings.android.bocian.data.ExternalPersonsData;
import com.websarva.wings.android.bocian.data.PositionData;
import com.websarva.wings.android.bocian.listItem.AddCompanyListItem;
import com.websarva.wings.android.bocian.listItem.AddCustomerListItem;
import com.websarva.wings.android.bocian.listItem.AddEmployeeListItem;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static com.websarva.wings.android.bocian.beans.Constants.Num.*;

// 社外者追加画面
public class AddMemberActivity extends AppCompatActivity {

    private boolean allCheck = false; // 全選択状態（チェックで切り替えた場合は異なる）
    private Spinner division_spinar; // 部署スピナー
    private Spinner section_spinar; // 課スピナー
    private AddEmployeeListAdapter empAdapter;
    private AddCompanyListAdapter companyAdapter;
    private HashMap<Integer ,ArrayList<AddEmployeeListItem>> addEmployeeMap; // 全ての部署・課ごとのリストを有するmap
    private HashMap<Integer ,ArrayList<Integer>> externalParticipantMap; // ほかの画面に受け渡す社外参加者ID
    private List<AddCompanyListItem>  addCompanyList; // 会社リスト


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        // DBヘルパークラスの生成
        BocianDBHelper helper = new BocianDBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        // 初期設定
        division_spinar = findViewById(R.id.addMemberActivity_spinar_division); // 部署スピナー
        section_spinar = findViewById(R.id.addMemberActivity_spinar_section); // 課スピナー

        // 前画面からデータの受け取り
        ArrayList<Integer> empIdList = (ArrayList<Integer>) getIntent().getSerializableExtra("社内参加者リスト");
        ArrayList<Integer> epIdList = (ArrayList<Integer>) getIntent().getSerializableExtra("社外参加者リスト");
        //helper.setDataList(db,null,null);

        // 社内
        List<EmployeeData> empList = helper.getDataList(db, Constants.DB.tableEmployee, null,null, null);
        List<DepartmentData> depList = helper.getDataList(db, Constants.DB.tableDepartment,null,null,null);
        List<PositionData> posList = helper.getDataList(db, Constants.DB.tablePosition,null,null,null);

        addEmployeeMap = new HashMap<>(); // 全ての部署・課ごとのリストを有するmap
        depList.forEach(t -> addEmployeeMap.put(t.getDepId() , new ArrayList<>())); // 鍵 -> 部署ID データ -> AddEmployeeListItemインスタンス

        // 社員リストの作成
        for (EmployeeData emp : empList) {
            String depName = depList.parallelStream().filter(d -> d.getDepId() == emp.getDepId()).findAny().map(DepartmentData::getDepName).orElse("なし");
            String secName = depList.parallelStream().filter(d -> d.getDepId() == emp.getDepId()).findAny().map(DepartmentData::getSecName).orElse("なし");
            String posName = posList.parallelStream().filter(d -> d.getPosId() == emp.getPosId()).findAny().map(PositionData::getPosName).orElse("なし");

            AddEmployeeListItem AddEmployeeItem = new AddEmployeeListItem();
            AddEmployeeItem.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            AddEmployeeItem.setEmpId(emp.getEmpId());
            AddEmployeeItem.setName(emp.getEmpName());
            AddEmployeeItem.setDivision(depName);
            AddEmployeeItem.setSection(secName);
            AddEmployeeItem.setPost(posName);
            if (empIdList.parallelStream().filter(d -> d == emp.getEmpId()).findAny().orElse(_ONE) != _ONE) AddEmployeeItem.setChecked(true); //
            addEmployeeMap.get(emp.getDepId()).add(AddEmployeeItem); // インスタンスを課ごとのリストに挿入
        }


        // 会社
        List<CompanyData> cmpList = helper.getDataList(db, Constants.DB.tableCompany, null, null,null);
        List<ExternalPersonsData> epList = helper.getDataList(db, Constants.DB.tableExternalPersons, null, null,null);
        externalParticipantMap = new HashMap<>(); // 全ての会社のリストを有するmap
        cmpList.forEach(t -> externalParticipantMap.put(t.getCompId() , new ArrayList<>())); // 鍵 -> 会社ID    データ -> ExternalPersonsDataインスタンス

        // 会社リストの作成
        addCompanyList = new ArrayList<>(); // アダプタのdata部分のリストを作成
        for (CompanyData cmp : cmpList){
            AddCompanyListItem addCompanyItem = new AddCompanyListItem();
            // 会社が同一で参加している場合リストに追加
            externalParticipantMap.put(cmp.getCompId(),
                    epList.parallelStream().filter(d -> d.getCompanyId() == cmp.getCompId() && epIdList.contains(d.getExPersonsId())).map(t-> t.getExPersonsId()).collect(Collectors.toCollection(ArrayList::new)));
            addCompanyItem.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            addCompanyItem.setCompanyId(cmp.getCompId());
            addCompanyItem.setName(cmp.getCompName());
            if (!externalParticipantMap.get(cmp.getCompId()).isEmpty())
                addCompanyItem.setCount(externalParticipantMap.get(cmp.getCompId()).size() + "人");  // 予約画面から値が渡されてきていればセットする処理
            addCompanyList.add(addCompanyItem); // インスタンスをリストに挿入
        }

        // 選択人数の設定
        TextView text = findViewById(R.id.addMemberActivity_tv_choice);
        text.setText(externalParticipantMap.values().parallelStream().mapToInt(List::size).sum() +
                addEmployeeMap.values().parallelStream().mapToLong(t -> t.parallelStream().filter(AddEmployeeListItem::isChecked).count()).sum() + "人選択中");
        // 初期リスト・アダプタの設定
        ListView listView = findViewById(R.id.addMemberActivity_list_vi_person); // レイアウト
        empAdapter = new AddEmployeeListAdapter(AddMemberActivity.this, addEmployeeMap.values().stream().findFirst().get(), R.layout.add_employee_list_item);
        companyAdapter = new AddCompanyListAdapter(AddMemberActivity.this, addCompanyList, R.layout.company_list_item);
        listView.setAdapter(empAdapter);

        // 社内と社外の切り替え
        Spinner sp1 = findViewById(R.id.addMemberActivity_spinar_inside_outside);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                EditText et = findViewById(R.id.addMemberActivity_ed_tv_name);
                ImageButton bt = findViewById(R.id.addMemberActivity_img_bt_plus);
                Button bt2 = findViewById(R.id.addMemberActivity_bt_choice);
                Spinner sp = findViewById(R.id.addMemberActivity_spinar_division);
                Spinner sp2 = findViewById(R.id.addMemberActivity_spinar_section);

                switch (position){
                    case ZERO: // 社内
                        // 検索テキストボックスのヒントを変更
                        et.setHint("なまえ");

                        // 企業登録ボタンを不可視化
                        bt.setVisibility(View.INVISIBLE);

                        // 全選択ボタンを可視化
                        bt2.setVisibility(View.VISIBLE);

                        // スピナーを可視化
                        sp.setVisibility(View.VISIBLE);

                        sp2.setVisibility(View.VISIBLE);
                        // 鍵を取得（主キー以外の文字列2つを突き合わせているが、判別が難しいので仕方ない）
                        Integer key =  depList.parallelStream().filter(d -> d.getDepName()
                                .equals(division_spinar.getSelectedItem()) && d.getSecName().equals(section_spinar.getSelectedItem()))
                                .findAny().map(d -> Integer.valueOf(d.getDepId())).orElse(new Integer(-1));
                        // 鍵を取得（アダプターを生成）
                        empAdapter
                                = new AddEmployeeListAdapter(AddMemberActivity.this, addEmployeeMap.get(key), R.layout.add_employee_list_item);
                        listView.setAdapter(empAdapter);

                        break;
                    case ONE: // 社外
                        // 検索テキストボックスのヒントを変更
                        et.setHint("企業名");

                        // 企業登録ボタンを可視化
                        bt.setVisibility(View.VISIBLE);

                        // 全選択ボタンを不可視化
                        bt2.setVisibility(View.INVISIBLE);

                        // スピナーを不可視化
                        sp.setVisibility(View.INVISIBLE);

                        sp2.setVisibility(View.INVISIBLE);

                        listView.setAdapter(companyAdapter);

                        break;
                }
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        // 部署スピナー要素の作成
        List<String> rStream = depList.parallelStream().map(DepartmentData::getDepName).distinct().collect(Collectors.toList());
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, rStream);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        division_spinar.setAdapter(adapter);
        // リスナーの作成
        division_spinar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                // 課スピナー要素の作成
                List<String> rStream = depList.parallelStream().filter(d -> d.getDepName().equals(division_spinar.getSelectedItem())).map(DepartmentData::getSecName).distinct().collect(Collectors.toList());
                ArrayAdapter adapter = new ArrayAdapter(AddMemberActivity.this, android.R.layout.simple_spinner_item, rStream);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                section_spinar.setAdapter(adapter);

                // 鍵を取得（主キー以外の文字列2つを突き合わせているが、判別が難しいので仕方ない）
                Integer key =  depList.parallelStream().filter(d -> d.getDepName()
                        .equals(division_spinar.getSelectedItem()) && d.getSecName().equals(section_spinar.getSelectedItem()))
                        .findAny().map(d -> Integer.valueOf(d.getDepId())).orElse(new Integer(-1));
                // 鍵を取得（アダプターを生成）
                if (key != _ONE) {
                    empAdapter
                            = new AddEmployeeListAdapter(AddMemberActivity.this, addEmployeeMap.get(key), R.layout.add_employee_list_item);
                    listView.setAdapter(empAdapter);
                }
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });
        // 課スピナー要素の作成
        rStream = depList.parallelStream().filter(d -> d.getDepName().equals(division_spinar.getSelectedItem())).map(DepartmentData::getSecName).distinct().collect(Collectors.toList());
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, rStream);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        section_spinar.setAdapter(adapter);
        // リスナーの作成
        section_spinar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                // 鍵を取得（主キー以外の文字列2つを突き合わせているが、判別が難しいので仕方ない）
                Integer key =  depList.parallelStream().filter(d -> d.getDepName()
                        .equals(division_spinar.getSelectedItem()) && d.getSecName().equals(section_spinar.getSelectedItem()))
                        .findAny().map(d -> Integer.valueOf(d.getDepId())).orElse(new Integer(_ONE));
                // 鍵を取得（アダプターを生成）
                if (key != _ONE) {
                    empAdapter
                            = new AddEmployeeListAdapter(AddMemberActivity.this, addEmployeeMap.get(key), R.layout.add_employee_list_item);
                    listView.setAdapter(empAdapter);
                }
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        // 社外のアイテムを選択したとき
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // 企業が表示されているときに限定する
            if (listView.getAdapter().equals(companyAdapter)) {
                Intent intent = new Intent(AddMemberActivity.this, AddCustomerActivity.class);
                int cmpId = addCompanyList.get(position).getCompanyId();
                intent.putExtra("会社ID" , cmpId);
                intent.putExtra("参加者リスト" , externalParticipantMap.get(cmpId));
                startActivityForResult(intent, ZERO);
            }else{
                // 社内のアイテムを選択したとき
                // 現在の参加者人数の表示を更新
                switch (view.getId()){
                case R.id.checkbox:
                    text.setText(externalParticipantMap.values().parallelStream().mapToInt(List::size).sum() +
                            addEmployeeMap.values().parallelStream().mapToLong(t -> t.parallelStream().filter(AddEmployeeListItem::isChecked).count()).sum() + "人選択中");
                }
            }
        });
        // 企業登録画面の起動
        findViewById(R.id.addMemberActivity_img_bt_plus).setOnClickListener(view -> {
            Intent intent = new Intent(AddMemberActivity.this, EditCompanyActivity.class);
            startActivity(intent);
        });
        // この画面の終了（キャンセル）
        /*findViewById(R.id.addMemberActivity_bt_cancel).setOnClickListener(view -> {
            Intent intent = new Intent(AddMemberActivity.this, MenuActivity.class);
            startActivity(intent);
        });*/
        findViewById(R.id.addMemberActivity_bt_cancel).setOnClickListener(view -> { finish(); });

        // この画面の終了（確定）
        findViewById(R.id.addMemberActivity_bt_confirm).setOnClickListener(view -> {
            Intent intent = new Intent();

            // 参加者リストの作成（社内）
            ArrayList<Integer> inParticipant = new ArrayList<>();
            for(Map.Entry<Integer, ArrayList<AddEmployeeListItem>> item : addEmployeeMap.entrySet())
                item.getValue().parallelStream().filter(AddEmployeeListItem::isChecked).map(AddEmployeeListItem::getEmpId).forEach(t-> inParticipant.add(t));
            // 参加企業リストの作成
            ArrayList<Integer> externalParticipant = new ArrayList<>();
            for (Iterator<Integer> i = externalParticipantMap.keySet().iterator(); i.hasNext();)
                if (externalParticipantMap.get(i.next()).isEmpty())
                    i.remove();
            externalParticipantMap.values().parallelStream().forEach(i -> i.parallelStream().forEach(j -> externalParticipant.add(j)));

            intent.putExtra("社内参加者リスト", inParticipant);
            intent.putExtra("社外参加者リスト", externalParticipant);
            setResult(RESULT_OK, intent);
            finish();
        });

        // 全選択
        findViewById(R.id.addMemberActivity_bt_choice).setOnClickListener(view -> {
            for (int i = ZERO; i < empAdapter.getCount(); i++) {
                View c_listView = empAdapter.getView(i, null, null);
                CheckBox check = (CheckBox) c_listView.findViewById(R.id.checkbox);
                check.setChecked(!allCheck); // 反転させる
                empAdapter.notifyDataSetChanged();
            }
            allCheck = !allCheck; // フラグを反転
            Button btn = findViewById(R.id.addMemberActivity_bt_choice);
            btn.setText(allCheck ? "全解除" : "全選択");
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
        int cmpId = intent.getIntExtra("会社ID", _ONE);
        Log.d("eee", "会社ID" + Long.toString(cmpId));

        if (cmpId != _ONE){
            ArrayList<Integer> epIdList = (ArrayList<Integer>) intent.getSerializableExtra("社外参加者リスト");

            externalParticipantMap.put(cmpId, epIdList);

            // 現在の参加者人数の表示を更新
            TextView text = findViewById(R.id.addMemberActivity_tv_choice);
            text.setText(externalParticipantMap.values().parallelStream().mapToInt(List::size).sum() +
                    addEmployeeMap.values().parallelStream().mapToLong(t -> t.parallelStream().filter(AddEmployeeListItem::isChecked).count()).sum() + "人選択中");
            // 会社参加人数の表示を更新
            AddCompanyListItem CompanyItem = addCompanyList.parallelStream().filter(t -> t.getCompanyId() == cmpId).findAny().orElse( new AddCompanyListItem());
            CompanyItem.setCount(epIdList.size() + "人");
            // アダプタの更新
            companyAdapter.notifyDataSetChanged();
        }
    }
}
