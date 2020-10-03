package multithreading.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainRun {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++){

            ThreadPool newTask = new ThreadPool(i);

            executorService.submit(newTask);
        }

        /*указывает на то, что передача заданий закончена
        и нужно запускать выполенение переданных задач*/
        executorService.shutdown();

        System.out.println("Передача заданий была закончена и задания начинают выполняться : ");
        System.out.println();

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
