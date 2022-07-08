import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
//Lizhipeng G31R6T 
public class Task1 {
    private static final int NUM_THREADS = 10;

    public static List<String> generate(final int from, final int to, final int count) {
        if (from < 0 || to < 0 || !isInRange(count, 0, to - from + 1)) throw new IllegalArgumentException();

        List<String> generated = new ArrayList<>(count);

        // TODO Create `NUM_THREADS` threads.
        // TODO Each thread:
        //      - generates a random number in the [from, to] interval
        //      - converts it into kanji using `KanjiLib.convert`
        //      - creates a string "<number>, <kanji>" using the given input and converted string
        //      - if `generated` has size equal to `count`, it exits immediately
        //      - puts the string into `generated` if it is not already present
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Object lock = "lock";
        for (int i = 0; i < NUM_THREADS; i++) {

            // TODO Start the above threads.
            new Thread(() -> {
                while (true) {

                    int randomNumber = (int) (from + Math.random() * (to - from + 1));
                    String convertString = KanjiLib.convert(randomNumber);

                    String resultString = randomNumber + ", " + convertString;

                    synchronized (lock){
                        if (generated.size() >= count) {
                            countDownLatch.countDown();
                            break;

                        } else {
                            if (!generated.contains(resultString)) {
                                generated.add(resultString);
                            }
                        }
                    }
                }
            }).start();

        }

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
