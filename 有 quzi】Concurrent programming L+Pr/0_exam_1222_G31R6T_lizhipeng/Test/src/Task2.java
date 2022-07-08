import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
//lizhipeng G31R6T
public class Task2 {
    private static final int NUM_THREADS = 10;
    private static final int CHANNEL_CAPACITY = 100;
    private static final int POISON_PILL = -1;

    private static List<Integer> uniqueIntList = new ArrayList<>();

    // return a number int
    public static int randomInt(int from,int to){
        int randomNumber = (int) (from + Math.random()*(to-from+1));
        if(!uniqueIntList.contains(randomNumber)){
            uniqueIntList.add(randomNumber);
            return randomNumber;
        }else{
            return randomInt(from,to);
        }
    }

    public static List<String> generate(final int from, final int to, final int count) {
        if (from < 0 || to < 0 || !isInRange(count, 0, to - from + 1)) throw new IllegalArgumentException();

        List<String> generated = new ArrayList<>(count);

        CountDownLatch countDownLatch = new CountDownLatch(11);

        // TODO Define a data structure that will be used as a bounded communication channel between threads
        //      the maximal capacity of the channel must be `CHANNEL_CAPACITY`.
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(CHANNEL_CAPACITY);

        // TODO Create a producer thread (A) that generates `count` random numbers on the
        //      [from, to] interval and sends them to consumers (B) using a bounded channel.

        Thread threadA = new Thread(()->{
            for (int i = 0; i < count-10; i++) {
                int randomInt = randomInt(from,to);
                try {
                    blockingQueue.add(randomInt);
                } catch (Exception e) {
                    System.out.println("arrive CHANNEL_CAPACITY,send failed");
                }

                if((i+1)==(count-10)){
                    for (int j = 0; j < NUM_THREADS; j++) {
                        blockingQueue.add(POISON_PILL);
                    }
                    // count down
                    countDownLatch.countDown();
                }

                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        threadA.start();

        // TODO Random numbers must be unique (use a thread-confined data structure to keep track).
        // TODO This is thread cannot be interrupted.
        // TODO When random number generation ends signal end of transmission to each other thread (B)
        //      using the `POISON_PILL` value.

        // TODO Create `NUM_THREADS` threads. Each thread:
        //      - receives a number from thread A
        //      - if the received number equals `POISON_PILL`, it exits immediately
        //      - converts the received number into kanji using `KanjiLib.convert`
        //      - creates a string "<number>, <kanji>" using the given input and converted string
        //      - puts the string into `generated` (unconditionally)

        for (int i = 0; i < NUM_THREADS; i++) {
            new Thread(() -> {

                while(true){
                    Integer nextInt = blockingQueue.poll();
                    if (nextInt != null && nextInt != POISON_PILL) {

                        String convertString = KanjiLib.convert(nextInt);
                        String resultString = nextInt + "," + convertString;

                        if (!generated.contains(resultString)) {
                            generated.add(resultString);
                        }
                    }else if(nextInt != null && nextInt == POISON_PILL){
                        System.out.println(Thread.currentThread().getName() + " - finished");
                        countDownLatch.countDown();
                        break;
                    }
                }
            }).start();
        }

        // TODO Start the above threads (thread A and threads B, 11 overall).


        // TODO Wait for each thread to finish.
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return generated;
    }

    private static boolean isInRange(int count, int from, int to) {
        return from <= count && count <= to;
    }
}