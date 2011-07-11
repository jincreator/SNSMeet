package org.snsmeet;

import org.snsmeet.AccountDB;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ToggleButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.MergeCursor;
import android.graphics.drawable.Drawable;
import android.widget.CursorAdapter;
import android.widget.Toast;
import android.content.Context;

public class Account extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private AccountDB accountdb;
	private Cursor cursor_twitter;
	private Cursor cursor_facebook;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountdb=new AccountDB(this);
        cursor_twitter=accountdb.cursor_twitter();
        startManagingCursor(cursor_twitter);
        cursor_facebook=accountdb.cursor_facebook();
        startManagingCursor(cursor_facebook);
        Cursor[] mcursor=new Cursor[] {cursor_twitter,cursor_facebook};
        Cursor account_cursor=new MergeCursor(mcursor);
        startManagingCursor(account_cursor);
        setContentView(R.layout.account);
        ListView account_list=(ListView)findViewById(R.id.account_listview);
        AccountAdapter account_adapter=new AccountAdapter(this,account_cursor);
        account_list.setAdapter(account_adapter);
        View account_add=findViewById(R.id.account_add);
        account_add.setOnClickListener(this);
    }
	class AccountAdapter extends CursorAdapter{
		private Cursor cursor;
		public AccountAdapter(Context context, Cursor cur) {
            super(context,cur);
            cursor=cur;
        }

        @Override
        public View newView(Context context, Cursor cur, ViewGroup parent) {
            LayoutInflater li = LayoutInflater.from(context);
            return li.inflate(R.layout.account_list, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cur) {
        	final int cs=cur.getInt(cur.getColumnIndex("_id"));
        	final String csv=cur.getString(cur.getColumnIndex("service"));
        	TextView nick = (TextView)view.findViewById(R.id.account_nick);
        	final ToggleButton use = (ToggleButton)view.findViewById(R.id.account_toggle);
        	Drawable twitter_icon = getResources().getDrawable(R.drawable.ic_twitter_newbird_blue);
        	twitter_icon.setBounds(0, 0, twitter_icon.getIntrinsicWidth(), twitter_icon.getIntrinsicHeight());
        	Drawable facebook_icon = getResources().getDrawable(R.drawable.ic_f_logo);
        	facebook_icon.setBounds(0, 0, facebook_icon.getIntrinsicWidth(), facebook_icon.getIntrinsicHeight());
        	if(csv.equals("twitter"))
        		use.setCompoundDrawables(null,twitter_icon,null,null);
        	if(csv.equals("facebook"))
        		use.setCompoundDrawables(null,facebook_icon,null,null);
        	nick.setText(cur.getString(cur.getColumnIndex("nick")));
        	use.setChecked(cur.getLong(cur.getColumnIndex("use"))==1);
        	use.setOnClickListener(new OnClickListener(){
        		public void onClick(View v){
        			if(csv.equals("twitter"))
        				accountdb.use_twitter(use.isChecked(), cs);
        			if(csv.equals("facebook"))
        				accountdb.use_facebook(use.isChecked(), cs);
        		}
        	});
            ImageView delete = (ImageView)view.findViewById(R.id.account_delete);
            delete.setOnClickListener(new OnClickListener() {
                
                public void onClick(View v) {
                	if(csv.equals("twitter"))
                		accountdb.delete_twitter(cs);
                	if(csv.equals("facebook"))
                		accountdb.delete_facebook(cs);
    				cursor.requery();
                }
            });
        }
    }
		
	public void onClick(View v){
		switch(v.getId()){
		case R.id.account_add:
			final Intent twitter=new Intent(this,Twitter_Add.class);
            final Intent facebook;
			AlertDialog.Builder b=new AlertDialog.Builder(this);
			b.setTitle(R.string.account_add);
			b.setItems(R.array.sns_type,new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int sns_type){
					switch(sns_type){
					case 0:
						startActivity(twitter);
						cursor_twitter.requery();
						break;
					case 1:
						accountdb.insert_facebook("dcba","efgh","higk");
						cursor_facebook.requery();
						break;
					}
				}
			});
			b.show();
			break;
		}
	}
}