package com.example.a73645.listviewtesst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by 73645 on 2018/1/30.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    public static final String CREATE_LIST = "create table list("+"id integer primary key autoincrement, "+"type integer, "+"title text, "+"todo text)";
    private Context mContext;
    public MyDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory,int version)
    {
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_LIST);
        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists List");
        onCreate(db);
    }
}
