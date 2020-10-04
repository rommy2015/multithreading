package multithreading.producerconsumerwithnotify;

@SuppressWarnings("DuplicatedCode")
public class MainRun {

    public static void main(String[] args) {

        ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();

        Thread threadProducer = new Thread(() -> {
            try {
                producerAndConsumer.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread threadConsumer = new Thread(() -> {
            try {
                producerAndConsumer.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadProducer.start();
        threadConsumer.start();

        try {
            threadProducer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            threadConsumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
