package pw.artwhite.hellorunnable;

/**
 * Реализация интерфейса Runnable
 *
 * Интерфейс Runnable определяет один метод run,
 * предназначенный для размещения кода, исполняемого в потоке
 * Runnable - объект пересылается в конструктор Thread
 * и с помощью метода start() поток
 */
public class HelloRunnable implements Runnable{
    public void run() {
        System.out.println("Hello from thread!");
    }

    public static void main(String[] args) {
        (new Thread(new HelloRunnable())).start();
        System.out.println("Hello from main thread");
    }
}
