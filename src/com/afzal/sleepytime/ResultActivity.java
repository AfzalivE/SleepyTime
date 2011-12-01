package com.afzal.sleepytime;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.afzal.sleepytime.utils.ActionBarActivity;

public class ResultActivity extends ActionBarActivity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        
        Bundle extras = getIntent().getExtras();
        
        if (extras != null) {
        	Integer type = (int) extras.getLong("type");
        	Integer oldHour = extras.getInt("hour");
        	Integer oldMin = extras.getInt("min");
        	
        	String sOldMin = null;
        	if (oldMin.toString().length() == 1) {
        		sOldMin = "0" + oldMin;
        	} else {
        		sOldMin = "" + oldMin;
        	}
        	
        	String selTime =  oldHour + ":" + sOldMin;
        	TextView inTime = (TextView) findViewById(R.id.intime);
        	inTime.setText(selTime);
        	Log.d("ST", type + " | " + selTime);
        	
        	Integer[] newHour = new Integer[4];
			Integer[] newMin = new Integer[4];
        	String[] newTimes = new String[4];
        	
			switch (type) {
        		case 0: 
                	newHour[0] = oldHour + (oldMin + 90) / 60;
        			newMin[0] = (oldMin + 90) % 60;
                	for (int i = 1; i < newHour.length; i++) {
                		newHour[i] = oldHour + (oldMin + (i + 3)* 90) / 60;
                		newMin[i] = (oldMin + (i + 3) * 90) % 60;
                	}
        			break;
        		case 1:
        			for (int i = 0; i < newHour.length; i++) {
	        			newHour[i] = (oldHour + (oldMin + (i+2) * 90) / 60);
	        			newMin[i] = (oldMin + (i+2) * 90) % 60;
        			}
        			break;
        		default:
        			break;
        	}
			
			TextView[] outTimes = new TextView[4];
			
			outTimes[0] = (TextView) findViewById(R.id.outtime1);
			outTimes[1] = (TextView) findViewById(R.id.outtime2);
			outTimes[2] = (TextView) findViewById(R.id.outtime3);
			outTimes[3] = (TextView) findViewById(R.id.outtime4);
			
			for (int i = 0 ; i < newHour.length; i++) {
				String sNewMin = null;
				if (newMin[i].toString().length() == 1) {
					sNewMin = "0" + newMin[i];
				} else {
					sNewMin = "" + newMin[i];
				}
				newTimes[i] = newHour[i].toString() + ":" + sNewMin;
				outTimes[i].setText(newTimes[i]);
				Log.d("ST", type + " | " + newTimes[i]);
			}
        }
    }
    
    @Override
    protected void onStart() {
        int versionNumber = Integer.valueOf(android.os.Build.VERSION.SDK);
        if (versionNumber >= 11) {
                super.onStart();
                ActionBar actionBar = this.getActionBar();
                actionBar.setDisplayHomeAsUpEnabled(true);
        }
        else {
                super.onStart();
        }
    }

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.result, menu);

        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, SleepyTimeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
