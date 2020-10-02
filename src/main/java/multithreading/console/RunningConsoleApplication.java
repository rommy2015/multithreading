package multithreading.console;

import multithreading.synchronizedthreads.Test;
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


        mainStart();
       // runThread();
        //  exampleWorkTwoThreads();
       // withRunnable();
    }


    public void mainStart(){

        Test test = new Test();

        test.doWork();

    }

}
