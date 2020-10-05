package multithreading.reenrantlock;

public class MainRun {

    public static void main(String[] args) throws InterruptedException {

        Task task = new Task();

        Thread firstThread = new Thread(() -> {
            task.firstThread();
        });

        Thread secondThread = new Thread(() -> {
            task.secondThread();
        });

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        task.showCounter();
    }
}
