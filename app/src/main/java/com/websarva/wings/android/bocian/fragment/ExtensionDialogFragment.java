package com.websarva.wings.android.bocian.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Spinner;

import com.websarva.wings.android.bocian.R;

public class ExtensionDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity() , R.style.AlertDialogStyle);
        //延長画面からダイアログに時、分をStringで取得(メッセージで使用)
        Spinner hour = getActivity().findViewById(R.id.extension_spinar_hour);
        String hourSelectedItem = hour.getSelectedItem().toString();
        Spinner minutes = getActivity().findViewById(R.id.extension_spinar_minutes);
        String minutesSelectedItem = minutes.getSelectedItem().toString();

        //ダイアログのタイトルをセット
        builder.setTitle("延長確認");
        //ダイアログのメッセージをセット
        builder.setMessage(hourSelectedItem + "時" + minutesSelectedItem + "分" + "まで延長します。よろしいですか？");


        builder.setPositiveButton(R.string.bt_confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton(R.string.bt_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
