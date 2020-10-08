package multithreading.javautilcuncurrents.cuncurrentsmethod;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

public class CollectionsConcurrent {

    public static void main(String[] args) {
        // printCar();

        // fillCollection();
        travers();
    }

    private static void travers() {
        List list = new ArrayList<Integer>();
        list = Collections.synchronizedList(list);

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        synchronized (list) {

            /* печать значений коллекции*/
            try {
                for (Iterator i = list.iterator(); i.hasNext(); ) {

                    System.out.println(i.next().toString());
                }
            } catch (ConcurrentModificationException e) {
            }
        }

    }

    private static void fillCollection() {

        ListElem listElem = new ListElem();
        List<Integer> elemList = listElem.getList();

        for (int i = 1; i < 10; i++) {
            elemList.add(i);
        }

        Thread firstThread = new Thread(() -> {

            listElem.outputCollection();

        });

        Thread secondThread = new Thread(() -> {

            listElem.removeElements();

        });

        firstThread.start();
        secondThread.start();


        try {
            firstThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void printCar() {
        Car car = new Car();

        for (int i = 0; i < 20; i++) {

            String generatedString = RandomStringUtils.randomAlphabetic(10);
            String id = String.valueOf(i);

            car.addValue(generatedString, id);
        }

        System.out.println(car);
    }

}
