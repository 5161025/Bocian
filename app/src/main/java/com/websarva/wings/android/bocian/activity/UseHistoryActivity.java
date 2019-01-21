package com.websarva.wings.android.bocian.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.websarva.wings.android.bocian.R;

import java.util.ArrayList;
import java.util.HashMap;

// 利用履歴画面
public class UseHistoryActivity extends AppCompatActivity {

    // newNoodleの1-30が追加されたかどうかのフラグ true:追加された false:まだ追加されてない
    boolean newNoodleAddFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_history);

        // ListViewに表示する項目を生成
        ArrayList<HashMap<String, String>> listData = new ArrayList<>();

        String[] da = {"2018年8月30日 木曜日", "2018年9月1日 土曜日", "2018年9月3日 月曜日", "2018年9月10日 月曜日"};

        String[] purpose = {"研修", "営業", "打ち合わせ", "スケジューリング会議"};

        String[] time = {"9:00~10:00","9:00~10:00","10:00~11:00","10:15~11:15"};

        String[] companyName = {"PDS株式会社","UUUM株式会社","株式会社GENESIS ONE","いちから株式会社"};

        String[] name = {"予約者:田中太郎","予約者:佐藤太郎","予約者:草加雅人","予約者:檀隼人"};


        for (int i = 0; i < 4; i++) {
            HashMap<String, String> data = new HashMap<>();
            data.put("date", da[i]);            //日付
            data.put("plans", purpose[i]);        //目的
            data.put("time", time[i]);          //時間
            data.put("cname", companyName[i]);          //会社名
            data.put("name",name[i]);
            listData.add(data);
        }
        /**
         * Adapterを生成
         *リストビュー自身のレイアウト。今回は自作。
         *受け渡し元項目名
         *受け渡し先ID
         */
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                listData, // 使用するデータ
                R.layout.use_history_list_item, // 自作したレイアウト
                new String[]{"date", "plans", "time","cname","name"}, // どの項目を
                new int[]{R.id.useHistory_cl_tv_date, R.id.useHistory_cl_tv_purpose, R.id.useHistory_cl_tv_time, R.id.useHistory_cl_tv_companyname,R.id.useHistory_cl_tv_outername} // どのidの項目に入れるか
        );
        // idがlistのListViewを取得
        ListView listView = (ListView) findViewById(R.id.useHistory_list_vi_history);
        listView.setAdapter(simpleAdapter);


//        listView.setOnItemClickListener();
    }

}