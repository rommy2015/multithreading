package multithreading.synchronizedthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerSecond {

    Random random = new Random();

    private List<AtomicInteger> listFirst = new ArrayList<>();

    private List<AtomicInteger> listSecond = new ArrayList<>();


    public void addToListElements(){

        for (int i = 0; i < 1000; i++){
            addToListFirst();
            addToListSecond();
        }
    }


    /**
     * перед тем, как добавить элемент в массив, поток будет засыпать
     * на 1 миллисекунду, при работе с указанным объектом.
     */
    private  void addToListFirst(){

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int nextInt = random.nextInt(100);
        AtomicInteger atomicInteger1 = new AtomicInteger(10);
        this.listFirst.add(atomicInteger1);

    }

    private synchronized void addToListSecond(){

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int nextInt = random.nextInt(100);
        AtomicInteger atomicInteger1 = new AtomicInteger(10);
        this.listSecond.add(atomicInteger1);

    }


    public void main(){

        long before = System.currentTimeMillis();


        Thread threadFirst = new Thread(() ->{
            addToListElements();
        });

        Thread threadSecond = new Thread(() ->{
            addToListElements();
        });

        threadFirst.start();
        threadSecond.start();

        try {
            threadFirst.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            threadSecond.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Первая коллекция : " + listFirst.size());

        System.out.println("Вторая коллекция : " + listSecond.size());

        long after = System.currentTimeMillis();

        long differenceTime = after - before;

        System.out.println("Время обработки запроса:" + differenceTime);

    }
}
