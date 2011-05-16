/**
 * 
 */
package com.proj.ubinfc;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * @author Jogi
 *
 */
public class MainActivity extends TabActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final TabHost tabHost = getTabHost();
        
        Intent intent = getIntent();     
        

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("Social")
                .setContent(new Intent(this, SocialActivity.class)));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("Coupons")
                .setContent(new Intent(this, MainListActivity.class)));
        
        // This tab sets the intent flag so that it is recreated each time
        // the tab is clicked.
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("Products")
                .setContent(new Intent(this, SocialActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator("Authenticate")
                .setContent(new Intent(this, SocialActivity.class)));       
        
        
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(getIntent().getExtras()!=null)
        	getTabHost().setCurrentTab(getIntent().getExtras().getInt("tab"));
	}

}
