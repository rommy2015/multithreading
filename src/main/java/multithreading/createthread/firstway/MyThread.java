package multithreading.createthread.firstway;

import static multithreading.utils.OutputStream.printMessage;

public class MyThread extends Thread {

    private String message;

    private int count;

    public MyThread(String message, int count) {
        this.message = message;
        this.count = count;
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
