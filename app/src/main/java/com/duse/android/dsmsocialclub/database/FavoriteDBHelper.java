package com.duse.android.dsmsocialclub.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mahesh Gaya on 9/24/16.
 */
public class FavoriteDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FavoritEevents.db";

    private static final String INTEGER_DATA_TYPE = "integer";


    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + FavoriteContract.FeedEntry.FAVORITE_TABLE_NAME +
            "(" + FavoriteContract.FeedEntry.FAVORITE_COLUMN_ID + " " +
            INTEGER_DATA_TYPE + " primary key, " +
            FavoriteContract.FeedEntry.FAVORITE_COLUMN_EVENT_ID + " " +
            INTEGER_DATA_TYPE + ", " +
            FavoriteContract.FeedEntry.FAVORITE_COLUMN_BOOLEAN + " " +
            INTEGER_DATA_TYPE +")";
    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + FavoriteContract.FeedEntry.FAVORITE_TABLE_NAME;


    public FavoriteDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DROP_TABLE);
        onCreate(sqLiteDatabase);
    }
}
