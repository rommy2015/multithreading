package multithreading.waitnotify;

import java.util.Scanner;

public class WaitAndNotify {

    public void producer(){

        synchronized (this){

            System.out.println("Thread producer is started...");

            try {
                /**
                 * 1 - отпускаем монитор (intrinsic lock)
                 * 2 - ждем вызова метода notify() от другого потока
                 */
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread producer is resumed...");
        }

    }

    public void consumer(){

        try {
            /*делаем это для того, чтобы симитировать запуск первого потока,
             * по очереди - первым.*/
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        synchronized (this){
            System.out.println("Waiting for key pressed - Enter...");

            String line = scanner.nextLine();

            this.notify();

            System.out.println("Второй поток,  вызвал метод notify() для данного разделяемого ресурса :");
            System.out.println(         " но второй поток все еще работает с разделяемым ресурсом, хоть и пробудил" +
                    " первый поток.");
        }
    }
}
