package multithreading.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static multithreading.utils.OutputStream.printMessage;

@Component
public class RunningConsoleApplication implements CommandLineRunner {


    Logger LOGGER = LoggerFactory.getLogger(RunningConsoleApplication.class);

    private Thread thread;

   @Autowired
    public RunningConsoleApplication(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run(String... args) throws Exception {

        String message = "Hello. It is Main thread - 1.";
        int count = 1000000;

        printMessage(message, count);

        LOGGER.info("Start the application from Main Tread!!!!");




        thread.start();



    }
}
