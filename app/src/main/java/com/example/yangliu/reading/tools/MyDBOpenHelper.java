package com.example.yangliu.reading.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 刘海风 on 2016/8/4.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    private static MyDBOpenHelper myDBOpenHelper;
    public  static  MyDBOpenHelper in(Context context){
        if (myDBOpenHelper==null){
            myDBOpenHelper=new MyDBOpenHelper(context,"data",null,1);

        }
        return myDBOpenHelper;

    }
    public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "data", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DATA1(id INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAE(2000),image VARCHAE(100),url VARCHAE(100),parent VARCHAE(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
