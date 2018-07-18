package com.example.rul0070.ass2_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LevelDataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "levelDataBase"; // name the database

    private static final String TABLE_ORIGINAL_LEVELS = "originalLevels"; // Name the tables
    private static final String TABLE_SAVED_LEVELS = "savedLevels";

    private static final String KEY_ID = "id"; //orignialLevels Table Column Names

    private static final String KEY_SAVED_ID = "savedId"; //SavedLevels Table Column Names

    //COMMON Table Columns Names

    private static final String KEY_LEVEL_CONTENT = "levelContent";
    private static final String KEY_MOVES = "moves";
//    private static final String KEY_MOVE_HISTORY = "moveHistory";
//    private static final String KEY_BOX_MOVED = "boxMoved";

    //string to create Tables
    private static final String CREATE_ORIGINAL_LEVELS_TABLE = "CREATE TABLE " + TABLE_ORIGINAL_LEVELS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LEVEL_CONTENT + " TEXT," + KEY_MOVES + " TEXT"  + ")";
    private static final String CREATE_SAVE_TABLE = "CREATE TABLE " + TABLE_SAVED_LEVELS + "(" + KEY_SAVED_ID + " TEXT PRIMARY KEY," + KEY_LEVEL_CONTENT + " TEXT," + KEY_MOVES +  "TEXT" + ")";


    public LevelDataBase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create tables
    @Override
    public void onCreate(SQLiteDatabase db){
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEVELS);
        db.execSQL(CREATE_ORIGINAL_LEVELS_TABLE);
        db.execSQL(CREATE_SAVE_TABLE);
    }

    //upgrade db
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORIGINAL_LEVELS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_LEVELS);

        // Create tables again
        onCreate(db);
    }

    public void addOriginalLevel(Level level) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, level.getLevelId());
        values.put(KEY_LEVEL_CONTENT, level.getLevelContent()); // LevelContent
        values.put(KEY_MOVES, level.getMove()); // playerMoves
//        values.put(KEY_MOVE_HISTORY, level.get_moveHistory());
//        values.put(KEY_BOX_MOVED, level.get_boxMoved());

        // Inserting Row
        db.insert(TABLE_ORIGINAL_LEVELS, null, values);
        db.close(); // Closing database connection
    }

    public void addSavedLevel(Level level) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SAVED_ID, level.getLevelId());
        values.put(KEY_LEVEL_CONTENT, level.getLevelContent()); // LevelContent
        values.put(KEY_MOVES, level.getMove()); // playerMoves


        // Inserting Row
        db.insert(TABLE_SAVED_LEVELS, null, values);
        db.close(); // Closing database connection

    }

    public Level getOriginalLevel(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ORIGINAL_LEVELS, new String[] {KEY_ID, KEY_LEVEL_CONTENT, KEY_MOVES}, KEY_ID + "=?", new String[] { String.valueOf(id)}, null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        Level level = new Level(Integer.parseInt(cursor.getString(0)),cursor.getString(1) );
        return level;
    }

    public Level getSavedLevel(String savedName){
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT * FROM " + TABLE_LEVELS + " WHERE " + KEY_ID + " = " + idInt;

        Cursor cursor = db.query(TABLE_SAVED_LEVELS, new String[] {KEY_SAVED_ID, KEY_LEVEL_CONTENT, KEY_MOVES}, KEY_SAVED_ID + "=?", new String[] { savedName}, null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();

        Level level = new Level(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)));
        return level;
    }



}
