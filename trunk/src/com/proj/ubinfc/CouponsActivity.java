/**
 * 
 */
package com.proj.ubinfc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.proj.ubinfc.nfc.ForegroundDispatch;

/**
 * @author Jogi 
 * Activty that handles all the coupons stuff
 */
public class CouponsActivity extends Activity {

	private Button butScanCoupon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coupons);

		butScanCoupon = (Button) findViewById(R.id.butScanCoupon);

		butScanCoupon.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i("Coupons",
						"Coupons Activity started, will scan a coupon now...");
				Intent i = new Intent(getApplicationContext(),
						ForegroundDispatch.class);
				i.putExtra("next", "Coupons");
				startActivity(i);
			}
		});
	}
}
