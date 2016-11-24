package com.cocoa.piccolo.piccolo;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.cocoa.piccolo.piccolo.DatabaseHelper
 * @author: shenjun@kuaiqiangche.com
 * @date: 16/11/23 10:25
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "event";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MSG = "jsonMsg";


    public static final String CREATE_DB = "create table " + TABLE_NAME + " ("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_MSG + " text)";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public int getDBCount(DatabaseHelper databaseHelper) {

        SQLiteDatabase readableDB = databaseHelper.getReadableDatabase();
        Cursor result = readableDB.rawQuery("select count(*) from " + TABLE_NAME, null);
        result.moveToFirst();
        return result.getInt(0);

    }

//
//    public void insertEvent(DatabaseHelper databaseHelper, Event event) {
//
//        SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("jsonMsg", event.getJsonMsg());
//        long result = writableDatabase.insert(TABLE_NAME, null, contentValues);
//
//        Log.e("insertEvent", "insertEvent" + result);
//
//    }
//
//
//    public List<Event> getTopEventList(DatabaseHelper databaseHelper, int count) {
//
//        SQLiteDatabase readableDB = databaseHelper.getReadableDatabase();
//
//        Cursor cursor = readableDB.rawQuery("select * from " + TABLE_NAME + " where 1=1 limit " + count, null);
//
//        List<Event> list = new ArrayList<>();
//
//
//        while (cursor.moveToNext()) {
//            Event event = new Event();
//            int idColumnIndex = cursor.getColumnIndex(COLUMN_ID);
//            int id = cursor.getInt(idColumnIndex);
//
//            int msgColumnIndex = cursor.getColumnIndex(COLUMN_MSG);
//            String jsonMsg = cursor.getString(msgColumnIndex);
//
//            event.setJsonMsg(jsonMsg);
//            event.setId(id);
//            list.add(event);
//        }
//        return list;
//    }
//
//
//    public int deleteEvent(DatabaseHelper databaseHelper, String id) {
//
//        SQLiteDatabase writableDatabase = databaseHelper.getWritableDatabase();
//
//        return writableDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{id});
//    }

}
//        DatabaseHelper databaseHelper = new DatabaseHelper(this, "event.db", null, 1);
//
//        List<Event> list = new ArrayList<>();
//        list.addAll(databaseHelper.getTopEventList(databaseHelper, 1000));
//
//        long c1 = System.currentTimeMillis();
//        for (Event e : list) {
//            int result = databaseHelper.deleteEvent(databaseHelper, e.getId() + "");
//            Log.e("----", e.toString() + "    result =" + result);
//
//        }
//        Log.e("----", System.currentTimeMillis() - c1 + " delete  lost time");

//
//        long c = System.currentTimeMillis();
//        for (int i = 0; i < 100; i++) {
//            Event event = new Event();
//            event.setJsonMsg("ahahhads");
//            databaseHelper.insertEvent(databaseHelper, event);
//
//        }
//        Log.e("----", System.currentTimeMillis() - c + " add and get  lost time");
//
//
//        list.clear();
//        list.addAll(databaseHelper.getTopEventList(databaseHelper, 10));
//
//        for (Event e : list) {
//
//            Log.e("----", e.toString());
//
//        }


//        Log.e("----", "----"+databaseHelper.getDBCount(databaseHelper));
