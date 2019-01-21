package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 会議室テーブル
public class ConferenceRoomData {
    private int confRoomId; // 会議室ID（主キー）
    private String confRoomName; // 会議室名
    private int capacity; // 最大収容人数

    public ConferenceRoomData(int confRoomId, String confRoomName, int capacity) {
        this.confRoomId = confRoomId;
        this.confRoomName = confRoomName;
        this.capacity = capacity;
    }
    public ConferenceRoomData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.confRoomId = c.getInt(offset);
        this.confRoomName = c.getString(offset + 1);
        this.capacity = c.getInt(offset + 2);
    }

    public int getConfRoomId() {
        return confRoomId;
    }

    public void setConfRoomId(int confRoomId) {
        this.confRoomId = confRoomId;
    }

    public String getConfRoomName() {
        return confRoomName;
    }

    public void setConfRoomName(String confRoomName) {
        this.confRoomName = confRoomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
