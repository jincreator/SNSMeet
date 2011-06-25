package org.snsmeet.main;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class AccountDB extends SQLiteOpenHelper {
    /** Called when the activity is first created. */
    public AccountDB(Context context){
    	super(context,"account.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
    	db.execSQL("CREATE TABLE twitter(_id  INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT, token TEXT);");
    	db.execSQL("CREATE TABLE facebook(_id  INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT, token TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    	db.execSQL("DROP TABLE IF EXISTS twitter");
    	db.execSQL("DROP TABLE IF EXISTS facebook");
    	onCreate(db);
    }
    public void insert_twitter(SQLiteDatabase db,String nick, String key){
    	db.execSQL("INSERT INTO twitter VALUES (null,'"+nick+"','"+key+"');");
    }
    public void insert_facebook(SQLiteDatabase db,String nick, String key){
    	db.execSQL("INSERT INTO facebook VALUES (null,'"+nick+"','"+key+"');");
    }
}