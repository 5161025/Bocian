package com.websarva.wings.android.bocian.activity;
/*

*************管理者予約確認画面************

 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.websarva.wings.android.bocian.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AdominiConActivity extends AppCompatActivity {

    // newNoodleの1-30が追加されたかどうかのフラグ true:追加された false:まだ追加されてない
    boolean newNoodleAddFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adomini_con);

        // ListViewに表示する項目を生成
        ArrayList<HashMap<String, String>> listData = new ArrayList<>();

        String[] da = {"2018年8月30日 木曜日", "2018年9月1日 土曜日", "2018年9月3日 月曜日", "2018年9月10日 月曜日"};

        String[] plans = {"研修", "営業", "打ち合わせ", "スケジューリング会議"};

        String[] time = {"9:00~10:00","9:00~10:00","10:00~11:00","10:15~11:15"};

        String[] area = {"特別","会議室B","会議室A","会議室C"};

        String[] name = {"予約者:田中太郎", "予約者:佐藤太郎", "予約者:草加雅人", "予約者:檀黎斗"};

      //  String[] name = {"予約者:田中太郎", "予約者:佐藤太郎", "予約者:草加雅人", "予約者:檀黎斗"};

        for (int i = 0; i < 4; i++) {
            HashMap<String, String> data = new HashMap<>();
            data.put("date", da[i]);            //日付
            data.put("plans", plans[i]);        //目的
            data.put("time", time[i]);          //時間
            data.put("area",area[i]);           //場所
            data.put("name", name[i]);          //予約者
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
                R.layout.book_custom_list_layout, // 自作したレイアウト
                new String[]{"date", "plans", "time", "area", "name"}, // どの項目を
                new int[]{R.id.bookingCon_tv_date, R.id.bookingCon_tv_plans, R.id.bookingCon_tv_time,R.id.bookingCon_tv_area ,R.id.bookingCon_tv_name} // どのidの項目に入れるか
        );
        // idがlistのListViewを取得
        ListView listView = (ListView) findViewById(R.id.adominiCon_list_con);
        listView.setAdapter(simpleAdapter);
    }
}