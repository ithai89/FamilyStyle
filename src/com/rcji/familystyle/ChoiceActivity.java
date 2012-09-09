package com.rcji.familystyle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.maps.MapActivity;

public class ChoiceActivity extends MapActivity {
	public static void startInstance(Context context) {
		Intent i = new Intent(context,ChoiceActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(i);
	}
	
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        
        Util.applyCustomFont((ViewGroup)findViewById(R.id.choicemain), Typeface.createFromAsset(getApplicationContext().getAssets(),
				"Oswald-Bold.otf"));
        
        tv = (TextView) findViewById(R.id.choicevenue);
        Tables.generateTables();
        tv.setText(Tables.getTable().get(0).toString());
        
        
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }



	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
