package org.snsmeet.main;

import org.snsmeet.BarcodeFormat;
import org.snsmeet.R;
import org.snsmeet.client.android.Contents;
import org.snsmeet.client.android.Intents;
import org.snsmeet.client.android.share.ShareActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QRcode extends Activity {
    /** Called when the activity is first created. */
	private AccountDB accountdb=new AccountDB(this);
	private SQLiteDatabase db;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder b=new AlertDialog.Builder(this);
		b.setTitle(R.string.account_add);
		b.setItems(R.array.qrcode_order,new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int qrcode_order){
				switch(qrcode_order){
				case 1:
					show();
					scan();
					break;
				case 0:
					scan();
					show();
					break;
				}
			}
		});
		b.show();
        setContentView(R.layout.qrcode);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT"); //형식
                String contents = intent.getStringExtra("SCAN_RESULT");
            }
            else if(resultCode == RESULT_CANCELED)
            {
                ;
            }
        }
    }
	void scan(){
		Intent i= new Intent("org.snsmeet.client.android.SCAN");
		i.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(i,0);
	}
	void show(){
		db=accountdb.getWritableDatabase();
		Cursor account=db.rawQuery("SELECT * FROM twitter WHERE use=1", null);
		account.moveToFirst();
	    Intent i = new Intent(Intents.Encode.ACTION);
	    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
	    i.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
	    i.putExtra(Intents.Encode.FORMAT, BarcodeFormat.QR_CODE.toString());
	    i.putExtra(Intents.Encode.DATA, "http://twitter.com/"+account.getString(account.getColumnIndex("nick")));
	    startActivity(i);
	    while(account.moveToNext()){
	    	i.putExtra(Intents.Encode.DATA, "http://twitter.com/"+account.getString(account.getColumnIndex("nick")));
		    startActivity(i);
	    }
	}
}