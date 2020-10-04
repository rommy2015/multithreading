package multithreading.producerconsumerwithnotify;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

@SuppressWarnings("DuplicatedCode")
public class ProducerAndConsumer {

    private Queue<Integer> queue = new LinkedList<>();

    private final int LIMIT_QUEUE = 10; /*очередь на 10 элеметов*/

    private final Object lock = new Object();

    public void producer() throws InterruptedException {

        int value = 0;
        /**
         * Метод offer (E e) интерфейса очереди
         * вставляет указанный элемент в эту очередь,
         * если это можно сделать немедленно,
         * не нарушая ограничения по емкости.
         * Этот метод предпочтительнее метода add (),
         * так как этот метод не выдает исключение,
         * когда емкость контейнера заполнена, так как он возвращает false.
         */
          System.out.println("Thread-Producer is started...");

            while (true){
                synchronized (lock){

                     /**
                       * 1 - отпускаем монитор (intrinsic lock)
                       * 2 - ждем вызова метода notify() от другого потока
                      * queue.size() == LIMIT_QUEUE - данное условие говорит о том,
                      * что нужно запусить цикл, который будет вызывать метод wait() на
                      * указанном объекте (мы сделали фиктивный объект) и елси очередь полная,
                      * тогда поток-producer засыпает и не будет добавлять элементы.
                      **/
                    while (queue.size() == LIMIT_QUEUE){
                        lock.wait();
                    }

                    queue.offer(value++);

                    System.out.println("Thread-Producer is resumed and has added an element...");

                    lock.notify();

                    System.out.println("Thread-Producer awaken the Thread-Consumer...");
                }
            }
    }

    public void consumer() throws InterruptedException {

        while (true){
            synchronized (lock){

                /**
                 * если очередь пустая, тогда вызывает метод wait(), чтобы поток-consumer уснул
                 */
                while (queue.size() == 0){
                    lock.wait();
                }

                int valueGiven = queue.poll();

                System.out.println("Получено значение из очереди ... :" + valueGiven);

                lock.notify();

                System.out.println("Второй поток,  вызвал метод notify() для данного разделяемого ресурса :");
                System.out.println(         " но второй поток все еще работает с разделяемым ресурсом, хоть и пробудил" +
                        " первый поток.");
            }

            Thread.sleep(1000);
        }

    }
}
