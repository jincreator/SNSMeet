package org.snsmeet.main;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class AccountDB{
	private ADB accountdb;
	private SQLiteDatabase sdb;
	public AccountDB(Context ctx){
		accountdb=new ADB(ctx);
		sdb=accountdb.getWritableDatabase();
	}
    class ADB extends SQLiteOpenHelper {
        /** Called when the activity is first created. */
        public ADB(Context context){
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
    }
    public void insert_twitter(String nick, String token, String token_secret){
    	sdb.execSQL("INSERT INTO twitter VALUES (null,'"+nick+"','"+token+"','"+token_secret+"','1');");
    }
    public void delete_twitter(int id){
    	sdb.execSQL("DELETE FROM twitter WHERE _id='"+id+"';");
    }
    public void use_twitter(boolean onoff, int id){
    	sdb.execSQL("UPDATE twitter SET use="+(int)(onoff?1:0)+" WHERE _id="+id+";");
    }
    public void insert_facebook(String nick, String token, String token_secret){
    	sdb.execSQL("INSERT INTO facebook VALUES (null,'"+nick+"','"+token+"','"+token_secret+"','1');");
    }
    public void delete_facebook(int id){
    	sdb.execSQL("DELETE FROM facebook WHERE _id='"+id+"';");
    }
    public void use_facebook(boolean onoff, int id){
    	sdb.execSQL("UPDATE facebook SET use="+(int)(onoff?1:0)+" WHERE _id="+id+";");
    }
    public Cursor cursor_twitter(){
    	return sdb.rawQuery("SELECT * FROM twitter",null);
    }
    public Cursor cursor_facebook(){
    	return sdb.rawQuery("SELECT * FROM facebook",null);
    }
    public Cursor cursor_using_twitter(){
    	return sdb.rawQuery("SELECT * FROM twitter WHERE use=1", null);
    }
    public Cursor cursor_using_facebook(){
    	return sdb.rawQuery("SELECT * FROM facebook WHERE use=1", null);
    }

}