package pw.artwhite.test;

import pw.artwhite.currentThread.CurrentThread;

/**
 * Created by artwhite on 30/10/2017.
 */
public class HelloRunnableWithDelay extends Thread{
    public void run() {
        try {
            Thread.sleep(1);
            System.out.println("Hello from thread!");
        } catch (InterruptedException e) {
            System.out.println("err");
        }

    }

    public static void main(String[] args) {
        new HelloRunnableWithDelay().start();
        System.out.println("Hello from main thread");
    }
}
