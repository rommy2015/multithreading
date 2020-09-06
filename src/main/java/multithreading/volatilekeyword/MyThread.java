package multithreading.volatilekeyword;

public class MyThread extends Thread {

    private String numberThread;

    private volatile boolean isRunning = true;

    public MyThread(String numberThread) {
        this.numberThread = numberThread;
    }

    @Override
    public void run() {

        printAndSleepLoop(numberThread);

    }


    public void setRunningAndShutdown(boolean running) {
        isRunning = running;
    }


    /**
     * в бесконечном цикле печается некоторое
     * привествие, и затем поток засыпает на 100 мсек
     */
    public void printAndSleepLoop(String numberThread){
        while (isRunning){
            System.out.println("Hello, I am thread : " + numberThread);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
