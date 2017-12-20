package pw.artwhite.currentThread;

/**
 * Класс демонстрирует использование
 * методов класса Thread
 * в главном потоке программы
 *
 * @author artwhite
 */

public class CurrentThread{
    public static void main(String[]args){
        // переменная thread ссылается на главный поток программы
        Thread thread = Thread.currentThread();

        System.out.println("Текущий поток: " + thread);
        System.out.println("Имя потока: " + thread.getName());
        System.out.println("Приоритет потока: " + thread.getPriority());
        System.out.println("Группа потока: " + thread.getThreadGroup());
        System.out.println("Идентефикатор потока: " + thread.getId());
        System.out.println("Состояние потока: " + thread.getState());
        thread.setName("Главный поток");
        thread.setPriority(10);
        System.out.println("Теперь текущий поток: " + thread);
        System.out.println("Теперь имя потока: " + thread.getName());

        for (int i = 5; i > 0; i--){
            System.out.println(i);
            try {
                thread.sleep(300);
            } catch (InterruptedException e){

            }System.out.println("Поток завершен");
        }
    }
}

