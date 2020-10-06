package multithreading.semaphore.communication;

import java.util.concurrent.Semaphore;

public class Connection {

    /**
     * Модификатор volatile накладывает некоторые дополнительные условия на чтение/запись переменной.
     *
     * Важно понять две вещи о volatile переменных:
     * 1.	Операции чтения/записи volatile переменной являются атомарными.
     * 2.	Результат операции записи значения в volatile переменную
     * одним потоком, становится виден всем другим потокам, которые
     * используют эту переменную для чтения из нее значения.
     *   Потому что для хранения переменной не используется
     *   локальный кэш процессора, а все изменения сбрасываются в основную память.
     */

    private volatile static Connection CONNECTION;

    private int connectionCount;

    final Semaphore semaphore = new Semaphore(10, true);

    private Connection() {
    }

    public static Connection getConnection(){

        if(CONNECTION == null){
            synchronized (Connection.class){
                if(CONNECTION == null){
                    CONNECTION = new Connection();
                }
            }
        }
        return CONNECTION;
    }


    public void doWorkWithSemaphore(int idThread)  {

        try {
            System.out.println("Поток - " + idThread + " - просит место в канале соединения.");

            semaphore.acquire();

            System.out.println("Поток - " + idThread + " - занял место в канале соединения.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doWork(idThread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Поток - " + idThread + " - освободил место в канале соединения.");
            semaphore.release();
        }

    }


    /*в данном методе мы будет проводить некую полезную работу с данным ресурсом (CONNECTION)*/
    public void doWork(int idThread) throws InterruptedException {

        synchronized (this){
            idThread++;
            System.out.println("Номер соединения : " + idThread);
        }

        /*симуляция полезных вычилений, вместо этого может быть вызыван метод,
        * котоырй обрабатывает какой-то запрос*/
        Thread.sleep(5000);

        /**
         * После того, как поток занявший соединение выполнил некоторую полезную работу
         * то мы вычитаем из счетчика 1, что символизирует, что можно осовободить одно
         * место в емкости семафора
         */
      /*  synchronized (this){
            this.connectionCount--;
        }*/
    }



}
