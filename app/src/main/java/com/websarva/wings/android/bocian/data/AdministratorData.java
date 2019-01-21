package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 管理者テーブル
public class AdministratorData {
    private int adminId; // 管理者ID（主キー）
    private String password; // パスワード

    public AdministratorData(int adminId, String password) {
        this.adminId = adminId;
        this.password = password;
    }

    public AdministratorData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.adminId = c.getInt(offset);
        this.password = c.getString(offset + 1);
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
