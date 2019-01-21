package com.websarva.wings.android.bocian.data;

import android.database.Cursor;

// 社員マスタテーブル
public class EmployeeData {
    private int empId; // 社員ID（主キー）
    private int depId; // 部署ID（外部キー）
    private int posId; // 役職ID（外部キー）
    private String empName; // 社員名
    private String empKana; // 読み仮名

    public EmployeeData(int empId, int depId, int posId, String empName, String empKana) {
        this.empId = empId;
        this.depId = depId;
        this.posId = posId;
        this.empName = empName;
        this.empKana = empKana;
    }

    public EmployeeData(Cursor c, int offset) {
        /* open済みのCursorからインスタンスを生成するコンストラクタ。Cursorのcloseの責務は持たない */
        this.empId = c.getInt(offset);
        this.depId = c.getInt(offset + 1);
        this.posId = c.getInt(offset + 2);
        this.empName = c.getString(offset + 3);
        this.empKana = c.getString(offset + 4);
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getPosId() {
        return posId;
    }

    public void setPosId(int posId) {
        this.posId = posId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpKana() {
        return empKana;
    }

    public void setEmpKana(String empKana) {
        this.empKana = empKana;
    }
}
