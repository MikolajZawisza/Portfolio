package com.example.mikol.portfolio;

public class ProjectTable
{
    static final int DB_VERSION = 1;
    static final String DB_NAME = "database.db";
    static final String TABLE_NAME = "PROJECT";

    static final String ID = "id";
    static final String PROJECT_NAME = "name";
    static final String PROJECT_DESCRIPTION = "description";
    static final String PROJECT_PATHTOCODE ="pathToCode";
    static final String PROJECT_PATHTOPHOTO ="pathToPhoto";

    static final int ID_COLUMN = 0;
    static final int NAME_COLUMN = 1;
    static final int DESCRIPTION_COLUMN = 2;
    static final int PATHTOCODE_COLUMN = 3;
    static final int PATHTOPHOTO_COLUMN = 4;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PROJECT_NAME + " varchar(100), "
            + PROJECT_DESCRIPTION  + " varchar(100), "
            + PROJECT_PATHTOCODE + " varchar(200), "
            + PROJECT_PATHTOPHOTO + " varchar(200)"
            + ");";

}
