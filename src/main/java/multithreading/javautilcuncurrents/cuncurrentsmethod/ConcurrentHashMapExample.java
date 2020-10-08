package multithreading.javautilcuncurrents.cuncurrentsmethod;

public class ConcurrentHashMapExample {

     {
        HashMapExample hashMapExample = new HashMapExample();
        System.out.println("ConcurrentHashMap");
        hashMapExample.createMap(true);
        hashMapExample.addValue (true);

        System.out.println("\n\nHashMap");
        hashMapExample.createMap(false);
        hashMapExample.addValue (false);
    }
}
