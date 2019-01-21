package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 目的テーブル
public class PurposeData {
    private int pupId; // 目的ID （主キー）
    private String pupName; // 目的名
    private int pupScore; // 目的点数

    public PurposeData(int pupId, String pupName, int pupScore) {
        this.pupId = pupId;
        this.pupName = pupName;
        this.pupScore = pupScore;
    }

    public PurposeData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.pupId = c.getInt(offset);
        this.pupName = c.getString(offset + 1);
        this.pupScore = c.getInt(offset + 2);
    }

    public int getPupId() { return pupId; }

    public void setPupId(int pupId) { this.pupId = pupId; }

    public String getPupName() { return pupName; }

    public void setPupName(String pupName) { this.pupName = pupName; }

    public int getPupScore() { return pupScore; }

    public void setPupScore(int pupScore) { this.pupScore = pupScore; }
}
