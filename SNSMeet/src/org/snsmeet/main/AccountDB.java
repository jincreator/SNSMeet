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
    	db.execSQL("CREATE TABLE twitter(_id INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT, token TEXT, token_secret TEXT, use INTEGER);");
    	db.execSQL("CREATE TABLE facebook(_id INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT, token TEXT, token_secret TEXT, use INTEGER);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    	db.execSQL("DROP TABLE IF EXISTS twitter");
    	db.execSQL("DROP TABLE IF EXISTS facebook");
    	onCreate(db);
    }
    public void insert_twitter(SQLiteDatabase db,String nick, String token, String token_secret){
    	db.execSQL("INSERT INTO twitter VALUES (null,'"+nick+"','"+token+"','"+token_secret+"','1');");
    }
    public void delete_twitter(SQLiteDatabase db,int id){
    	db.execSQL("DELETE FROM twitter WHERE _id='"+id+"';");
    }
    public void use_twitter(SQLiteDatabase db, boolean onoff, int id){
    	db.execSQL("UPDATE twitter SET use="+(int)(onoff?1:0)+" WHERE _id="+id+";");
    }
    public void insert_facebook(SQLiteDatabase db,String nick, String token, String token_secret){
    	db.execSQL("INSERT INTO facebook VALUES (null,'"+nick+"','"+token+"','"+token_secret+"','1');");
    }
    public void delete_facebook(SQLiteDatabase db,int id){
    	db.execSQL("DELETE FROM facebook WHERE _id='"+id+"';");
    }
    public void use_facebook(SQLiteDatabase db, boolean onoff, int id){
    	db.execSQL("UPDATE facebook SET use="+(int)(onoff?1:0)+" WHERE _id="+id+";");
    }
}