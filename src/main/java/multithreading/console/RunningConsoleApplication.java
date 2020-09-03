package multithreading.console;

import multithreading.createthread.firstway.MyThread;
import multithreading.createthread.secondway.Runner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunningConsoleApplication implements CommandLineRunner {

    Logger LOGGER = LoggerFactory.getLogger(RunningConsoleApplication.class);

    public RunningConsoleApplication() {
    }

    @Override
    public void run(String... args) throws Exception {

      //  exampleWorkTwoThreads();

        withRunnable();
    }


    void withRunnable(){

        String message = "Hello from main Thread - .";
        int count = 100;
        Thread thread = new Thread(new Runner(count, message));

        thread.start();

    }



    void exampleWorkTwoThreads() throws InterruptedException {
        String message_1 = "Hello. It is Main thread - 1.";
        String message_2 = "Hello. It is Main thread - 2.";
        int count = 100;

        LOGGER.info("Start the application from Main Tread!!!!");

        Thread thread_1 = new MyThread(message_1, count);

        thread_1.start();

        Thread thread_2 = new MyThread(message_2, count);

        thread_2.start();
    }

}
