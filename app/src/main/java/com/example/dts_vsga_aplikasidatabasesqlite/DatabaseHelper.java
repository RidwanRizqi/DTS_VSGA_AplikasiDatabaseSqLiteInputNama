package com.example.dts_vsga_aplikasidatabasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class DatabaseHelper extends SQLiteOpenHelper {

   private static final String TAG = DatabaseHelper.class.getName() ;
   public static String DATABASE = "student_database";
   private static final int DATABASE_VERSION = 1;
   private static final String TABLE_STUDENT = "students";
   private static final String KEY_ID = "id";
   public static final String KEY_FIRSTNAME = "name";
   public static final String CREATE_TABLE_STUDENTS =
           "CREATE TABLE " + TABLE_STUDENT + "("
                   + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + KEY_FIRSTNAME + " TEXT);";

   public DatabaseHelper(@Nullable Context context) {
      super(context, DATABASE, null, DATABASE_VERSION);
      Log.d(TAG, "DatabaseHelper: " + CREATE_TABLE_STUDENTS);
   }

   @Override
   public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL(CREATE_TABLE_STUDENTS);
      Log.d(TAG, "onCreate: SUCCESS");
   }

   @Override
   public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
      sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
      onCreate(sqLiteDatabase);
   }

   public long AddStudent(String name) {
      SQLiteDatabase db = getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(KEY_FIRSTNAME, name);
      return db.insert(TABLE_STUDENT, null, contentValues);
   }

   public ArrayList<String> getAll() {
      ArrayList<String> list = new ArrayList<>();
      SQLiteDatabase db = getReadableDatabase();
      Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_STUDENT, null);
      cursor.moveToPosition(-1);
      while (cursor.moveToNext()) {
         list.add(cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
      }
      return list;
   }
}