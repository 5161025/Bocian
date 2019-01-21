package com.websarva.wings.android.bocian.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.websarva.wings.android.bocian.R;

import static com.websarva.wings.android.bocian.beans.ConvertResolution.convertDpPixel;

public class EditCompanyDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // ダイアログビルダーを生成。
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
        //エディットテキストの生成
        EditText editText1 = new EditText(getActivity());
        EditText editText2 = new EditText(getActivity());
        EditText editText3 = new EditText(getActivity());

        //外枠とパーツの作成
        LinearLayout layout = new LinearLayout(getActivity());
        //上から下にパーツを組み込む設定
        layout.setOrientation(LinearLayout.VERTICAL);

        //外枠にパーツを組み込む
        layout.addView(setViewMargin(make_TextView("社外者名")));
        layout.addView(setViewMargin(editText1));
        layout.addView(setViewMargin(make_TextView("よみがな")));
        layout.addView(setViewMargin(editText2));
        layout.addView(setViewMargin(make_TextView("役職")));
        layout.addView(setViewMargin(editText3));

        builder.setPositiveButton("確定", new DialogButtonClickListener());
        builder.setNegativeButton("キャンセル", new DialogButtonClickListener());

        AlertDialog dialog = builder.create();

        //タイトルの設定
        dialog.setTitle("社外者登録ダイアログ");
        //レイアウトをダイアログに設定
        dialog.setView(layout);


        //エディットボックスの入力タイプの設定
        editText1.setInputType( InputType.TYPE_TEXT_VARIATION_PERSON_NAME );
        editText2.setInputType( InputType.TYPE_CLASS_TEXT);
        editText3.setInputType( InputType.TYPE_CLASS_TEXT);

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
    // テキストビューの作成
    private TextView make_TextView(String sMessage){
        //テキストビューの生成
        TextView tv = new TextView(getActivity());
        //メッセージの設定
        tv.setText(sMessage);
        //Viewの大きさを設定
        LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = (int)convertDpPixel(10, tv.getContext());
        layout_params.setMargins( margin, 0, margin, 0 );    // marginを設ける
        tv.setLayoutParams( layout_params );    // レイアウトを登録
        return tv;
    }
    // スピナーの作成
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
