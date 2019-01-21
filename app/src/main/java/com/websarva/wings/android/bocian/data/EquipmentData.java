package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 備品マスタテーブル
public class EquipmentData {
    private int eqId; // 備品ID（主キー）
    private String eqName; // 備品名

    public EquipmentData(int eqId, String eqName) {
        this.eqId = eqId;
        this.eqName = eqName;
    }

    public EquipmentData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.eqId = c.getInt(offset);
        this.eqName = c.getString(offset + 1);
    }

    public int getEqId() { return eqId; }

    public void setEqId(int eqId) { this.eqId = eqId; }

    public String getEqName() { return eqName; }

    public void setEqName(String eqName) { this.eqName = eqName; }
}
