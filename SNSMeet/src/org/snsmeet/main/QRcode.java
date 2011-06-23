package org.snsmeet.main;

import org.snsmeet.R;
import org.snsmeet.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class QRcode extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qrcode);
        Intent i= new Intent("org.snsmeet.client.android.SCAN");
        i.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(i,0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT"); //형식
                String contents = intent.getStringExtra("SCAN_RESULT"); //URL
            }
            else if(resultCode == RESULT_CANCELED)
            {
                ;
            }
        }
    }
}