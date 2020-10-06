package multithreading.deadlock;

public class MainRun {

    public static void main(String[] args) throws InterruptedException {

        Runner runner = new Runner();


        Thread fistThread = new Thread(() -> {

            runner.firstThread();
        });

        Thread secondThread = new Thread(() -> {
           runner.secondThread();
        });

        fistThread.start();

        secondThread.start();

        fistThread.join();
        secondThread.join();

        /*выполнение финальног кода, после того, как потоки выполнят свои задачи*/
        runner.finished();

    }
}
