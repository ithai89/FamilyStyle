package com.rcji.familystyle;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.rcji.familystyle.locu.Locu;

public class MainActivity extends Activity {
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.maintv);
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE); 
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location!=null) {
        	double longitude = location.getLongitude();
        	double latitude = location.getLatitude();
        	Locu.query(latitude, longitude, tv);
        }
        //lm.requestLocationUpdates(LocationManager.GPS, 2000, 10, locationListener);
        final ImageView iv = (ImageView) findViewById(R.id.mainiv);
        iv.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				tv.setVisibility(View.VISIBLE);
				iv.setVisibility(View.GONE);
				ChoiceActivity.startInstance(MainActivity.this);
				
			}
		});

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);
        
        
    }
    
    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            Locu.query(latitude,longitude, tv);
        }

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
    };

    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
