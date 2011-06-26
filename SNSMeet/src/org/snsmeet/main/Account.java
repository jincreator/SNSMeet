package org.snsmeet.main;

import org.snsmeet.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ListView;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class Account extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private AccountDB accountdb;
	private SQLiteDatabase db;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountdb=new AccountDB(this);
        db=accountdb.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM twitter",null);
        startManagingCursor(c);
        setContentView(R.layout.account);
        SimpleCursorAdapter a=new SimpleCursorAdapter(this,R.layout.account_list,c,new String[]{"nick","token"},new int[]{R.id.account_nick,R.id.account_token});
        ListView l=(ListView)findViewById(R.id.account_listview);
        l.setAdapter(a);
        View account_add=findViewById(R.id.account_add);
        account_add.setOnClickListener(this);
    }
	public void onClick(View v){
		switch(v.getId()){
		case R.id.account_add:
			final Intent twitter=new Intent(this,Twitter_Add.class);
			AlertDialog.Builder b=new AlertDialog.Builder(this);
			b.setTitle(R.string.account_add);
			b.setItems(R.array.sns_type,new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int sns_type){
					switch(sns_type){
					case 0:
						startActivity(twitter);
						db.execSQL("INSERT INTO twitter VALUES (null,'abcd','efgh',1);");
						break;
					}
				}
			});
			b.show();
		}
	}
}