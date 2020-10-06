package multithreading.semaphore.parking;

import static multithreading.semaphore.parking.ParkingRun.PARKING_PLACES;
import static multithreading.semaphore.parking.ParkingRun.SEMAPHORE;

public class Car implements Runnable {

    private int carNumber;

    public Car(int carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public void run() {

        System.out.printf("Автомобиль №%d подъехал к парковке.\n", this.carNumber);

        /* acquire() запрашивает доступ к следующему за вызовом этого метода блоку кода,
        если доступ не разрешен, поток вызвавший этот метод блокируется до тех пор,
        пока семафор не разрешит доступ
         */

        try {
            SEMAPHORE.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int parkingNumber = -1;

        /*Ищем свободное место и паркуемся */

        synchronized (PARKING_PLACES){
            for (int i = 0; i < 5; i++)
                if (!PARKING_PLACES[i]) {      //Если место свободно
                    PARKING_PLACES[i] = true;  //занимаем его
                    parkingNumber = i;         //Наличие свободного места, гарантирует семафор
                    System.out.printf("Автомобиль №%d припарковался на месте %d.\n", carNumber, i);
                    break;
                }
        }

        try {
            /*Уходим за покупками, к примеру*/
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (PARKING_PLACES) {
            PARKING_PLACES[parkingNumber] = false; //Освобождаем место
        }

        /* release(), напротив, освобождает ресурс*/
        SEMAPHORE.release();
        System.out.printf("Автомобиль №%d покинул парковку.\n", carNumber);


    }
}
