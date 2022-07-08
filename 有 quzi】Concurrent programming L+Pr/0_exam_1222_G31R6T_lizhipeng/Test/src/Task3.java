import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task3 {
    private static final int NUM_THREADS = 10;
    private static final int CHANNEL_CAPACITY = 100;
    private static final int POISON_PILL = -1;
    private static final int MAX_WAIT_SEND_NUM = 100;
    private static final int MAX_WAIT_SEND_ET = 10;
//LIZHIPENG G31R6T
    // TODO Declare a thread-safe data structure for holding result named `generated`.
    // TODO Declare a data structure for holding 10 "B" threads.
    // TODO Declare thread reference for "A".
    // TODO Define a data structure that will be used as a bounded communication channel between threads
    //      the maximal capacity of the channel must be `CHANNEL_CAPACITY`.

    public List<String> get() throws InterruptedException {
        // TODO Wait for all threads to finish ("A" and all "B" threads).
        threadA.join();

        for (Thread bThread:bThreads){
            bThread.join();
        }
        //      `InterruptedException` should be propagated (not caught).
        // TODO Return a `List` of `String` containing the data from `generated`.
        //      Note: Conversion to plain `List` is needed since `generated` should have some other (thread-safe) type.
        return generated;
    }

    List<Thread> bThreads;
    public List<Thread> getThreads() {
        // TODO Return the references of the 10 "B" threads.
        return bThreads;
    }

    public void interrupt() {
        // TODO Interrupt the random generation ("A") thread.
        threadA.interrupt();
    }

    private static BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(CHANNEL_CAPACITY);
    private static List<String> generated;
    private static Thread threadA = null;
    public Task3(final int from, final int to, final int count) {
        if (from < 0 || to < 0 || !isInRange(count, 0, to - from + 1)) throw new IllegalArgumentException();

        // TODO Implement the same logic as in Task2 with the following modifications:

        Set<Integer> set = new HashSet<>();

        generated = new ArrayList<>(count);
        threadA = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                if (!Thread.currentThread().isInterrupted()) {
                    int randomInt = generateNum(from, to, set);
                    boolean add = blockingQueue.add(randomInt);

                    // failed for timeout, finish immediately
                    if (!add) {
                        break;
                    }
                }else{
                    sendPoisonPill();
                }

                try {
                    Thread.sleep(MAX_WAIT_SEND_NUM);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (i == count) {
                    blockingQueue.add(POISON_PILL);
                }
            }

        });


        threadA.start();

        // TODO If thread "A" is interrupted it should quit sending numbers and
        //      send `POISON_PILL` to others immediately.


        // TODO Random number generation should be implemented in the `generateNum` method.

        // TODO Sending `POISON_PILL`s should be implemented in the `sendPoisonPill` method.

        // TODO Thread "A" should wait up to `MAX_WAIT_SEND_NUM` milliseconds to send a number.
        //      If thread "A" fails to send due to timeout, it should finish immediately.

        // TODO "B" threads should also send `POISON_PILL` when interrupted and then quit.

        // TODO Start threads, but DO NOT wait for them here to finish.
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread threadB = new Thread(() -> {
                if (!Thread.currentThread().isInterrupted()) {
                    int nextInt = blockingQueue.poll();

                    while (nextInt != POISON_PILL) {

                        String convertString = KanjiLib.convert(nextInt);
                        String resultString = nextInt + "," + convertString;

                        if (!generated.contains(resultString)) {
                            generated.add(resultString);
                        }
                    }
                } else {
                    //interrupted
                    blockingQueue.add(POISON_PILL);
                }


            });
            bThreads.add(threadB);
            threadB.start();
        }
    }

    private void sendPoisonPill() {
        // TODO Send `POISON_PILL` via the channel to each of the 10 "B" threads.
        // TODO After `MAX_WAIT_SEND_ET` time give up and continue to the next thread.
        for (int i = 0; i < NUM_THREADS; i++) {
            blockingQueue.add(POISON_PILL);
            try {
                Thread.sleep(MAX_WAIT_SEND_ET);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int generateNum(int from, int to, Set<Integer> /* Note: Suggested type, can be modified. */ sent) {
        // TODO Move random number generation here.
        int randomNumber = (int) (from + Math.random()*(to-from+1));
        if(!sent.contains(randomNumber)){
            sent.add(randomNumber);
            return randomNumber;
        }else{
            return generateNum(from,to,sent);
        }
    }

    private static boolean isInRange(int count, int from, int to) {
        return from <= count && count <= to;
    }
}
