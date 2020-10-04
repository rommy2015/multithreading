package multithreading.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainRun {

    private static BlockingQueue blockingQueue = new ArrayBlockingQueue(10);


    public static void main(String[] args) {

        Thread producerThread = new Thread(() -> {
             producer();
        });

        Thread consumerThread = new Thread(() -> {
            consumer();
        });

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*данный метод будет имитировать работу потока-производителя*/
    private static void producer(){

        Random random = new Random();

        while (true){

            try {
                blockingQueue.put(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*данный метод будет имитировать работу потока-потребителя, который будет брать элементы,
    * из очерди*/
    private static void consumer(){

        Random random = new Random();

        while (true){
            try {

                Thread.sleep(100);

                int nextInt = random.nextInt(10);

                if(nextInt == 5){
                    Integer currentElementFromQueue = (Integer) blockingQueue.take();

                    System.out.println(currentElementFromQueue);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println("Размер очереди : " + blockingQueue.size());
        }

    }
}
