package multithreading.deadlock;

/*Эмуляция с разными счетами*/
public class Account {

    /*при создании объект данного класса, на балансе банковского счета
    * будет находится 10 000 р.*/
    private int balance = 10000;

    /*c помощью даннога метода можно будет пополянть баланс данного счета */
    public void deposit(int amount){

        this.balance += amount;
    }


    /*списаине денег с баланса*/
    public void writeOffMoneyFromBalance(int amount){
        this.balance -= amount;
    }

    /*получение текущего баланса*/
    public int getBalance(){
        return this.balance;
    }

    /*Перемещение денег с одного счета, на другой счет*/
    public static void transferMoney(Account firstAccount, Account secondAccount, int amount ){

        firstAccount.writeOffMoneyFromBalance(amount);/*списали деньги*/

        secondAccount.deposit(amount); /*ранее списанные деньги, положили на указанный счет*/
    }
}
