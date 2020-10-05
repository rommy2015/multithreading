package multithreading.reenrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task {

    private Lock lock = new ReentrantLock();

    private int counter;

    private void createIncrement() {
        for (int i = 0; i < 10000; i++) {
            this.counter++;
        }
    }


    public void firstThread() {
        lock.lock();

        createIncrement();

        lock.unlock();
    }

    public void secondThread() {
        lock.lock();

        createIncrement();

        lock.unlock();
    }

    public void showCounter() {
        System.out.println("Размер счетчика = " + this.counter);
    }
}
