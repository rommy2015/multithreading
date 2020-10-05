package multithreading.сountdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample implements Runnable {

    private CountDownLatch countDownLatch;

    public CountDownLatchExample(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Событие сработало для потока...");
        this.countDownLatch.countDown();


    }
}
