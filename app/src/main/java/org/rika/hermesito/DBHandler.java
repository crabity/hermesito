package org.rika.hermesito;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Rika on 2/16/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Hermesito";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_ACCT = "accounts";
    private static final String TABLE_XACTION = "xactions";
    private static final String TABLE_DETAIL = "details";

    // Accounts table fields
    private static final String ACCT_ID = "acct_id";
    private static final String ACCT_NAME = "acct_name";
    private static final String ACCT_TYPE = "acct_type";
    private static final String ACCT_NUM = "num";
    private static final String ACCT_LIMIT = "limit";

    // Transaction table fields
    private static final String XACTION_ID = "xaction_id";
    private static final String XACTION_DATETIME = "datetime";
    private static final String XACTION_NAME = "xaction_name";
    private static final String XACTION_ACCT_ID = "xaction_acct_id";
    private static final String XACTION_AMOUNT = "amount";

    // Detail table fields
    private static final String DETAIL_ID = "detail_id";
    private static final String DETAIL_XACTION_ID = "detail_xaction_id";
    private static final String DETAIL_NAME = "detail_name";
    private static final String DETAIL_CATEGORY = "category";
    private static final String DETAIL_BASE = "base";
    private static final String DETAIL_TIP = "tip";
    private static final String DETAIL_TAX = "tax";

    private Context context;

    public DBHandler(Context c){
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
        context = c;
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //Static table
        db.execSQL("CREATE TABLE IF NOT EXISTS " +  TABLE_ACCT + "("
                + ACCT_ID + " INTEGER PRIMARY KEY,"
                + ACCT_NAME + "TEXT,"
                + ACCT_TYPE + "TEXT,"
                + ACCT_NUM + "INTEGER, "
                + ACCT_LIMIT + "REAL"
                + ")"
        );

        //Dynamic tables
        db.execSQL("CREATE TABLE IF NOT EXISTS " +  TABLE_XACTION + "("
                        + XACTION_ID + " INTEGER PRIMARY KEY,"
                        + XACTION_DATETIME + "INTEGER,"
                        + XACTION_NAME + "TEXT, "
                        + XACTION_AMOUNT + "REAL, "
                        + "FOREIGN KEY(" + XACTION_ACCT_ID + ") REFERENCES " + TABLE_ACCT + "(" + ACCT_ID + ")"
                        + ")"
        );

        db.execSQL("CREATE TABLE IF NOT EXISTS " +  TABLE_DETAIL + "("
                        + DETAIL_ID + " INTEGER PRIMARY KEY,"
                        + DETAIL_CATEGORY + "TEXT,"
                        + DETAIL_NAME + "TEXT, "
                        + DETAIL_BASE + "REAL, "
                        + DETAIL_TIP + "REAL, "
                        + DETAIL_TAX + "REAL, "
                        + "FOREIGN KEY(" + DETAIL_XACTION_ID + ") REFERENCES " + TABLE_XACTION + "(" + XACTION_ID + ")"
                        + ")"
        );

        fillAcctTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP_TABLE_IF_EXISTS " + TABLE_ACCT);
        onCreate(db);
    }

    private void fillAcctTable(SQLiteDatabase db) {
        String line;
        String csvDelimiter = ",";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.acct)));
            while ((line = br.readLine()) != null) {
                String[] input = line.split(csvDelimiter);
                ContentValues values = new ContentValues();
                values.put(ACCT_NAME, input[0]);
                values.put(ACCT_TYPE, input[1]);
                values.put(ACCT_NUM, input[2]);
                values.put(ACCT_LIMIT, input[3]);
                db.insert(TABLE_ACCT, null, values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}