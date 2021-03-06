import java.util.List;
import java.util.Set;

public class Task3 {
    private static final int NUM_THREADS = 10;
    private static final int CHANNEL_CAPACITY = 100;
    private static final int POISON_PILL = -1;
    private static final int MAX_WAIT_SEND_NUM = 100;
    private static final int MAX_WAIT_SEND_ET = 10;

    // TODO Declare a thread-safe data structure for holding result named `generated`.
    // TODO Declare a data structure for holding 10 "B" threads.
    // TODO Declare thread reference for "A".
    // TODO Define a data structure that will be used as a bounded communication channel between threads
    //      the maximal capacity of the channel must be `CHANNEL_CAPACITY`.

    public List<String> get() throws InterruptedException {
        // TODO Wait for all threads to finish ("A" and all "B" threads).
        //      `InterruptedException` should be propagated (not caught).
        // TODO Return a `List` of `String` containing the data from `generated`.
        //      Note: Conversion to plain `List` is needed since `generated` should have some other (thread-safe) type.
        return null;
    }

    public List<Thread> getThreads() {
        // TODO Return the references of the 10 "B" threads.
        return null;
    }

    public void interrupt() {
        // TODO Interrupt the random generation ("A") thread.
    }

    public Task3(final int from, final int to, final int count) {
        if (from < 0 || to < 0 || !isInRange(count, 0, to - from + 1)) throw new IllegalArgumentException();

        // TODO Implement the same logic as in Task2 with the following modifications:

        // TODO If thread "A" is interrupted it should quit sending numbers and
        //      send `POISON_PILL` to others immediately.

        // TODO Random number generation should be implemented in the `generateNum` method.

        // TODO Sending `POISON_PILL`s should be implemented in the `sendPoisonPill` method.

        // TODO Thread "A" should wait up to `MAX_WAIT_SEND_NUM` milliseconds to send a number.
        //      If thread "A" fails to send due to timeout, it should finish immediately.

        // TODO "B" threads should also send `POISON_PILL` when interrupted and then quit.

        // TODO Start threads, but DO NOT wait for them here to finish.
    }

    private void sendPoisonPill() {
        // TODO Send `POISON_PILL` via the channel to each of the 10 "B" threads.
        // TODO After `MAX_WAIT_SEND_ET` time give up and continue to the next thread.
    }

    private int generateNum(int from, int to, Set<Integer> /* Note: Suggested type, can be modified. */ sent) {
        // TODO Move random number generation here.
        return -1; // TODO Return the generated unique random number.
    }

    private static boolean isInRange(int count, int from, int to) {
        return from <= count && count <= to;
    }
}
