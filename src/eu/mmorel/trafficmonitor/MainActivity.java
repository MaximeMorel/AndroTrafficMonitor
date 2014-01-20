package eu.mmorel.trafficmonitor;

import android.net.TrafficStats;
import android.os.Bundle;
import android.app.Activity;
import android.service.notification.StatusBarNotification;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView tv;
	long lastTotal = 0;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv = (TextView) findViewById(R.id.textView1);
        
        tv.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				long total = TrafficStats.getTotalRxBytes() + TrafficStats.getTotalTxBytes();
				long traffic = total - lastTotal;
				lastTotal = total;
				
				tv.setText(traffic/1024 + " KiB/s");
				tv.postDelayed(this, 1000);
				//StatusBarNotification.
			}
		}, 1000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
