package multithreading.callbackfuture.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureAndTask {
    public static void main(String[] args) {

        Callable myComputation = () -> 1 + 10;

        FutureTask task = new FutureTask(myComputation);
        Thread thread = new Thread(task);  // это Runnable
        thread.start();

        try {
            Integer result = (Integer) task.get();  // это Future

            System.out.println("Результат вычисления : " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
