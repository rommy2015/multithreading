package multithreading.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static multithreading.volatilekeyword.UtilsRunVolatile.runThread;

@Component
public class RunningConsoleApplication implements CommandLineRunner {

    Logger LOGGER = LoggerFactory.getLogger(RunningConsoleApplication.class);

    public RunningConsoleApplication() {
    }

    @Override
    public void run(String... args) throws Exception {

        runThread();
        //  exampleWorkTwoThreads();
       // withRunnable();
    }




}
