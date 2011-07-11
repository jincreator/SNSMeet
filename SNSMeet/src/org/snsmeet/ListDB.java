package org.snsmeet;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

public class ListDB{
	private LDB listdb;
	private SQLiteDatabase sdb;
	public ListDB(Context ctx){
		listdb=new LDB(ctx);
		sdb=listdb.getWritableDatabase();
	}
    class LDB extends SQLiteOpenHelper {
        /** Called when the activity is first created. */
        public LDB(Context context){
            super(context,"list.db",null,1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE twitter(_id INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT);");
            db.execSQL("CREATE TABLE facebook(_id INTEGER PRIMARY KEY AUTOINCREMENT, nick TEXT);");
        }
        @Override
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            db.execSQL("DROP TABLE IF EXISTS twitter");
            db.execSQL("DROP TABLE IF EXISTS facebook");
            onCreate(db);
        }
    }
    public void insert_twitter(String nick){
    	sdb.execSQL("INSERT INTO twitter VALUES (null,'"+nick+"');");
    }
    public void delete_twitter(int id){
    	sdb.execSQL("DELETE FROM twitter WHERE _id='"+id+"';");
    }
    public void insert_facebook(String nick){
    	sdb.execSQL("INSERT INTO facebook VALUES (null,'"+nick+"');");
    }
    public void delete_facebook(int id){
    	sdb.execSQL("DELETE FROM facebook WHERE _id='"+id+"';");
    }
    public Cursor cursor_twitter(){
    	return sdb.rawQuery("SELECT * FROM twitter",null);
    }
    public Cursor cursor_facebook(){
    	return sdb.rawQuery("SELECT * FROM facebook",null);
    }
}