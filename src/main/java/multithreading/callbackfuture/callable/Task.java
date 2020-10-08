package multithreading.callbackfuture.callable;

public class Task implements Runnable {

    private int idThread;

    public Task(int idThread) {
        this.idThread = idThread;
    }

    @Override
    public void run() {
        System.out.println("Поток №: " + this.idThread);
        try {

            System.out.println("Поток заснул");
            Thread.sleep(3000);
            System.out.println("Поток № :" + this.idThread + ",  проснулся");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RunTest.result++;
    }
}
