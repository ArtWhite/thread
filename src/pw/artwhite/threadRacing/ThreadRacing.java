package pw.artwhite.threadRacing;

/**
 * Created by artwhite on 29/12/2017.
 */
public class ThreadRacing extends Thread{

    private int i;

    public void run() {

        for (i = 1; i <= 2000; i++) {
            System.out.println(getName() + " - " + i);

            if(i == 2000){
                System.out.println(getName() + " завершил гонку");
            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadRacing firstRacer = new ThreadRacing();
        ThreadRacing secondRacer = new ThreadRacing();

        firstRacer.setPriority(MAX_PRIORITY);
        secondRacer.setPriority(MIN_PRIORITY);

        firstRacer.start();
        secondRacer.start();

        while (firstRacer.isAlive() || secondRacer.isAlive()) {
            if (firstRacer.i % 10 == 0) {
                secondRacer.setPriority(MAX_PRIORITY);
                firstRacer.setPriority(MIN_PRIORITY);
            }

            if (secondRacer.i % 20 == 0) {
                secondRacer.setPriority(MIN_PRIORITY);
                firstRacer.setPriority(MAX_PRIORITY);
            }
        }
    }
}