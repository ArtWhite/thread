package pw.artwhite.eggOrChicken;

/**
 * Created by artwhite on 29/12/2017.
 */
public class EggOrChicken extends Thread {

    public void run() {
        for (int i = 0; i < 100; i++) {
            setPriority(1);
            try {
                sleep(200);        //Приостанавливает поток на 1 секунду
            } catch (InterruptedException e) {
            }
            System.out.println("яйцо - " + i);
        }
    }

    public static void main(String[] args) {
        EggOrChicken eggOrChicken = new EggOrChicken();//Создание потока
        EggOrChicken eggOrChicken1 = new EggOrChicken();
        Thread thread = Thread.currentThread();
        thread.setPriority(10);

        System.out.println("Спор начат...");
        eggOrChicken.start(); //Запуск потока

        for(int i = 0; i < 100; i++) {
            try{
                Thread.sleep(200);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}

            System.out.println("курица - " + i);
        }

        //Слово «курица» сказано 5 раз

        if(eggOrChicken.isAlive()){	//Если оппонент еще не сказал последнее слово

            try{
                eggOrChicken.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException e){}

            System.out.println("Первым появилось яйцо");
        }
        else{	//если оппонент уже закончил высказываться
            System.out.println("Первой появилась курица");
        }
        System.out.println("Спор завершён");
    }
}