package com.andi.bukutamuonline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DB_Controller extends SQLiteOpenHelper {
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "BUKUTAMU.DB", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE TAMU(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAMA TEXT unique, TUJUAN TEXT, TGL TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TAMU;");
        onCreate(sqLiteDatabase);
    }

    public void insert_tamu(String nama, String tujuan,String tgl){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAMA", nama);
        contentValues.put("TUJUAN", tujuan);
        contentValues.put("TGL", tgl);
        this.getWritableDatabase().insertOrThrow("TAMU","",contentValues);
    }

    public void delete_tamu(String nama){
        this.getWritableDatabase().delete("TAMU","nama='"+nama+"'",null);
    }

    public void update_tamu(String old_nama, String new_nama){
        this.getWritableDatabase().execSQL("UPDATE TAMU SET NAMA='"+new_nama+"' WHERE NAMA='"+old_nama+"'");
    }

    public void list_all_tamu(TextView textView){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM TAMU", null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+"\n");
        }
    }
}
