package multithreading.callbackfuture.futuretask;

import java.util.concurrent.Callable;

public class MainRun {

    public static void main(String[] args) throws InterruptedException {

        Callable task = () -> "Hello, World!";

        try {
            task.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(10000);

    }
}
