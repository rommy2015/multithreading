package multithreading.waitnotify;

public class MainRun {

    public static void main(String[] args) {

        WaitAndNotify waitAndNotify = new WaitAndNotify();

        Thread threadProducer = new Thread(() -> {
            waitAndNotify.producer();
        });

        Thread threadConsumer = new Thread(() -> {
            waitAndNotify.consumer();
        });

        threadProducer.start();
        threadConsumer.start();

        try {
            threadProducer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            threadConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
