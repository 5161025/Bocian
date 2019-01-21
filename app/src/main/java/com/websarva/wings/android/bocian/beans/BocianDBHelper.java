package com.websarva.wings.android.bocian.beans;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.websarva.wings.android.bocian.data.AdministratorData;
import com.websarva.wings.android.bocian.data.CompanyData;
import com.websarva.wings.android.bocian.data.ConferenceEquipmentData;
import com.websarva.wings.android.bocian.data.ConferenceRoomData;
import com.websarva.wings.android.bocian.data.DepartmentData;
import com.websarva.wings.android.bocian.data.EmployeeData;
import com.websarva.wings.android.bocian.data.EquipmentData;
import com.websarva.wings.android.bocian.data.ExternalParticipantData;
import com.websarva.wings.android.bocian.data.ExternalPersonsData;
import com.websarva.wings.android.bocian.data.InParticipantData;
import com.websarva.wings.android.bocian.data.PositionData;
import com.websarva.wings.android.bocian.data.PurposeData;
import com.websarva.wings.android.bocian.data.ReserveData;
import com.websarva.wings.android.bocian.data.RegularMeetingData;
import com.websarva.wings.android.bocian.data.TemporaryReserveData;
import com.websarva.wings.android.bocian.data.TerminalData;


import java.util.ArrayList;
import java.util.List;

import static com.websarva.wings.android.bocian.beans.Constants.Num.*;

public class BocianDBHelper extends SQLiteOpenHelper {


    public BocianDBHelper(Context context) {
        super(context, Constants.DB.DBName, null, Constants.DB.VERSION);
    }

    // データベースが作成された時に実行される処理
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE cocktailmemo(");
        stringBuilder.append("_id INTEGER PRIMARY KEY,");
        stringBuilder.append("name TEXT,");
        stringBuilder.append("note TEXT");
        stringBuilder.append(");");
        String sql = stringBuilder.toString();

        db.execSQL(sql);
    }

    // データベースをバージョンアップした時に実行される処理
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // データベースが開かれた時に実行される処理
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    // 社員データを取得するメソッド
    // selection = 検索条件

    /**
     * @param db データベース
     * @param table テーブル名
     * @param selection 条件
     * @param orderBy 順
     * @return list<T> 指定したテーブルに対応するクラスのリスト
     */
    public static <T> List<T> getDataList(SQLiteDatabase db, String table, String selection, String[] args, String orderBy) {
        List<T> list = new ArrayList<>();

        Cursor cursor = db.query(table, null, selection, args, null, null, orderBy);

        // cursorオブジェクトにtry with resourcesを使うとメモリリークが発生するとか
        try {
            switch (table){
                case Constants.DB.tableAdministrator:
                    while (cursor.moveToNext()) list.add((T) (new AdministratorData(cursor, ZERO)));
                    break;
                case Constants.DB.tableCompany:
                    while (cursor.moveToNext()) list.add((T) (new CompanyData(cursor, ZERO)));
                    break;
                case Constants.DB.tableConferenceEquipment:
                    while (cursor.moveToNext()) list.add((T) (new ConferenceEquipmentData(cursor, ZERO)));
                    break;
                case Constants.DB.tableConferenceRoom:
                    while (cursor.moveToNext()) list.add((T) (new ConferenceRoomData(cursor, ZERO)));
                    break;
                case Constants.DB.tableDepartment:
                    while (cursor.moveToNext()) list.add((T) (new DepartmentData(cursor, ZERO)));
                    break;
                case Constants.DB.tableEmployee:
                    while (cursor.moveToNext()) list.add((T) (new EmployeeData(cursor, ZERO)));
                    break;
                case Constants.DB.tableEquipment:
                    while (cursor.moveToNext()) list.add((T) (new EquipmentData(cursor, ZERO)));
                    break;
                case Constants.DB.tableExternalPersons:
                    while (cursor.moveToNext()) list.add((T) (new ExternalPersonsData(cursor, ZERO)));
                    break;
                case Constants.DB.tableExternalParticipant:
                    while (cursor.moveToNext()) list.add((T) (new ExternalParticipantData(cursor, ZERO)));
                    break;
                case Constants.DB.tableInParticipant:
                    while (cursor.moveToNext()) list.add((T) (new InParticipantData(cursor, ZERO)));
                    break;
                case Constants.DB.tablePosition:
                    while (cursor.moveToNext()) list.add((T) (new PositionData(cursor, ZERO)));
                    break;
                case Constants.DB.tablePurpose:
                    while (cursor.moveToNext()) list.add((T) (new PurposeData(cursor, ZERO)));
                    break;
                case Constants.DB.tableReserve:
                    while (cursor.moveToNext()) list.add((T) (new ReserveData(cursor, ZERO)));
                    break;
                case Constants.DB.tableRegularMeeting:
                    while (cursor.moveToNext()) list.add((T) (new RegularMeetingData(cursor, ZERO)));
                    break;
                case Constants.DB.tableTemporaryReserve:
                    while (cursor.moveToNext()) list.add((T) (new TemporaryReserveData(cursor, ZERO)));
                    break;
                case Constants.DB.tableTerminal:
                    while (cursor.moveToNext()) list.add((T) (new TerminalData(cursor, ZERO)));
                    break;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } finally {
            try {
                cursor.close();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
    /**
     * @param db データベース
     * @param table テーブル名
     * @param data 更新するデータ
     */
    public static void setDataList(SQLiteDatabase db, String table, Object data){
        String sql;
        SQLiteStatement stmt;
        switch (table){
            case Constants.DB.tableAdministrator:
                // SQLの作成
                sql = "replace into Administrator(adminId, password) values(?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((AdministratorData) data).getAdminId());
                stmt.bindString(TWO, ((AdministratorData) data).getPassword());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableCompany:
                // SQLの作成
                sql = "replace into Company(compId, compName) values(?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((CompanyData) data).getCompId());
                stmt.bindString(TWO, ((CompanyData) data).getCompName());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableConferenceEquipment:
                // SQLの作成
                sql = "replace into ConferenceEquipment(confEqId, reserveId, eqId, quantity) values(?, ?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((ConferenceEquipmentData) data).getConfEqId());
                stmt.bindLong(TWO, ((ConferenceEquipmentData) data).getReserveId());
                stmt.bindLong(THREE, ((ConferenceEquipmentData) data).getEqId());
                stmt.bindLong(FOUR, ((ConferenceEquipmentData) data).getQuantity());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableConferenceRoom:
                // SQLの作成
                sql = "replace into ConferenceRoom(confEqId, reserveId, eqId, quantity) values(?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((ConferenceRoomData) data).getConfRoomId());
                stmt.bindString(TWO, ((ConferenceRoomData) data).getConfRoomName());
                stmt.bindLong(THREE, ((ConferenceRoomData) data).getCapacity());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableDepartment:
                // SQLの作成
                sql = "replace into Department(depId, depName, eqId, secName) values(?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((DepartmentData) data).getDepId());
                stmt.bindString(TWO, ((DepartmentData) data).getDepName());
                stmt.bindString(THREE, ((DepartmentData) data).getSecName());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableEmployee:
                // SQLの作成
                sql = "replace into Employee(empId, depId, posId, empName, empKana) values(?, ?, ?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((EmployeeData) data).getEmpId());
                stmt.bindLong(TWO, ((EmployeeData) data).getDepId());
                stmt.bindLong(THREE, ((EmployeeData) data).getPosId());
                stmt.bindString(FOUR, ((EmployeeData) data).getEmpName());
                stmt.bindString(FIVE, ((EmployeeData) data).getEmpKana());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableEquipment:
                // SQLの作成
                sql = "replace into Equipment(eqId, eqName) values(?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((EquipmentData) data).getEqId());
                stmt.bindString(TWO, ((EquipmentData) data).getEqName());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableExternalParticipant:
                // SQLの作成
                sql = "replace into ExternalParticipant(exParticipantId, reserveId, exPersonsId) values(?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((ExternalParticipantData) data).getExParticipantId());
                stmt.bindLong(TWO, ((ExternalParticipantData) data).getReserveId());
                stmt.bindLong(THREE, ((ExternalParticipantData) data).getExPersonsId());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableExternalPersons:
                // SQLの作成
                sql = "replace into ExternalPersons(exPersonsId, companyId, exPersonsName, exPersonsKana, exPersonsPosition) values(?, ?, ?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((ExternalPersonsData) data).getExPersonsId());
                stmt.bindLong(TWO, ((ExternalPersonsData) data).getCompanyId());
                stmt.bindString(THREE, ((ExternalPersonsData) data).getExPersonsName());
                stmt.bindString(FOUR, ((ExternalPersonsData) data).getExPersonsKana());
                stmt.bindString(FIVE, ((ExternalPersonsData) data).getExPersonsPosition());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableInParticipant:
                // SQLの作成
                sql = "replace into InParticipant(inParticipantId, reserveId, employeeId, flg) values(?, ?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((InParticipantData) data).getInParticipantId());
                stmt.bindLong(TWO, ((InParticipantData) data).getReserveId());
                stmt.bindLong(THREE, ((InParticipantData) data).getEmployeeId());
                stmt.bindLong(FOUR, ((InParticipantData) data).getFlg());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tablePosition:
                // SQLの作成
                sql = "replace into Position(posId, posName, posScore) values(?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((PositionData) data).getPosId());
                stmt.bindString(TWO, ((PositionData) data).getPosName());
                stmt.bindLong(THREE, ((PositionData) data).getPosScore());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tablePurpose:
                // SQLの作成
                sql = "replace into Purpose(pupId, pupName, pupScore) values(?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((PurposeData) data).getPupId());
                stmt.bindString(TWO, ((PurposeData) data).getPupName());
                stmt.bindLong(THREE, ((PurposeData) data).getPupScore());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableReserve:
                // SQLの作成
                sql = "replace into Reserve(resId, resTerminalNumber, pupId, confRoomId, regId, useDay, startTime, endTime, numExPersons, flg, remarks) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((ReserveData) data).getResId());
                stmt.bindLong(TWO, ((ReserveData) data).getResTerminalNumber());
                stmt.bindLong(THREE, ((ReserveData) data).getPupId());
                stmt.bindLong(FOUR, ((ReserveData) data).getConfRoomId());
                stmt.bindLong(FIVE, ((ReserveData) data).getRegId());
                stmt.bindString(SIX, ((ReserveData) data).getUseDay());
                stmt.bindString(SEVEN, ((ReserveData) data).getStartTime());
                stmt.bindString(EIGHT, ((ReserveData) data).getEndTime());
                stmt.bindLong(NINE, ((ReserveData) data).getNumExPersons());
                stmt.bindLong(TEN, ((ReserveData) data).getFlg());
                stmt.bindString(ELEVEN, ((ReserveData) data).getRemarks());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableRegularMeeting :
                // SQLの作成
                sql = "replace into RgularMeeting(regId, regDay) values(?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((RegularMeetingData) data).getRegId());
                stmt.bindString(TWO, ((RegularMeetingData) data).getRegDay());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableTemporaryReserve:
                // SQLの作成
                sql = "replace into TemporaryReserve(priorId, tmpId, reserveTime, flg) values(?, ?, ?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((TemporaryReserveData) data).getPriorld());
                stmt.bindLong(TWO, ((TemporaryReserveData) data).getTmpId());
                stmt.bindString(THREE, ((TemporaryReserveData) data).getReserveTime());
                stmt.bindLong(FOUR, ((TemporaryReserveData) data).getFlg());
                // SQLの実行
                stmt.execute();
                break;
            case Constants.DB.tableTerminal:
                // SQLの作成
                sql = "replace into Terminal(terminalNumber, empId) values(?, ?)";
                // SQL文字列をもとにプリペアドステートメントを取得
                stmt = db.compileStatement(sql);
                // 変数のバインド
                stmt.bindLong(ONE, ((TerminalData) data).getTerminalNumber());
                stmt.bindLong(TWO, ((TerminalData) data).getEmpId());
                // SQLの実行
                stmt.execute();
                break;
        }
    }
}
