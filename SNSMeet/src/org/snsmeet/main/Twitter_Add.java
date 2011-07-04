package org.snsmeet.main;

import org.snsmeet.R;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class Twitter_Add extends Activity{
	public static String consumerKey = "9rLaK4l7RBn2x4YMgRVkw";
	public static String consumerSecret = "sbkoZ5Q5kCngvPh65e1HM7eQoGLdW5FfudfwkcOPy5Q";
	public static Uri CALLBACK_URL = Uri.parse("snsmeet://twitter");	 
	private Twitter twitter;
	private AccessToken acToken;
	private RequestToken rqToken;
	private Status status = null;
	private String token;
	private String stoken;
	private AccountDB accountdb;
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_add);
        accountdb=new AccountDB(this);
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerSecret);
        try {
        	rqToken = twitter.getOAuthRequestToken(CALLBACK_URL.toString());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
        token=rqToken.getToken();
        stoken=rqToken.getTokenSecret();
        startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse(rqToken.getAuthorizationURL())));
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri uri = intent.getData();
        if(uri != null && CALLBACK_URL.getScheme().equals(uri.getScheme())){
            String oauth_verifier = uri.getQueryParameter("oauth_verifier");
            try {
                 acToken = twitter.getOAuthAccessToken(rqToken, oauth_verifier);
                 accountdb.insert_twitter(token, token, stoken);
            } catch (TwitterException e) {
               e.printStackTrace();
            }
        }
    }
}