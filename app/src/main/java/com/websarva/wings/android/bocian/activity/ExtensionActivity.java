package com.websarva.wings.android.bocian.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.fragment.ExtensionDialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ExtensionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extension);

        /**@author 小倉
         * Spinner(時)のId取得
         * 21時が選択されたときのsetOnItemListenerのltのインスタンス化とspinnerにltの設定
         * 後ほど21時を選択した場合のSpinner(分)を不活性にするための処理使用するため
         */
        TextView useFinishTime = findViewById(R.id.extension_lastTime);
        TextView time = findViewById(R.id.extension_tv_remainingTime);
        Spinner sp = findViewById(R.id.extension_spinar_hour);
        Litenner lt = new Litenner();
        sp.setOnItemSelectedListener(lt);

        /**@author 小倉
         * 残り時間の欄に( 利用終了時刻 - 現在時刻 )を格納(フォーマットはHH:mm)
         */
        try {
            time.setText(remainingTime(changeDateData(useFinishTime.getText().toString()),getNowDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /**@author 小倉
         * 確定ボタンの処理
         */
        findViewById(R.id.extension_bt_confirm).setOnClickListener(v -> {
            ExtensionDialogFragment extensionDialogFragment = new ExtensionDialogFragment();
            extensionDialogFragment.show(getSupportFragmentManager(),"ExtensionDialogFragment");
        });
        /**@author 小倉
         * キャンセルボタンの処理
         */
        findViewById(R.id.extension_bt_cancel).setOnClickListener( v -> finish());

    }

    /**@author 小倉
     * 現在日時をyyyy/MM/dd HH:mm:ss形式で取得する.<br>
     * @return nowDate system.currentMills()から取得した現在時刻(system.currentMills()はDateを
     *                 インスタンス化した際に自動で用意され、現在日時を自動取得する
     */
    public static Date getNowDate(){
        final Date nowDate = new Date();
        return nowDate;
    }

    /**@author 小倉
     * @param  time
     * @return endtime String型からDate型に変換された利用終了時刻(延長前)
     * @throws ParseException
     */
    public static Date changeDateData(String time) throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        final Date endTime = sdf.parse(time);
        return endTime;
    }

    /**
     *
     * @param from
     * @param to
     * @return remainingTime
     */
    public static String remainingTime(Date from,Date to){
        final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        //Calenderクラスで現在日時を取得
        Calendar now = Calendar.getInstance();
        now.setTime(to);
        //Calenderクラスで利用終了時刻を取得
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(from);

        endTime.add(Calendar.HOUR,-(now.get(Calendar.HOUR)));
        endTime.add(Calendar.MINUTE,-(now.get(Calendar.MINUTE)));

        String remainingTime = sdf.format(endTime.getTime());

        return remainingTime;
    }
//(小倉)スピナー内のアイテムが選択されたときのリスナー
//(小倉)21時以降は延長できないので21時を選択した場合は「00」分に設定する
    private class Litenner implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // On selecting a spinner item
            Object item = parent.getItemAtPosition(position);
            Spinner sp2 = findViewById(R.id.extension_spinar_minutes);

            //「positon + 1」 は　lengthの開始オリジンが違うため
            if(getResources().getStringArray(R.array.spinner_1).length == position + 1){
                sp2.setSelection(0);
                sp2.setEnabled(false);
            }else{
                sp2.setEnabled(true);
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}

