package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 社外参加者テーブル
public class ExternalParticipantData {
    private int exParticipantId; // 社外参加者ID（主キー）
    private int reserveId; // 予約ID（外部キー）
    private int exPersonsId; // 社外者ID（外部キー）

    public ExternalParticipantData(int exParticipantId, int reserveId, int exPersonsId) {
        this.exParticipantId = exParticipantId;
        this.reserveId = reserveId;
        this.exPersonsId = exPersonsId;
    }
    public ExternalParticipantData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.exParticipantId = c.getInt(offset);
        this.reserveId = c.getInt(offset + 1);
        this.exPersonsId = c.getInt(offset + 2);
    }

    public int getExParticipantId() { return exParticipantId; }

    public void setExParticipantId(int exParticipantId) { this.exParticipantId = exParticipantId; }

    public int getReserveId() { return reserveId; }

    public void setReserveId(int reserveId) { this.reserveId = reserveId; }

    public int getExPersonsId() { return exPersonsId; }

    public void setExPersonsId(int exPersonsId) { this.exPersonsId = exPersonsId; }
}
