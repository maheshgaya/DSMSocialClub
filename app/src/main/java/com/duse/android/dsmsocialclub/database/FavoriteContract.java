package com.duse.android.dsmsocialclub.database;

import android.provider.BaseColumns;

/**
 * Created by Mahesh Gaya on 9/24/16.
 */
public class FavoriteContract {
    private FavoriteContract(){}
    public static class FeedEntry implements BaseColumns{
        //SQLite
        public static final String FAVORITE_TABLE_NAME = "favorites";
        public static final String FAVORITE_COLUMN_ID = "id";
        public static final String FAVORITE_COLUMN_EVENT_ID = "eventid";
        public static final String FAVORITE_COLUMN_BOOLEAN = "favorite";
    }
}
