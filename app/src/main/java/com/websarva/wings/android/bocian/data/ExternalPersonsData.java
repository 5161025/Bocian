package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 社外者マスタテーブル
public class ExternalPersonsData {
    private int exPersonsId; // 社外者ID（主キー）
    private int companyId; // 会社ID （外部キー）
    private String exPersonsName; // 社外者名
    private String exPersonsKana; // 読み仮名
    private String exPersonsPosition; // 役職

    public ExternalPersonsData(int exPersonsId, int companyId, String exPersonsName, String exPersonsKana, String exPersonsPosition) {
        this.exPersonsId = exPersonsId;
        this.companyId = companyId;
        this.exPersonsName = exPersonsName;
        this.exPersonsKana = exPersonsKana;
        this.exPersonsPosition = exPersonsPosition;
    }

    public ExternalPersonsData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.exPersonsId = c.getInt(offset);
        this.companyId = c.getInt(offset + 1);
        this.exPersonsName = c.getString(offset + 2);
        this.exPersonsKana = c.getString(offset + 3);
        this.exPersonsPosition = c.getString(offset + 4);
    }

    public int getExPersonsId() {
        return exPersonsId;
    }

    public void setExPersonsId(int exPersonsId) {
        this.exPersonsId = exPersonsId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getExPersonsName() {
        return exPersonsName;
    }

    public void setExPersonsName(String exPersonsName) {
        this.exPersonsName = exPersonsName;
    }

    public String getExPersonsKana() {
        return exPersonsKana;
    }

    public void setExPersonsKana(String exPersonsKana) {
        this.exPersonsKana = exPersonsKana;
    }

    public String getExPersonsPosition() {
        return exPersonsPosition;
    }

    public void setExPersonsPosition(String exPersonsPosition) {
        this.exPersonsPosition = exPersonsPosition;
    }
}
