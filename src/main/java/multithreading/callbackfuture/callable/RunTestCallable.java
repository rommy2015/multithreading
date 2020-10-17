package multithreading.callbackfuture.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RunTestCallable {

    public static void main(String[] args) {

        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

        /*результат выполнения потоков.*/
        List<Future<Integer>> resultList = new ArrayList<>();

        Integer count = 0;

        for (int i = 1; i < 6; i++) {

            TaskCallable task = new TaskCallable(i, count);

            Future<Integer> integerFuture = executorService.submit(task);

            resultList.add(integerFuture);
        }

        /*устанавливаем время, которое сервис обслуживающий пул потоков будет ждать,
         * пока не выполняться поставленные задачи для потоков.*/
        //  executorService.awaitTermination(1, TimeUnit.DAYS);


        int number = 0;

        /*получаем значния из коллекции   List<Future<Integer>> resultList
         и передаем эти значения в коллекцию List<Integer> list.  */
        List<Integer> integerList = resultList.stream().map(integerFuture -> {
            List<Integer> list = new ArrayList<>();
            try {
                Integer integer = integerFuture.get();
                list.add(integer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            return list;
        })
                .flatMap(List::stream)
                .collect(Collectors.toList());

        /*Производим суммирование значений всех элементов из коллекции List<Integer> list */
        int sum = integerList
                .stream()
                .flatMapToInt(IntStream::of)
                .sum();

        System.out.println("sum значений элементов в коллекции: " + sum);

        System.out.println(integerList);


        for (Future<Integer> result : resultList) {
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
