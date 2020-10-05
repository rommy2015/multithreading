package multithreading.сountdownlatch.racecar;

import static multithreading.сountdownlatch.racecar.RaceRun.START;
import static multithreading.сountdownlatch.racecar.RaceRun.TRACK_LENGTH;

public class Car implements Runnable {

    private int carNumber;

    /*считаем, что скорость автомобиля постоянная*/
    private int carSpeed;

    public Car(int carNumber, int carSpeed) {
        this.carNumber = carNumber;
        this.carSpeed = carSpeed;
    }

    @Override
    public void run() {

        try {
            System.out.printf("Автомобиль №%d подъехал к стартовой прямой.\n", carNumber);

            /*Автомобиль подъехал к стартовой прямой - условие выполнено
            уменьшаем счетчик на 1*/
            START.countDown();

            /*метод await() блокирует поток, вызвавший его, до тех пор, пока
            счетчик CountDownLatch не станет равен 0*/
            START.await();

            /*ждем пока проедет трассу*/
            Thread.sleep(TRACK_LENGTH / carSpeed);
            System.out.printf("Автомобиль №%d финишировал!\n", carNumber);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
