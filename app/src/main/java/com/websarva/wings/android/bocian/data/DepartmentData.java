package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 部署テーブル
public class DepartmentData {
    private int depId; // 部署ID（主キー）
    private String depName; // 部署名
    private String secName; // 課名


    public DepartmentData(int depId, String depName,String secName) {
        this.depId = depId;
        this.depName = depName;
        this.secName = secName;
    }

    public DepartmentData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.depId = c.getInt(offset);
        this.depName = c.getString(offset + 1);
        this.secName = c.getString(offset + 2);
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getSecName() { return secName; }

    public void setSecName(String secName) { this.secName = secName; }
}
