package com.sivaram.session16assignment2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    // Declare Progress Bars  Objects
    ProgressBar firstProgressBar, secondProgressBar, thirdProgressBar, fourthProgressBar;

    // Declare Button Objects
    Button startParallelDownloaButton;

    // Declare Progress Bar Async Task Class Object.
    StartProgressBarAsync firstAsyncProgressBar, secondAsyncProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Type Cast Progress Bar from XML to Java code to access them in Async Task
        firstProgressBar = (ProgressBar) findViewById(R.id.firstProgressBar);

        // Second Progress Bar
        secondProgressBar = (ProgressBar) findViewById(R.id.secondProgressBar);

        // Third Progress Bar
        thirdProgressBar = (ProgressBar) findViewById(R.id.thirdProgressBar);

        // Fourth Progress Bar
        fourthProgressBar = (ProgressBar) findViewById(R.id.fourthProgressBar);

        // Type Cast Button To Access in java
        startParallelDownloaButton = (Button)findViewById(R.id.parallelDownloadButton);

        // Set On Click Listener To Start Progress Bar Async Task
        startParallelDownloaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Initialize Progress Bar With 0 Value;
                firstProgressBar.setProgress(0);
                secondProgressBar.setProgress(0);
                thirdProgressBar.setProgress(0);
                fourthProgressBar.setProgress(0);

                // Create Async Task Object and Execute by passing the Progress Bar Objects which would the status be updated.
                new StartProgressBarAsync().execute(firstProgressBar);

                new StartProgressBarAsync().execute(secondProgressBar);

            }
        });
    }


    // Create Progress Bar Progress Update Class Which extends from AsyncTask
    class StartProgressBarAsync extends AsyncTask<ProgressBar, Integer, Void>{


        // On Do In Background method. change the progress bar value.
        @Override
        protected Void doInBackground(ProgressBar... progressBarParams) {
            // Declare Progress bar object based on the prarameters passed through do in
            final ProgressBar progressBar = (ProgressBar)progressBarParams[0];

            // Using Run on UI Thread Method access the Progress Bar
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    // Update Progress Bar Status using for loop
                    for(int i=0;i<100;i++){
                        progressBar.setProgress(i);
                        // Stop Thread to update and view progress of progress bar
                        SystemClock.sleep(5);
                    }
                }
            });

            return null;
        }
    }
}
