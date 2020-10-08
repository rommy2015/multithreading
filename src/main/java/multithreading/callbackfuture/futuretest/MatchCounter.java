package multithreading.callbackfuture.futuretest;

import javax.imageio.stream.FileImageInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class MatchCounter implements Callable {

    private File directory;
    private String keyword;
    private int count;

    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }


    /**
     * Ищем в файле заданное ключевое слово.
     * @param file Файл, в котором идет поиск
     * return true, если слово найдено в файле
     */
    public boolean search(File file)  {

        boolean found = false;

        FileReader fileReader = null;
        try {
             fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner input = new Scanner(fileReader);

            /*как только к файлу был получен доступ, файл сканируется на предмет заданного
            * слова*/
            while(!found && input.hasNextLine()) {
                String line = input.nextLine();
                if(line.contains(keyword)) return true;
            }
        return false;
    }

   /*запустить поток для поиска файлов и подсчитать количество файлов, в которых найдено будет
   заданное слово.
   *  Внутри метода call() мы используем тот же механизм рекурсивно.
   * Для каждого подкаталога создадим новый MathCounter и
   * запустим поток для него.
   * Также мы спрячем объект FutureTask в ArrayList<Future>. */
   public List <Future> performCountingViewedFiles(){

       File[] files = this.directory.listFiles();

       List <Future> results = new ArrayList();

       for(File file : files)

           if(file.isDirectory())  {
               System.out.println("Найдена директория : " + file.getAbsolutePath());
               System.out.println();
               MatchCounter counter = new MatchCounter(file, keyword);
               System.out.println("Рекурсивно задаем поиск в поддиректории");
               System.out.println();
               FutureTask task = new FutureTask(counter);
               results.add(task);
               Thread thread = new Thread(task);
               thread.start();
           } else {
               if(search(file)) count++;
           }

       return results;
   }

    /*запустить поток для поиска файлов и подсчитать.
   *  Внутри метода call() Все потоки, которые работали рекурсивно, . */
    @Override
    public Integer call() throws Exception {

        List<Future> futureResultList = performCountingViewedFiles();

        for(Future result : futureResultList){
            count += (Integer) result.get();
        }

        System.out.println("На текущий момент найдено файлов : " + this.count);
        System.out.println();
        System.out.println("Было запущено задач паралельно : " + futureResultList.size());
        return this.count;
    }

}


