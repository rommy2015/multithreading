package multithreading.createthread.secondway;

import static multithreading.utils.OutputStream.printMessage;

public class Runner implements Runnable {

    private int count;

    private String message;

    public Runner(int count, String message) {
        this.count = count;
        this.message = message;
    }

    @Override
    public void run() {
        for(int i = 0; i <= count; i++){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            printMessage(message, i);
        }
    }
}
