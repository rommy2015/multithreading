package multithreading.volatilekeyword;

import java.util.Scanner;

public class UtilsRunVolatile {

    public static void runThread(){

        MyThread myThread = new MyThread("1");
        myThread.start();

        /**
         * этот кусок кода выполняется в главном потоке
         * как только объект типа Scanner, тогда консоль ожидает
         * нажатия любой клавиши, как только мы нажали какую-то клавишу,
         * главный поток начинает выполнять свои команды,пока в это время
         * работает дочерний поток
         */
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        /**
         * теперь главный поток, сообщает дочернему потоку, что нужно
         * поменять значение переменной, которая запустила цикл, а это в
         * свою очередь остановить дочерний поток.
          */
        myThread.setRunningAndShutdown(false);

    }
}

