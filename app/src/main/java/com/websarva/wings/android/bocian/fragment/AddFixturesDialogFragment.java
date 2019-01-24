package com.websarva.wings.android.bocian.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.activity.FixturesActivity;
import com.websarva.wings.android.bocian.activity.NewReservationActivity;
import com.websarva.wings.android.bocian.beans.BocianDBHelper;
import com.websarva.wings.android.bocian.beans.Constants;
import com.websarva.wings.android.bocian.data.EquipmentData;
import com.websarva.wings.android.bocian.data.ExternalPersonsData;

import java.util.List;
import java.util.stream.Collectors;

import static com.websarva.wings.android.bocian.beans.Constants.Num.*;

import static com.websarva.wings.android.bocian.beans.ConvertResolution.convertDpPixel;

public class AddFixturesDialogFragment extends DialogFragment {
    // DBヘルパークラス
    private BocianDBHelper helper;
    private SQLiteDatabase db;
    private LinearLayout layout;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // ダイアログビルダーを生成。
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // DBヘルパークラスの生成
        helper = new BocianDBHelper(getActivity());
        db = helper.getReadableDatabase();

        //外枠とパーツの作成
        layout = new LinearLayout(getActivity());
        //上から下にパーツを組み込む設定
        layout.setOrientation(LinearLayout.VERTICAL);

        // データの取得
        List<EquipmentData> eqList = helper.getDataList(db, Constants.DB.tableEquipment, null, null, null);

        //外枠にパーツを組み込む
        layout.addView(setViewMargin(make_TextView("種類")));
        layout.addView(setViewMargin(make_Spinner(eqList.stream().map(EquipmentData::getEqName).toArray(String[]::new))));
        layout.addView(setViewMargin(make_TextView("個数")));
        layout.addView(setViewMargin(make_Spinner(getResources().getStringArray(R.array.num))));

        builder.setPositiveButton("確定", new DialogButtonClickListener());
        builder.setNegativeButton("キャンセル", null);

        AlertDialog dialog = builder.create();

        if (getActivity().getClass() == FixturesActivity.class) {
            // 元画面から値を取得
            int id = getArguments().getInt("id");
            int count = getArguments().getInt("count");
            Spinner sp = (Spinner) layout.getChildAt(1);
            Spinner sp2 = (Spinner) layout.getChildAt(3);
            // 初期位置を設定
            sp.setSelection(eqList.stream().filter(d -> d.getEqId() == id).map(eqList::indexOf).findAny().orElse(ZERO));
            sp2.setSelection(count - ONE);
        }
        //タイトルの設定
        dialog.setTitle("備品追加ダイアログ");
        //レイアウトをダイアログに設定
        dialog.setView(layout);

        return dialog;
    }

    // マージンの設定
    private View setViewMargin(View tv){
        //Viewの大きさを設定
        LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        int margin = (int)convertDpPixel(10, tv.getContext());
        layout_params.setMargins( margin, 0, margin, 0 );    // marginを設ける
        tv.setLayoutParams( layout_params );    // レイアウトを登録
        return tv;
    }
    private TextView make_TextView(String sMessage){
        //テキストビューの生成
        TextView tv = new TextView(getActivity());
        //メッセージの設定
        tv.setText(sMessage);
        return tv;
    }
    private Spinner make_Spinner(String[] list){
        //テキストビューの生成
        Spinner tv = new Spinner(getActivity(), 0); // ダイアログ
        //メッセージの設定
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item);
        adapter.addAll(list);
        tv.setAdapter(adapter);
        return tv;
    }

    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            Spinner sp1 = (Spinner) layout.getChildAt(1);
            Spinner sp2 = (Spinner) layout.getChildAt(3);
            // 内容を元画面に反映する
            if (getActivity().getClass() == FixturesActivity.class) {
                FixturesActivity activity = (FixturesActivity) getActivity();
                activity.fixtureAdd(sp1.getSelectedItem().toString(), Integer.valueOf(sp2.getSelectedItem().toString()));
            }else if(getActivity().getClass() == NewReservationActivity.class){
                NewReservationActivity activity = (NewReservationActivity) getActivity();
                activity.fixtureAdd(sp1.getSelectedItem().toString(), Integer.valueOf(sp2.getSelectedItem().toString()));
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();
        helper.close();
    }
}
