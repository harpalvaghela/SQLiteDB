package com.ccd.harpalvaghela.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class helper extends SQLiteOpenHelper {

    public static String dbname="data";
    public static String tbname="reg";
    public static String name="name";
    public static String email="email";
    public static String id="id";
    public static int dbversion=1;

    static String createquery="create table "+tbname+"("+id+" integer primary key autoincrement,"+name+" varchar(30),"+email+" varchar(50));";

    public helper(Context contex) {
        super(contex, name, null, dbversion);
        Log.d("data","Database Created.");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createquery);
        Log.d("data","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    void insert(String sname,String semail)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",sname);
        cv.put("email",semail);
        db.insert(tbname,null,cv);
        Log.d("data","inserted...");
    }

    public ArrayList<pojo> getdata()
    {
        ArrayList<pojo> list=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String sql="select * from "+tbname;
        Cursor c=db.rawQuery(sql,null);
        while (c.moveToNext())
        {
            String id=c.getString(0);
            String name=c.getString(1);
            String email=c.getString(2);
            pojo p=new pojo();
            p.setId(id);
            p.setEmail(email);
            p.setName(name);
            list.add(p);
        }
        return list;
    }

    public void delete(String sid)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="delete from "+tbname+" where "+id+"="+sid;
        db.execSQL(sql);
        Log.d("data","Deleted...");
    }

    public void update(String sid,String sname,String semail)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",sname);
        cv.put("email",semail);
      //  db.update(tbname,cv,id+"="+sid,null);
        db.update(tbname,cv,id+"="+sid,null);

    }
}
