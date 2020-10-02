package multithreading.synchronizedthreads;

public class Test {

    private int counter;

   /* private synchronized void increment(){
        counter++;
    }
*/
    private void increment(){

        synchronized (this){
            counter++;
        }
    }

    public void doWork() {


        Thread threadFirst = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    increment();

                }
                System.out.println("\n counter after running threadFirst: " + counter);
            }
        });


        Thread threadSecond = new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10000; i++) {
                    increment();
                }


                System.out.println("\n counter after running threadSecond: " + counter);
            }
        });


        System.out.println("\n counter before running threads: " + counter);

        threadFirst.start();

        threadSecond.start();


        try {
            threadFirst.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            threadSecond.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n Итог : " + counter);

    }

}
