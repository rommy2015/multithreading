package multithreading.synchronizedthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    Random random = new Random();

    private List<Integer> listFirst = new ArrayList<>();

    private List<Integer> listSecond = new ArrayList<>();

    private Object lockMonitorFirst = new Object();

    private Object lockMonitorSecond = new Object();

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
    private synchronized void addToListFirst(){
        synchronized (lockMonitorFirst){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int nextInt = random.nextInt(100);
            this.listFirst.add(nextInt);
        }

    }

    private  void addToListSecond(){
        synchronized (lockMonitorSecond){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int nextInt = random.nextInt(100);
            this.listSecond.add(nextInt);
        }

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
