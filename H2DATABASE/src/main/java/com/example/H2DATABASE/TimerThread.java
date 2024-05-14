package com.example.H2DATABASE;

public class TimerThread extends Thread {
    private final int sleepTime; // Time to sleep in milliseconds

    public TimerThread(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(sleepTime);
                // Do something after the sleep time elapses
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}