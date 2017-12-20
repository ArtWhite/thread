package pw.artwhite.interferens1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by artwhite on 03/11/2017.
 */
public class InterferenceExample {
    private static final int HUNDRED_MILLION = 100_000_000;
    private AtomicInteger counter = new AtomicInteger();

    public boolean stop(){
        return counter.incrementAndGet() > HUNDRED_MILLION;
    }

    public void example() throws InterruptedException{
        InterferenceThread thread1 = new InterferenceThread("Поток 1", this);
        InterferenceThread thread2 = new InterferenceThread("Поток 2", this);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Ожидаем: " + HUNDRED_MILLION);
        System.out.println("Получим: " + thread1.getI());
    }
}
