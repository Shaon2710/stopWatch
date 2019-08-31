package com.example.stopwatch;

import android.content.Context;

public class Chronometer implements Runnable {

    public static final long mTom = 60000;
    public static final long mToh = 3600000;

    private Context context;
    private long sTime;
    private boolean isRun;

    public Chronometer(Context context) {
        this.context = context;
    }

    public void start(){
        sTime = System.currentTimeMillis();

        isRun = true;
    }
     public void stop(){
        isRun = false;
     }

    @Override
    public void run() {

        while (isRun){

            long since = System.currentTimeMillis() - sTime;

            int sec = (int) ((since / 1000) % 60);
            int min = (int) ((since / mTom) %60);
            int houre = (int) ((since / mToh) %24);

            ((MainActivity)context).updateTimerText(String.format("%02d:%02d:%02d",houre,min,sec));


        }

    }
}
