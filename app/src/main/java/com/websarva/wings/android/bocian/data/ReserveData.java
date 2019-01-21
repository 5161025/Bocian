package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 予約テーブル
public class ReserveData {
    private int resId; // 予約ID （主キー）
    private int resTerminalNumber; // 予約者端末番号（外部キー）
    private int pupId; // 目的ID（外部キー）
    private int confRoomId; // 会議室ID（外部キー）
    private int regId; // 定期会議ID（外部キー）
    private String useDay; // 利用日
    private String startTime; // 開始時刻
    private String endTime; // 終了時刻
    private int numExPersons; // 社外者人数
    private int flg; // 予約状態フラグ
    private String remarks; // 備考

    public ReserveData(int resId, int resTerminalNumber, int pupId, int confRoomId, int regId, String useDay, String startTime, String endTime, int numExPersons, int flg, String remarks) {
        this.resId = resId;
        this.resTerminalNumber = resTerminalNumber;
        this.pupId = pupId;
        this.confRoomId = confRoomId;
        this.regId = regId;
        this.useDay = useDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numExPersons = numExPersons;
        this.flg = flg;
        this.remarks = remarks;
    }

    public ReserveData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.resId = c.getInt(offset);
        this.resTerminalNumber = c.getInt(offset + 1);
        this.pupId = c.getInt(offset + 2);
        this.confRoomId = c.getInt(offset + 3);
        this.regId = c.getInt(offset + 4);
        this.useDay = c.getString(offset + 5);
        this.startTime = c.getString(offset + 6);
        this.endTime = c.getString(offset + 7);
        this.numExPersons = c.getInt(offset + 8);
        this.flg = c.getInt(offset + 9);
        this.remarks = c.getString(offset + 10);
    }

    public int getResId() { return resId; }

    public void setResId(int resId) { this.resId = resId; }

    public int getResTerminalNumber() { return resTerminalNumber; }

    public void setResTerminalNumber(int resTerminalNumber) { this.resTerminalNumber = resTerminalNumber; }

    public int getPupId() { return pupId; }

    public void setPupId(int pupId) { this.pupId = pupId; }

    public int getConfRoomId() { return confRoomId; }

    public void setConfRoomId(int confRoomId) { this.confRoomId = confRoomId; }

    public int getRegId() { return regId; }

    public void setRegId(int regId) { this.regId = regId; }

    public String getUseDay() { return useDay; }

    public void setUseDay(String useDay) { this.useDay = useDay; }

    public String getStartTime() { return startTime; }

    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }

    public void setEndTime(String endTime) { this.endTime = endTime; }

    public int getNumExPersons() { return numExPersons; }

    public void setNumExPersons(int numExPersons) { this.numExPersons = numExPersons; }

    public int getFlg() { return flg; }

    public void setFlg(int flg) { this.flg = flg; }

    public String getRemarks() { return remarks; }

    public void setRemarks(String remarks) { this.remarks = remarks; }
}
