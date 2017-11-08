package com.example.cowboy.sqlitemodule;

/**
 * Created by Cowboy on 30.10.2017.
 */

public class Config {

    public static final String DB_NAME = "sqlite_base";
    public static final String DB_TABLE = "first_table";
    public static final int DB_VERSION = 1;

    public static final String COMMAND_CREATE = "create table IF NOT EXISTS "
            + DB_TABLE + " ("
            + PersonContract._ID + " INTEGER PRIMARY KEY, "
            + PersonContract.KEY_NAME + " TEXT, "
            + PersonContract.KEY_SURNAME + " TEXT, "
            + PersonContract.KEY_PHONE + " TEXT, "
            + PersonContract.KEY_MAIL + " TEXT, "
            + PersonContract.KEY_SKYPE + " TEXT "
            + ");";

    public static final String COMMAND_DELETE = "DROP TABLE IF EXISTS " + DB_TABLE;
}
