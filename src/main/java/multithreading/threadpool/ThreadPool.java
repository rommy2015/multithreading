package multithreading.threadpool;

public class ThreadPool implements Runnable{

    private int id;

    public ThreadPool(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("This task with id : " + this.id + " is done.");

    }
}
