package multithreading.сountdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample implements Runnable {

    private CountDownLatch countDownLatch;

    private int idThread;

    public CountDownLatchExample(CountDownLatch countDownLatch,
                                 int idThread) {
        this.countDownLatch = countDownLatch;
        this.idThread = idThread;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Поток ожидает когда счетчик защелки станет = 0...");

        try {
            this.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Поток с id = " + this.idThread + " ... проснулся..");

    }
}
