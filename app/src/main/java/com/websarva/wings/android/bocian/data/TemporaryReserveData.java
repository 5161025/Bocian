package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 仮予約テーブル
public class TemporaryReserveData {
    private int priorld; // 追い出し先予約ID
    private int tmpId; // 仮予約ID
    private String reserveTime; // 予約時刻
    private int flg; // 承認フラグ


    public TemporaryReserveData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.priorld = c.getInt(offset);
        this.tmpId = c.getInt(offset + 1);
        this.reserveTime = c.getString(offset + 2);
        this.flg = c.getInt(offset + 3);
    }

    public int getPriorld() { return priorld; }

    public void setPriorld(int priorld) { this.priorld = priorld; }

    public int getTmpId() { return tmpId; }

    public void setTmpId(int tmpId) { this.tmpId = tmpId; }

    public String getReserveTime() { return reserveTime; }

    public void setReserveTime(String reserveTime) { this.reserveTime = reserveTime; }

    public int getFlg() { return flg; }

    public void setFlg(int flg) { this.flg = flg; }
}
