package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 役職テーブル
public class PositionData {
    private int posId; // 役職ID
    private String posName; // 役職名
    private int posScore; // 優先度

    public PositionData(int posId, String posName, int posScore) {
        this.posId = posId;
        this.posName = posName;
        this.posScore = posScore;
    }

    public PositionData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.posId = c.getInt(offset);
        this.posName = c.getString(offset + 1);
        this.posScore = c.getInt(offset + 2);
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public int getPosScore() {
        return posScore;
    }

    public void setPosScore(int posScore) {
        this.posScore = posScore;
    }
}
