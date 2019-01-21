package com.websarva.wings.android.bocian.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.websarva.wings.android.bocian.R;

import java.util.ArrayList;
import java.util.HashMap;

public class UsageHisActivity extends AppCompatActivity {

    // newNoodleの1-30が追加されたかどうかのフラグ true:追加された false:まだ追加されてない
    boolean newNoodleAddFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_his);

        // ListViewに表示する項目を生成
        ArrayList<HashMap<String, String>> listData = new ArrayList<>();

        String[] da = {"2018年8月10日 金曜日", "2018年8月13日 月曜日", "2018年8月15日 水曜日", "2018年8月22日 水曜日"
                      ,"2018年8月23日 木曜日", "2018年8月27日 月曜日", "2018年8月29日 水曜日"};

        String[] purpose = {"研修・セミナー", "営業", "打ち合わせ", "スケジューリング会議"
                           ,"アイデア出し会議","コーチング会議","プレゼンテーション"};

        String[] time = {"9:00~10:00","9:00~10:00","10:00~11:00","10:15~11:15"
                        ,"13:00~15:00","14:00~17:00","10:00~12:00"};

        String[] companyName = {"日本IT株式会社","株式会社TWW","株式会社APEX","日本製造株式会社"
                                ,"筒泉銀行株式会社","株式会社APEX","株式会社APEX"};

        String[] name = {"予約者:村尾 友彦","予約者:沼田 和博","予約者:赤坂 佑介","予約者:中 創"
                        ,"予約者:藤沢 知也","予約者:宮崎 俊一","予約者:安本 真美"};


        for (int i = 0; i < 7; i++) {
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
                R.layout.custom_list_layout, // 自作したレイアウト
                new String[]{"date", "plans", "time","cname","name"}, // どの項目を
                new int[]{R.id.usageHis_cl_tv_date, R.id.usageHis_cl_tv_purpose, R.id.usageHis_cl_tv_time, R.id.usageHis_cl_tv_companyname,R.id.usageHis_cl_tv_outername} // どのidの項目に入れるか
        );
        // idがlistのListViewを取得
        ListView listView = (ListView) findViewById(R.id.usageHis_list_vi_history);
        listView.setAdapter(simpleAdapter);


//        listView.setOnItemClickListener();
    }

}