import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private static final int NUM_THREADS = 10;
    private static final int CHANNEL_CAPACITY = 100;
    private static final int POISON_PILL = -1;

    public static List<String> generate(final int from, final int to, final int count) {
        if (from < 0 || to < 0 || !isInRange(count, 0, to - from + 1)) throw new IllegalArgumentException();

        List<String> generated = new ArrayList<>(count);

        // TODO Define a data structure that will be used as a bounded communication channel between threads
        //      the maximal capacity of the channel must be `CHANNEL_CAPACITY`.

        // TODO Create a producer thread (A) that generates `count` random numbers on the
        //      [from, to] interval and sends them to consumers (B) using a bounded channel.
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

        // TODO Start the above threads (thread A and threads B, 11 overall).


        // TODO Wait for each thread to finish.

        return generated;
    }

    private static boolean isInRange(int count, int from, int to) {
        return from <= count && count <= to;
    }
}
