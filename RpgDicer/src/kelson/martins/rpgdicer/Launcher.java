package kelson.martins.rpgdicer;

import java.util.Random;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class Launcher extends Activity {
	
	static int diceMax = 10;
	static int diceNumber = 0;
	static int success = 0;
	static int critical = 0;
	static int diceMaxNumber = 40;
	private SharedPreferences prefs;
	private int maxFail = 1;
	private int minSucess = 8;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);			
		PreferenceManager.setDefaultValues(this,R.xml.preferences,false);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		maxFail = Integer.parseInt(prefs.getString("pref_fail", "1"));
		minSucess = Integer.parseInt(prefs.getString("pref_success", "8")); 
		setContentView(R.layout.activity_launcher);
	}

	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();		
		maxFail = Integer.parseInt(prefs.getString("pref_fail", "1"));
		minSucess = Integer.parseInt(prefs.getString("pref_success", "8")); 
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		Integer items[] = new Integer[40];
	    
		for (Integer nI = 0; nI <= 39 ; nI++) {
			items[nI] = nI+1;
		}
		
		// Create an ArrayAdapter using the Integer Array and a default spinner layout
		//ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_single_choice,items);	
		//adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
		//spinner.setAdapter(adapter);
		//Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		
		/*
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	diceNumber = Integer.parseInt(String.valueOf(parent.getItemAtPosition(pos)));
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    	
		    }
		});		
		*/
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);		
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
	    switch(item.getItemId()) {
	    case R.id.action_about:
	        intent = new Intent(this, AboutActivity.class);
	        this.startActivity(intent);
	        break;
	    case R.id.action_settings:
	        intent = new Intent(this, SettingsActivity.class);
	        this.startActivity(intent);
	        break;	    
	    default:
	        return super.onOptionsItemSelected(item);
	    }

	    return true;
	}
	
	/** Called when the user touches the button */
	public void generate(View view) {
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		
		diceNumber = Integer.parseInt(String.valueOf(spinner.getSelectedItem()));
		
		TextView r = new TextView(this);
		TextView s = new TextView(this);
		TextView c = new TextView(this);		

	    r=(TextView)findViewById(R.id.resultText); 
	    r.setText(returnResult(diceNumber));	
 
	    s=(TextView)findViewById(R.id.sucess); 
	    s.setText("Success: " + String.valueOf(success));
	     

	    c=(TextView)findViewById(R.id.critical); 
	    c.setText("Critical Failure: " + String.valueOf(critical));	    
	}
	
	public String returnResult(int diceNumbers) {
		String toReturn = "Results: \n";
		success = 0;
		critical = 0;
		
		Random random = new Random();
		
		int results[] = new int[40];
		
		for (int nI = 0; nI < diceNumbers; nI++) {
			results[nI] = random.nextInt(diceMax) + 1;
			
			if(results[nI] <= maxFail) {
				critical++;
			}
			
			if(results[nI] >= minSucess) {
				success++;
			}			
		}
		
		for (int nI = 0; nI < diceNumbers; nI++) {
			toReturn += String.valueOf(results[nI]);
			
			if (!(nI + 1 == diceNumbers)) {
				toReturn += " - ";
			}
			
		}
		
		return toReturn;
	}
	
	

}
