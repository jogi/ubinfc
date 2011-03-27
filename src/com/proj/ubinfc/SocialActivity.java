package com.proj.ubinfc;

import java.util.Date;

import twitter4j.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.proj.ubinfc.nfc.ForegroundDispatch;
import com.proj.ubinfc.nfc.ForegroundNdefPush;
import com.proj.ubinfc.nfc.record.SocialRequestRecord;
import com.proj.ubinfc.twitter.LoginButton;
import com.proj.ubinfc.twitter.TwitterUtils;

public class SocialActivity extends Activity {
	
	private final Handler notificationHandler = new Handler();
	private String notificationText = "";
	
	final Runnable notification = new Runnable() {
		public void run() {
			Toast.makeText(getBaseContext(), notificationText, Toast.LENGTH_LONG).show();
		}
	};
	
	private LoginButton twtLoginButton;
    private SharedPreferences prefs;
    private Button butTwtBroadcast;
    private Button butTwtFollow;
    
    private static final int DIALOG_YES_NO_MESSAGE = 1;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.proj.ubinfc.R.layout.social);
        
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        twtLoginButton = (LoginButton) findViewById(R.id.loginTwitter);
        butTwtBroadcast = (Button) findViewById(R.id.butTwtBroadcast);
        butTwtFollow = (Button) findViewById(R.id.butTwtFollow);
        
        twtLoginButton.init(this);
        
        
        butTwtBroadcast.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
            	if(TwitterUtils.isAuthenticated(prefs))
            	{
	            	Log.i("Twitter info broadcast", "Tag broadcast initiated");
	            	Intent i = new Intent(getApplicationContext(), ForegroundNdefPush.class);
	            	
	            	//Get current user and get the JSON string
	            	User user = null;
	            	try {
						user = TwitterUtils.getCurrentUser(prefs);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Log.e("Twitter", "Error retrieving current user");
					}
	            	
					SocialRequestRecord reqRecord = new SocialRequestRecord();
					reqRecord.setId(user.getId());
					reqRecord.setRequestType("twitter");
					reqRecord.setTwitterScreenName(user.getScreenName());
					
					String jsonRecord = new Gson().toJson(reqRecord);
					Log.i("JSON Object", jsonRecord);
	            	i.putExtra("nfc_message", jsonRecord);
					
	            	startActivity(i);
            	} else {
            		//show dialog
            		showDialog(DIALOG_YES_NO_MESSAGE);
            	}
            }
        });
        
        butTwtFollow.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {            	
            	if(TwitterUtils.isAuthenticated(prefs))
            	{
	            	Log.i("Twitter follow request", "Follow request started. Waiting for nfc tag.");
	            	Intent i = new Intent(getApplicationContext(), ForegroundDispatch.class);	            	
					startActivity(i);
            	} else {
            		//show dialog
            		showDialog(DIALOG_YES_NO_MESSAGE);
            	}
            }
        });
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
    	return new AlertDialog.Builder(SocialActivity.this)
        .setIcon(R.drawable.alert_dialog_icon)
        .setTitle(R.string.loginAlertText)
        .setPositiveButton(R.string.loginAlertOK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                /* User clicked OK so do some stuff */
            }
        })
        .create();
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		updateLoginStatus();
	}

	public void updateLoginStatus() {
		twtLoginButton.setImageResource(TwitterUtils.isAuthenticated(prefs) ? R.drawable.logout_button : R.drawable.login_button);
	}

}