package com.proj.ubinfc.nfc;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;
import com.proj.ubinfc.MainActivity;
import com.proj.ubinfc.nfc.record.NFCRequestRecord;
import com.proj.ubinfc.nfc.record.ParsedNdefRecord;
import com.proj.ubinfc.nfc.record.TextRecord;
import com.proj.ubinfc.obj.TwitterRecord;
import com.proj.ubinfc.twitter.TwitterUtils;

public class SocialRequestHandler {
	
	private ParsedNdefRecord record;
	private Context context;
	
	public SocialRequestHandler(Context context, ParsedNdefRecord record) {
		this.context = context;
		this.record = record;
	}
	
	public void handle() {
		if(record==null) {
			
		} else {
			if(record.getClass().equals(TextRecord.class)) {
				//json to object
				TextRecord data = (TextRecord) record;
				NFCRequestRecord socialRecord = new Gson().fromJson(data.getText(), NFCRequestRecord.class);
				
				//if request type is twitter
				if(socialRecord.getRequestType().equalsIgnoreCase("twitter")) {
					try {
						//Get the specific request object
						TwitterRecord twitterRec = (TwitterRecord) new Gson().fromJson(socialRecord.getSpecificRequest(), TwitterRecord.class);
						TwitterUtils.sendFollowRequest(context, twitterRec.getScreenName());
						context.startActivity(new Intent(context,MainActivity.class).putExtra("tab", 1));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (socialRecord.getRequestType().equalsIgnoreCase("linkedin")) {
					
				} else if (socialRecord.getRequestType().equalsIgnoreCase("contact")) {
					
				} else {
					Log.e("SocialRequestHandler", "Unknown request type: "+socialRecord.getRequestType());
				}				
			}
		}
	}
}
