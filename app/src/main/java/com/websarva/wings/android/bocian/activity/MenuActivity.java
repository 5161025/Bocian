package com.websarva.wings.android.bocian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.websarva.wings.android.bocian.*;
import com.websarva.wings.android.bocian.beans.EndDrawerToggle;
import com.websarva.wings.android.bocian.fragment.AdminLoginFragment;
import com.websarva.wings.android.bocian.fragment.FirstFragment;
import com.websarva.wings.android.bocian.fragment.SecondFragment;

// ハンバーガーメニューテスト画面（テスト）
public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private EndDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new EndDrawerToggle(this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(drawerToggle);     // ハンバーガーボタンをクリックした動作はEndDrawerToggle

        // 最初にアクティビティがロードされたときの画面(スケジュール画面)
        displaySelectedScreen(R.id.nav_reservation);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }


    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    /**
     * 選択したメニューの画面を呼び出す
     * @param itemId
     */
    private void displaySelectedScreen(int itemId) {
        // フラグメントオブジェクトを作成する
        Fragment fragment = null;

        // とりあえず2画面しかないからあとで変更
        switch (itemId) {
            case R.id.nav_reservation:              // 新規予約画面
                fragment = new FirstFragment();
                break;
            case R.id.nav_confirm:                  // 予約確認画面
                fragment = new SecondFragment();
                break;
            case R.id.nav_schdule:                  // スケジュール画面
                fragment = new FirstFragment();
                break;
            case R.id.nav_company:                  // 会社情報編集画面
                fragment = new SecondFragment();
                break;
            case R.id.nav_history:                  // 利用履歴画面
                fragment = new FirstFragment();
                break;
            case R.id.nav_login:                    // ログイン画面   ログアウト時との違い
                fragment = new AdminLoginFragment();
                break;
        }

        // フラグメントを置き換える
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
            getIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        // メニューを閉じる
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.END);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // displayselectedscreenメソッドを呼び出し、選択されたメニューのIDを渡す
        displaySelectedScreen(item.getItemId());
        return true;
    }


//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_reservation) {
//            // 新規予約
//
//        } else if (id == R.id.nav_confirm) {
//            // 予約確認
//
//        } else if (id == R.id.nav_schdule) {
//            // スケジュール
//
//        } else if (id == R.id.nav_company) {
//            // 会社情報編集
//
//        } else if (id == R.id.nav_history) {
//            // 利用履歴
//
//        } else if (id == R.id.nav_login) {
//            // 管理者ログイン
//
//        }
//
//        //drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.END);
//        return true;
//    }

}

