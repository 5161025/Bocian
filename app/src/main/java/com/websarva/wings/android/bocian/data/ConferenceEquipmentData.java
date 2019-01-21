package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 会議備品テーブル
public class ConferenceEquipmentData {
    private int confEqId; // 会議備品ID（主キー）
    private int reserveId; // 予約ID（外部キー）
    private int eqId; // 備品ID（外部キー）
    private int quantity; // 数量

    public ConferenceEquipmentData(int confEqId, int reserveId, int eqId, int quantity) {
        this.confEqId = confEqId;
        this.reserveId = reserveId;
        this.eqId = eqId;
        this.quantity = quantity;
    }

    public ConferenceEquipmentData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.confEqId = c.getInt(offset);
        this.reserveId = c.getInt(offset + 1);
        this.eqId = c.getInt(offset + 2);
        this.quantity = c.getInt(offset + 3);
    }

    public int getConfEqId() {
        return confEqId;
    }

    public void setConfEqId(int confEqId) {
        this.confEqId = confEqId;
    }

    public int getReserveId() {
        return reserveId;
    }

    public void setReserveId(int reserveId) {
        this.reserveId = reserveId;
    }

    public int getEqId() {
        return eqId;
    }

    public void setEqId(int eqId) {
        this.eqId = eqId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
