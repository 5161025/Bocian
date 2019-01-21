package com.websarva.wings.android.bocian.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;

import static com.websarva.wings.android.bocian.beans.ConvertResolution.convertDpPixel;

public class AddFixturesDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // ダイアログビルダーを生成。
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //外枠とパーツの作成
        LinearLayout layout = new LinearLayout(getActivity());
        //上から下にパーツを組み込む設定
        layout.setOrientation(LinearLayout.VERTICAL);

        //外枠にパーツを組み込む
        layout.addView(setViewMargin(make_TextView("種類")));
        layout.addView(setViewMargin(make_Spinner(getResources().getStringArray(R.array.fixtures))));
        layout.addView(setViewMargin(make_TextView("個数")));
        layout.addView(setViewMargin(make_Spinner(getResources().getStringArray(R.array.num))));

        builder.setPositiveButton("確定", new DialogButtonClickListener());
        builder.setNegativeButton("キャンセル", new DialogButtonClickListener());

        AlertDialog dialog = builder.create();

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

        }
    }
}
