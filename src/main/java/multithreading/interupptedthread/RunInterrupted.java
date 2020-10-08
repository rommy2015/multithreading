package multithreading.interupptedthread;

import java.util.Random;

public class RunInterrupted {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        Thread thread = new Thread(() -> {

            for(int i = 0 ; i < 1000_000_000; i++ ){

                boolean isInterrupted = Thread.currentThread().isInterrupted();

                if(isInterrupted) {
                    System.out.println("Текущему потоку поступила команда - остановиться.");
                    return;
                }

                double nextDouble = random.nextDouble();

                Math.sin(nextDouble);
            }
        });

        System.out.println("Поток запускается.");
        System.out.println();

        thread.start();

        Thread.sleep(1000);

        thread.interrupt();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        System.out.println("Поток закончил свою работу.");

    }
}
