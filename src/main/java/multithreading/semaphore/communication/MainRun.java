package multithreading.semaphore.communication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainRun {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(15);

        Connection connection = Connection.getConnection();

        for (int i = 0; i < 15; i++){

            /*данная переменная должна быть как effective final или final,
            * чтобы можно было использовать ее в лямбда выражении*/
            final int idTread = i;

            executorService.submit(() -> {
                    connection.doWorkWithSemaphore(idTread);

            });
        }

        executorService.shutdown(); /*указщываем,что теперь заполнения пула потоков задачами - закончено*/

        /*установили лимит времени работы данного пула потоков.
        * Ждем один день, чтобы все задачи выполнились ( но конечно все будет гораздо быстрее*/
        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}
