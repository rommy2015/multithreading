package multithreading.createthread.firstway;

import org.springframework.stereotype.Component;

import static multithreading.utils.OutputStream.printMessage;

@Component
public class MyThread extends Thread {

    @Override
    public void run() {

        String message = "Hello. It is a thread.";
        int count = 1000;
        printMessage(message, count);
    }

}
