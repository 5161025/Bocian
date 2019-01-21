package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 会社マスタテーブル
public class CompanyData {
    private int compId; // 会社ID（主キー）
    private String compName; // 会社名

    public CompanyData(int compId, String compName) {
        this.compId = compId;
        this.compName = compName;
    }

    public CompanyData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.compId = c.getInt(offset);
        this.compName = c.getString(offset + 1);
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }
}
