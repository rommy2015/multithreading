package multithreading.javautilcuncurrents.cuncurrentsmethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Car {

    private String id;

    public Car() {
    }

    public Car(String  id) {
        this.id = id;
    }

    HashMap<String, String> hm = new HashMap<>();
    Map<String, String> map = Collections.synchronizedMap(hm);


    public void addValue(String key, String value) {
        synchronized (map){
            if (!map.containsKey(key))
                map.put(key, value);
        }

    }


    @Override
    public String toString() {
        return "Car{" +
                "map=" + map +
                '}';
    }
}
