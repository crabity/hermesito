package org.rika.hermesito;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rika on 2/16/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Hermesito";
    private static final int DATABASE_VERSION = 1;

    public DBHandler(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}