package com.elifnurtelase.stickerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class StickersDB extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "stickersDB";
    private static String TABLE_NAME = "favoriteTable";
    public static String KEY_ID = "id";
    public static String ITEM_TITLE = "itemFileName";
    public static String ITEM_STICKER_PACK = "itemStickerPack";
    public static String FAVORITE_STATUS = "fStatus";
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + " INTEGER PRIMARY KEY," + ITEM_TITLE+ " TEXT,"
            + ITEM_STICKER_PACK + " TEXT," + FAVORITE_STATUS+" TEXT)";
    private static int id = 1;
    public StickersDB(Context context) { super(context,DATABASE_NAME,null,DB_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // insert data into database
    public void insertIntoTheDatabase(String item_title, String item_sticker_pack, String fav_status) {
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ITEM_TITLE, item_title);
        cv.put(ITEM_STICKER_PACK, item_sticker_pack);
        cv.put(FAVORITE_STATUS, fav_status);
        db.insert(TABLE_NAME,null, cv);
        Log.d("FavDB Status", item_title + ", fStatus - "+fav_status+" - . " + cv);
    }
    // read pack data by id
    public Cursor read_all_data_by_sticker_pack(String item_sticker_pack) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME +" WHERE "+ ITEM_STICKER_PACK +"=" + item_sticker_pack+"";
        return db.rawQuery(sql,null,null);
    }

    // read data by id
    public Cursor read_item_data_by_id(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID+"="+id+"";
        return db.rawQuery(sql,null,null);
    }
    // remove line from database
    public void remove_fav(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ FAVORITE_STATUS+" ='0' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("remove", id.toString());

    }

    public void add_fav(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE " + TABLE_NAME + " SET  "+ FAVORITE_STATUS+" ='1' WHERE "+KEY_ID+"="+id+"";
        db.execSQL(sql);
        Log.d("add", id.toString());

    }

    // select all favorite list

    public Cursor select_all_favorite_list() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME+" WHERE "+FAVORITE_STATUS+" ='1'";
        return db.rawQuery(sql,null,null);
    }
}
