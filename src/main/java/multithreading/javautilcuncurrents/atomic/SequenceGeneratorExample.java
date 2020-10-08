package multithreading.javautilcuncurrents.atomic;

import java.util.List;
import java.util.ArrayList;

import java.math.BigInteger;

class Sequence implements Runnable
{
    Thread thread;
    int id;
    int count;
    boolean printed = false;
    SequenceGenerator sg;
    List<BigInteger> sequence = new ArrayList<BigInteger>();
    
    Sequence(final int id, final int count, SequenceGenerator sg)
    {
        thread = new Thread(this);
        this.count = count;
        this.id    = id;
        this.sg    = sg;
        
        System.out.println("Создан поток " + id);
        thread.start();
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < count; i++) {
            	sequence.add(sg.next());
              Thread.sleep((long) ((Math.random() * 2 + 1) * 30));
            }
        } catch (InterruptedException e) {
            System.out.println("Поток " + id + " прерван");
        }
        System.out.println("Поток " + id + " завершён");
//      printSequence();
    }
    public void printSequence()
    {
    	if (printed)
    		return;
    	String tmp = "[";
    	for (int i = 0; i < sequence.size(); i++) {
    		if (i > 0)
    			tmp += ", ";
    		String nb = String.valueOf(sequence.get(i));
    		while (nb.length() < 9)
    			nb = " " + nb;
    		tmp += nb;
    	}
    	tmp += "]";
    	System.out.println("Последовательность потока " + id + " : " + tmp);
    	printed = true;
    }
}

public class SequenceGeneratorExample
{
	public static void main(String[] args) 
	{
		SequenceGenerator sg = new SequenceGenerator();
		List<Sequence> sequences = new ArrayList<Sequence>();
		for (int i = 0; i < 10; i++) {
			Sequence seq = new Sequence(i, 3, sg);
			sequences.add(seq);
        }
    	System.out.println("\nРасчет последовательностей\n");
    	// Ожидания завершения потоков
		int summa;
		do {
			summa = 0;
			for (int i = 0; i < sequences.size(); i++) {
				if (!sequences.get(i).thread.isAlive()) {
					sequences.get(i).printSequence();
					summa++;
				}
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {}
		} while (summa < sequences.size());
		
		System.out.println("\n\nРабота потоков завершена");
		System.exit(0);
	}
}
