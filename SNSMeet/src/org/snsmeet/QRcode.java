package org.snsmeet;

import org.snsmeet.zxing.client.android.Contents;
import org.snsmeet.zxing.client.android.Intents;
import org.snsmeet.zxing.BarcodeFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

public class QRcode extends Activity {
	/** Called when the activity is first created. */
	private AccountDB accountdb;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountdb=new AccountDB(this);
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
		Intent i= new Intent("org.snsmeet.zxing.client.android.SCAN");
		i.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(i,0);
	}
	void show(){
		Cursor account=accountdb.cursor_using_twitter();
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