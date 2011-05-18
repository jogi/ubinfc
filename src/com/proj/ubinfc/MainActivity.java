/**
 * 
 */
package com.proj.ubinfc;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
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
        setContentView(R.layout.main);
        
        final TabHost tabHost = getTabHost();
        Resources res = getResources();    
        

        tabHost.addTab(tabHost.newTabSpec("social")
                .setIndicator("Social", res.getDrawable(R.drawable.ic_tab_social))
                .setContent(new Intent(this, SocialActivity.class)));

        tabHost.addTab(tabHost.newTabSpec("coupons")
                .setIndicator("Coupons")
                .setContent(new Intent(this, CouponsActivity.class)));
        
        // This tab sets the intent flag so that it is recreated each time
        // the tab is clicked.
        tabHost.addTab(tabHost.newTabSpec("products")
                .setIndicator("Products")
                .setContent(new Intent(this, SocialActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)));
        
        tabHost.addTab(tabHost.newTabSpec("authenticate")
                .setIndicator("Authenticate")
                .setContent(new Intent(this, SocialActivity.class)));       
        
        tabHost.setCurrentTab(3);
    }
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(getIntent().getExtras()!=null)
        	getTabHost().setCurrentTab(getIntent().getExtras().getInt("tab"));
	}

}
