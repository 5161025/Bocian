package com.websarva.wings.android.bocian.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.websarva.wings.android.bocian.R;
import com.websarva.wings.android.bocian.activity.EditCompanyActivity;
import com.websarva.wings.android.bocian.adapter.SelectedCompanyListAdapter;
import com.websarva.wings.android.bocian.listItem.SelectedCompanyListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_selected_company, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Activity activity = getActivity();
        super.onCreate(savedInstanceState);
        // ここは繰り返しに利用しているだけ
        String names[]      = getResources().getStringArray(R.array.companyList);

        List<SelectedCompanyListItem> data = new ArrayList<>(); // アダプタのdata部分のリストを作成
        // インスタンス生成してセットしている
        for (int i = 0; i < names.length; i++) {
            SelectedCompanyListItem item = new SelectedCompanyListItem();
            item.setId((new Random()).nextLong());  // 別に乱数にしなくてもよい
            item.setName(names[i]);
            data.add(item); // インスタンスをリストに挿入
        }
        // 自身のアクティビティ、データ、レイアウトを指定
        SelectedCompanyListAdapter adapter = new SelectedCompanyListAdapter(activity, data, R.layout.selected_company_list_item);
        ListView listView = view.findViewById(R.id.selectedCompany_list_vi_company); // レイアウト
        listView.setAdapter(adapter);

        // onViewCreatedではアクセスタイミングが早すぎるため、こちらで行う

        // 企業登録画面を起動
        view.findViewById(R.id.selectedCompany_img_bt_plus).setOnClickListener(v -> {
            Intent intent = new Intent(activity, EditCompanyActivity.class);
            startActivity(intent);
        });

        // 企業編集画面を起動（リスト）

        // 削除処理を起動（リスト）

        // この画面の終了（戻る）
        view.findViewById(R.id.selectedCompany_bt_back).setOnClickListener( v -> { activity.finish(); });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
