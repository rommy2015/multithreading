package multithreading.сountdownlatch.racecar;

import java.util.concurrent.CountDownLatch;

public class RaceRun {

    /*Создаем CountDownLatch на 8 "условий"*/
    public static final CountDownLatch START = new CountDownLatch(8);

    /*Условная длина гоночной трассы*/
    public static final int TRACK_LENGTH = 500000;



    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 5; i++) {

            int carSpeed = (int) (Math.random() * 100 + 50);

            Car car = new Car(i, carSpeed);

            Thread thread = new Thread(car);

            thread.start();

            Thread.sleep(1000);
        }

        /*Проверяем, собрались ли все автомобили,
        * у стартовой прямой. Если нет, ждем 100ms*/
        while (START.getCount() > 3){
            Thread.sleep(100);
        }


        Thread.sleep(1000);
        System.out.println("На старт!");

        /*Команда дана, уменьшаем счетчик на 1*/
        START.countDown();

        Thread.sleep(1000);
        System.out.println("Внимание!");

        /*Команда дана, уменьшаем счетчик на 1 */
        START.countDown();


        Thread.sleep(1000);
        System.out.println("Марш!");

        /* Команда дана, уменьшаем счетчик на 1*/
        START.countDown();

        /*счетчик становится равным нулю, и все ожидающие потоки
        одновременно разблокируются*/
    }


}
