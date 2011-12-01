package com.afzal.sleepytime;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.afzal.sleepytime.utils.ActionBarActivity;
import com.bugsense.trace.BugSenseHandler;

public class SleepyTimeActivity extends ActionBarActivity {
    /** Called when the activity is first created. */
	
	private Integer hour;
	private Integer min;
	private long type;
	private Spinner timeType;
	private TimePicker time;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        BugSenseHandler.setup(this, "aecd001b");

        timeType = (Spinner) findViewById(R.id.timetype);
        time = (TimePicker) findViewById(R.id.time);        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.home, menu);

        // Calling super after populating the menu is necessary here to ensure that the
        // action bar helpers have a chance to handle this event.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "Tapped home", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_ok:
                type = timeType.getSelectedItemId();
                hour = time.getCurrentHour();
                min = time.getCurrentMinute();

				Intent i = new Intent (SleepyTimeActivity.this, ResultActivity.class);
				i.putExtra("type", type);
				i.putExtra("hour", hour);
				i.putExtra("min", min);
				startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}