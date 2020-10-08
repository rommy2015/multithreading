package multithreading.javautilcuncurrents.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public class ArraySetExample {

    List<User> list  ;
    CopyOnWriteArraySet<User> cowSet;

    {
        list = new ArrayList<User>();
        list.add(new User ("Прохор "));
        list.add(new User ("Георгий"));
        list.add(new User ("Михаил" ));
    }

    class User {
        private String name;
        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    public ArraySetExample() {

        cowSet = new CopyOnWriteArraySet<User>(list);

        System.out.println("Первоначальные данные коллекции 'cowSet' :" + cowSet.toString());
        System.out.println();

        System.out.println("Цикл с измением");

        Iterator<User> itr = cowSet.iterator();
        int count = 0;

        while (itr.hasNext()) {
            User user = itr.next();
            System.out.println("  " + user.name);

            if (++count == 2) {
                cowSet.add(new User("Павел"));
                user.name += " Иванович";
            }
        }

        System.out.println("Данные коллекции 'cowSet' (после первоначального изменения) :" + cowSet.toString());
        System.out.println();
        System.out.println("\nЦикл без измения");

        itr = cowSet.iterator();

        while (itr.hasNext()) {
            User user = itr.next();
            System.out.println("  " + user.name);
        }
    }


    public static void main(String args[])    {
        new ArraySetExample();
    }
}
