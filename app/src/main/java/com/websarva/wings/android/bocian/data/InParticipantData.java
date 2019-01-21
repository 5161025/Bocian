package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 社内参加者テーブル
public class InParticipantData {
    private int InParticipantId; // 社内参加者ID（主キー）
    private int reserveId; // 予約ID（外部キー）
    private int employeeId; // 社員ID（外部キー）
    private int flg; // 参加フラグ

    public InParticipantData(int InParticipantId, int reserveId, int employeeId, int flg) {
        this.InParticipantId = InParticipantId;
        this.reserveId = reserveId;
        this.employeeId = employeeId;
        this.flg = flg;
    }

    public InParticipantData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.InParticipantId = c.getInt(offset);
        this.reserveId = c.getInt(offset + 1);
        this.employeeId = c.getInt(offset + 2);
        this.flg = c.getInt(offset + 3);
    }

    public int getInParticipantId() { return InParticipantId; }

    public void setInParticipantId(int inParticipantId) { InParticipantId = inParticipantId; }

    public int getReserveId() { return reserveId; }

    public void setReserveId(int reserveId) { this.reserveId = reserveId; }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public int getFlg() { return flg; }

    public void setFlg(int flg) { this.flg = flg; }
}
