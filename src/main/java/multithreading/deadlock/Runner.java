package multithreading.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static multithreading.deadlock.Account.transferMoney;

public class Runner {

    private Account firstAccount = new Account();

    private Lock lockForFirstAccount = new ReentrantLock();

    private Account secondAccount = new Account();

    private Lock lockForSecondAccount = new ReentrantLock();


    private void takeLocks(Lock lockFirst, Lock lockSecond)  {

        while (true) {
            /*создаем флаги, для сингнализации об успешной взятии блокировки*/
            boolean firstLockTaken = false;

            boolean secondLockTaken = false;
            /**  пытаемся взять блокировку на указанным объектах         */
                firstLockTaken = lockFirst.tryLock();

                secondLockTaken = lockSecond.tryLock();

                /*Данный блок кода проверяет, если блокировка взята успешно,
                 * тогда мы не заходим в блок if(), если же блокировка
                 * не взята, мы проверяем, какой из объектов не отдал ее и тогда
                 * принудительно снимаем*/
                if (firstLockTaken && secondLockTaken) return;

                if (firstLockTaken) lockFirst.unlock();

                if (secondLockTaken) lockSecond.unlock();

            /*если не получилось снять блокировку, тогда поток который исопльзует данный метод - засыпает*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void firstThread() {

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {

            takeLocks(lockForFirstAccount, lockForSecondAccount);

            try {
                /*cумма денег, которую нужно переместить с одного объекта
                 * в другой*/
                int amountForTransfer = random.nextInt(100);

                transferMoney(firstAccount, secondAccount, amountForTransfer);
            } finally {
                lockForFirstAccount.unlock();
                lockForSecondAccount.unlock();
            }

        }
    }

    public void secondThread() {

        Random random = new Random();

        for (int i = 0; i < 10000; i++) {

            takeLocks(lockForSecondAccount, lockForFirstAccount);

            try {
                /*cумма денег, которую нужно переместить с одного объекта
                 * в другой*/
                int amountForTransfer = random.nextInt(100);

                transferMoney(secondAccount, firstAccount, amountForTransfer);
            } finally {
                lockForFirstAccount.unlock();
                lockForSecondAccount.unlock();
            }

        }
    }

    /*после того, как потоки финишировали работу, будем выводит баланс
     * с 2-х счетов.*/
    public void finished() {

        int firstAccountBalance = this.firstAccount.getBalance();

        System.out.println("Баланс счета №1 : " + firstAccountBalance);

        int secondAccountBalance = this.secondAccount.getBalance();

        System.out.println("Баланс счета №2 : " + secondAccountBalance);

        int totalBalance = firstAccountBalance + secondAccountBalance;

        System.out.println("Общий баланс 2-х счетов : " + totalBalance);

    }
}
