package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView showTime;
    private Button startButton;
    private Button pushButton;
    private Button restartButton;

    private Chronometer chronometer;
    private Thread thread;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        showTime = (TextView) findViewById(R.id.watch);
        startButton = (Button) findViewById(R.id.start_button);
        pushButton = (Button) findViewById(R.id.push_button);
        restartButton = (Button) findViewById(R.id.restart_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer = new Chronometer(context);
                if (chronometer == null) {
                    chronometer = new Chronometer(context);
                    thread = new Thread(chronometer);
                    thread.start();
                }   chronometer.start();
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chronometer != null){
                    chronometer.stop();
                    thread.interrupt();
                    thread = null;

                    chronometer = null;
                }
            }
        });
    }

    public  void updateTimerText(final String time){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showTime.setText(time);
            }
        });
    }


}