package com.proj.ubinfc.nfc;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.proj.ubinfc.SocialActivity;
import com.proj.ubinfc.nfc.record.ParsedNdefRecord;
import com.proj.ubinfc.nfc.record.SocialRequestRecord;
import com.proj.ubinfc.nfc.record.TextRecord;
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
				SocialRequestRecord socialRecord = new Gson().fromJson(data.getText(), SocialRequestRecord.class);
				
				if(socialRecord.getRequestType().equalsIgnoreCase("twitter")) {
					try {
						TwitterUtils.sendFollowRequest(context, socialRecord.getTwitterScreenName());
						context.startActivity(new Intent(context,SocialActivity.class));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}
	}

}
