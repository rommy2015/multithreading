package multithreading.callbackfuture.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunTestCallable {

    public static void main(String[] args)  {

        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

        /*результат выполнения потоков.*/
        List<Future<Integer>> resultList = new ArrayList<>();

        Integer count = 0;

        for(int i = 1; i < 6; i++){

            TaskCallable task = new TaskCallable(i, count);

            Future<Integer> integerFuture  =  executorService.submit(task);

            resultList.add(integerFuture);
        }

        /*устанавливаем время, которое сервис обслуживающий пул потоков будет ждать,
         * пока не выполняться поставленные задачи для потоков.*/
      //  executorService.awaitTermination(1, TimeUnit.DAYS);

        int number = 0;

        for(Future<Integer> result : resultList){
            try {
                number = number + result.get();
            } catch (InterruptedException e) {
                System.out.println("Найдено число > 5 ");
            } catch (ExecutionException e) {
                Throwable cause = e.getCause();
                String localizedMessage = cause.getLocalizedMessage();

                System.out.println("---");
                System.out.println(localizedMessage);
                System.out.println("---");
               }

        }


        executorService.shutdown();/*заканчиваем постановку задач для пула потоков*/

        System.out.println("Результат вычисления потоков : " + number);
    }





}
