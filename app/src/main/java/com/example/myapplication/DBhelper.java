package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    private Context context;

    public DBhelper(Context context) {
        super(context, "contact.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table contact (id INTEGER primary key autoincrement, name TEXT, number TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists contact");
    }



    public Boolean addingContact(String name, String num){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("number",num);
        long result = db.insert("contact",null,cv);
        if (result == -1){
            return true;
        }else{
            return false;
        }

    }
    Cursor readAllData(){
        String query = "select * from contact";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    boolean updateData(String id , String name, String num){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name );
        cv.put("number", num);
        long result = db.update("contact",cv,"id = ?",new String[]{id});
        if (result == -1){
            return false;
        }else{
           return true;
        }
    }

    boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("contact","id = ?", new String[]{id});
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from contact");
    }
}
