package multithreading.callbackfuture.futuretest;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureRun {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Укажите базовый каталог (например, c:\\dir): ");
        String directory = in.nextLine();
        System.out.print("Введите ключевое слово (например, volatile): ");
        String keyword = in.nextLine();

        System.out.println("Запуск задачи в точке входа:");
        MatchCounter counter = new MatchCounter(new File(directory), keyword);
        FutureTask task = new FutureTask(counter);
        Thread thread = new Thread(task);
        thread.start();
        System.out.println();

        try {
            System.out.println();
            System.out.println(task.get() + " файлов найдено.");
            System.out.println();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        }
    }




}
