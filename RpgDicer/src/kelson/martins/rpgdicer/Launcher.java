package kelson.martins.rpgdicer;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Launcher extends Activity {

	static int diceMax = 10;
	static int diceNumber = 0;
	static int success = 0;
	static int critical = 0;
	static int diceMaxNumber = 40;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);			
		
		setContentView(R.layout.activity_launcher);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		Integer items[] = new Integer[40];
	    
		for (Integer nI = 0; nI <= 39 ; nI++) {
			items[nI] = nI+1;
		}
		
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		
		// Create an ArrayAdapter using the Integer Array and a default spinner layout
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item,items);
		
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
		    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		    	diceNumber = (Integer) parent.getItemAtPosition(pos);
		    }
		    public void onNothingSelected(AdapterView<?> parent) {
		    }
		});		
		
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);		
		return true;
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId()) {
	    case R.id.action_about:
	        Intent intent = new Intent(this, AboutActivity.class);
	        this.startActivity(intent);
	        break;
	    default:
	        return super.onOptionsItemSelected(item);
	    }

	    return true;
	}
	
	/** Called when the user touches the button */
	public void generate(View view) {
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
			
			if(results[nI] == 1) {
				critical++;
			}
			
			if(results[nI] >= 8) {
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
