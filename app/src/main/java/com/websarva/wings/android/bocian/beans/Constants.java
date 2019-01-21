package com.websarva.wings.android.bocian.beans;

// 名前付き定数クラス、ユーザに見せない内部的な定数はここで定義する
// また、レイアウトに使用しない数字などの国際的に通用する文字もここで定義する
public final class Constants {

    // データベース
    public final class DB {
        // データベース名
        public static final String DBName = "Bocian.db";
        // データベースバージョン
        public static final int VERSION = 1;
        // データベーステーブル名
        public static final String tableAdministrator = "Administrator";
        public static final String tableCompany = "Company";
        public static final String tableConferenceEquipment = "ConferenceEquipment";
        public static final String tableConferenceRoom = "ConferenceRoom";
        public static final String tableDepartment = "Department";
        public static final String tableEmployee = "Employee";
        public static final String tableEquipment = "Equipment";
        public static final String tableExternalParticipant = "ExternalParticipant";
        public static final String tableExternalPersons = "ExternalPersons";
        public static final String tableInParticipant = "InParticipant";
        public static final String tablePosition = "Position";
        public static final String tablePurpose = "Purpose";
        public static final String tableReserve = "Reserve";
        public static final String tableRegularMeeting = "RegularMeeting";
        public static final String tableTemporaryReserve = "TemporaryReserve";
        public static final String tableTerminal = "Terminal";
    }
    // 数字
    public final class Num {
        public static final int _ONE = -1;
        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int THREE = 3;
        public static final int FOUR = 4;
        public static final int FIVE = 5;
        public static final int SIX = 6;
        public static final int SEVEN = 7;
        public static final int EIGHT = 8;
        public static final int NINE = 9;
        public static final int TEN = 10;
        public static final int ELEVEN = 11;
        public static final int TWELVE = 12;
        public static final int THIRTEEN = 13;
        public static final int FOURTEEN = 14;
        public static final int FIFTEEN = 15;
        public static final int SIXTEEN = 16;
        public static final int SEVENTEEN = 17;
        public static final int EIGHTEEN = 18;
        public static final int NINETEEN = 19;
        public static final int TWENTY = 20;

    }
    private Constants (){}

}
