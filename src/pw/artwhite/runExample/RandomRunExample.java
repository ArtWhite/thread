package pw.artwhite.runExample;

/**
 * Класс запуска нескольких потоков
 *
 * @author Artem Umnov
 */
public class RandomRunExample extends Thread{

    private String nameOfThread;
    private int priorityOfThread;

    private RandomRunExample(String nameOfThread, int priorityOfThread) {
        this.nameOfThread = nameOfThread;
        this.priorityOfThread = priorityOfThread;
    }

    public void run(){
        Thread.currentThread().setName(nameOfThread);
        Thread.currentThread().setPriority(priorityOfThread);
        for (int i = 0; i < 1000; i++){
            System.out.println(Thread.currentThread().getName() + " " + i);
            System.out.println(Thread.currentThread().getPriority());
        }
    }

    public static void example(String name, int priority){
        (new Thread(new RandomRunExample(name, priority))).start();
    }
}