package multithreading.сountdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRun {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i = 0; i < 3; i++){

            CountDownLatchExample countDownLatchExample =
                    new CountDownLatchExample(countDownLatch);

            executorService.submit(countDownLatchExample);
        }

        executorService.shutdown(); /*обязательно нужно вызывать данный метод,
        чтобы прекратить исопльзование текущего пула потоков и постановку задач им*/

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Защелка от класса CountDownLatch" +
                " была открыта и главный поток продолжает работу...");
    }

}
