package multithreading.callbackfuture.callable;

import java.util.Random;
import java.util.concurrent.Callable;

public class TaskCallable implements Callable<Integer> {

    private int idThread;

    private Integer count;

    public TaskCallable(int idThread, Integer count) {
        this.idThread = idThread;
        this.count = count;
    }

    @Override
    public Integer call() throws Exception {

        Random random = new Random();

        int nextInt = random.nextInt(10);

        System.out.println("Работает поток: " + this.idThread);

        if(nextInt > 5 ) throw new Exception("Найдено число > 5");

        System.out.println("Рандомное число: " + nextInt);
        return nextInt;
    }
}
