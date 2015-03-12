package cmput301w15t07.TravelTracker.activity;

/*
 *   Copyright 2015 Kirby Banman,
 *                  Stuart Bildfell,
 *                  Elliot Colp,
 *                  Christian Ellinger,
 *                  Braedy Kuzma,
 *                  Ryan Thornhill
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.util.concurrent.CountDownLatch;

import cmput301w15t07.TravelTracker.DataSourceSingleton;
import cmput301w15t07.TravelTracker.model.DataSource;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * The base activity for TravelTracker activities.
 * 
 * Referenced http://stackoverflow.com/a/16636992 on 10/03/15
 * 
 * @author Elliot,
 *         therabidsquirel
 *
 */
public class TravelTrackerActivity extends Activity {
    /** String used to retrieve user data from intent */
    public static final String USER_DATA = "cmput301w15t07.TravelTracker.userData";
    
    /** String used to retrieve claim UUID from intent */
    public static final String CLAIM_UUID = "cmput301w15t07.TravelTracker.claimUUID";
    
    /** String used to retrieve item UUID from intent */
    public static final String ITEM_UUID = "cmput301w15t07.TravelTracker.itemUUID";
    
	private CountDownLatch loadedLatch = new CountDownLatch(1);
	
	protected DataSourceSingleton app;
	protected DataSource datasource;
	
	/**
	 * Called when the activity finishes loading.
	 */
	public void onLoaded() {
		loadedLatch.countDown();
	}
	
	/**
	 * Blocks the thread until the activity is loaded.
	 * @throws InterruptedException
	 */
	public void waitUntilLoaded() throws InterruptedException {
		loadedLatch.await();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    app = (DataSourceSingleton) getApplication();
        datasource = app.getDataSource();
	}
	
    public void signOut() {
        // adapted from 
        //    http://stackoverflow.com/questions/6298275/how-to-finish-every-activity-on-the-stack-except-the-first-in-android
        // on 10 March 2015
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Removes other Activities from stack
        startActivity(intent);
    }

    public void appendNameToTitle(String name) {
        setTitle(getTitle() + " - " + name);
    }
}