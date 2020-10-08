package multithreading.callbackfuture.callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RunTest {

    public static int result;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for(int i = 0; i < 5; i++){
            Task task = new Task(i);
            executorService.submit(task);
        }

        executorService.shutdown();/*заканчиваем постановку задач для пула потоков*/

        /*устанавливаем время, которое сервис обслуживающий пул потоков будет ждать,
        * пока не выполняться поставленные задачи для потоков.*/
        executorService.awaitTermination(1, TimeUnit.DAYS);

        System.out.println("Результат вычислений. : " + result);

    }
}
