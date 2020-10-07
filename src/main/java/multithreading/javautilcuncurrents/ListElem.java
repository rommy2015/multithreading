package multithreading.javautilcuncurrents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListElem {

    private List <Integer> list = new ArrayList();

    public List<Integer> getList() {
        return list;
    }

    /* печать значений коллекции */
    public void outputCollection(){

        list = Collections.synchronizedList(list);

        synchronized (list){
            for (int i = 0; i < list.size() ; i++) {

                System.out.println(list.get(i).toString());
            }
        }
    }

    public void removeElements(){

        list.remove(2);
    }



}
