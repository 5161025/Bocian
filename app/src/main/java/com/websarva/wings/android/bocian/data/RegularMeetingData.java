package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 定期会議テーブル
public class RegularMeetingData {
    private int regId; // 定期会議ID
    private String regDay; // 曜日

    public RegularMeetingData(int regId, String regDay) {
        this.regId = regId;
        this.regDay = regDay;
    }

    public RegularMeetingData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.regId = c.getInt(offset);
        this.regDay = c.getString(offset + 1);
    }

    public int getRegId() { return regId; }

    public void setRegId(int regId) { this.regId = regId; }

    public String getRegDay() { return regDay; }

    public void setRegDay(String regDay) { this.regDay = regDay; }
}
