package multithreading.javautilcuncurrents.copyonwrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {

    List<String> list;

    List<String> lst;

     {
         lst = new ArrayList<String>();
         lst.add("Java");
         lst.add("J2EE");
         lst.add("J2SE");
         lst.add("Collection");
         lst.add("Concurrent");
    }

    public CopyOnWriteArrayListExample() {

        list = new CopyOnWriteArrayList<String>(lst);

        System.out.println("Первоначальная коллекция : " + list.toString());
        System.out.println();

        System.out.println("ЦИКЛ с изменением");
        printCollection(true);

        System.out.println("\nЦИКЛ без изменением");
        printCollection(false);

    }


    private void printCollection(boolean change){

        System.out.println("Коллекция на текущий момент, перед итерацией : " + list.toString());
        System.out.println();

        Iterator<String> iterator = list.iterator();

        while(iterator.hasNext()){
            String element = iterator.next();

            System.out.printf("  %s %n", element);

            if (change) {
                if (element.equals("Collection")) {
                    list.add("Новая строка");
                    list.remove(element);
                }
            }
        }
    }

    public static void main(String args[]) {
        new CopyOnWriteArrayListExample();
        System.exit(0);
    }
}
