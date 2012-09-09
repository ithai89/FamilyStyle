package com.rcji.familystyle;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.rcji.familystyle.locu.Locu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView tv = (TextView) findViewById(R.id.maintv);
        ArrayList<String> arr = Locu.query(41.214796,  -73.17417);
        String tv_str = "";
        for (String str: arr) {
        	tv_str = tv_str + str + "\n";
        }
        tv.setText(tv_str);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
