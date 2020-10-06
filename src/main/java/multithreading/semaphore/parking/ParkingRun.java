package multithreading.semaphore.parking;

import java.util.concurrent.Semaphore;

public class ParkingRun {

    /*   Парковочное место занято - true, свободно - false*/
    public static final boolean[] PARKING_PLACES = new boolean[5];

    /*   Устанавливаем флаг "справедливый", в таком случае метод
    aсquire() будет раздавать разрешения в порядке очереди */
    public static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 1; i <= 7; i++) {

            Car car = new Car(i);

            Thread thread = new Thread(car);

            thread.start();

            Thread.sleep(400);
        }

    }



}
