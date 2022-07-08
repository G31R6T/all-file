import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

public class Tester {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        // (<number>, <kanjis>)
        final String PATTERN = "^\\d+,\\s+[\u4e00-\u9faf]+$";

        final int FROM = 1;
        final int TO = 99_999;
        final int COUNT = 1000;

        try (PrintWriter outFile = new PrintWriter("out1.txt")) {
            task1(FROM, TO, COUNT, PATTERN, outFile);
        }

        task2(FROM, TO, COUNT, PATTERN);
        task3a(FROM, TO, COUNT);

        final int FROM3b = 0;
        final int TO3b = 99_999_999;
        final int COUNT3b = 1_000_000;
        task3b(FROM3b, TO3b, COUNT3b, PATTERN);
        task3c(FROM3b, TO3b, COUNT3b, PATTERN);
    }

    private static void task1(int from, int to, int count, String pattern, PrintWriter pw) {
        List<String> generated = Task1.generate(from, to, count);
        KanjiLib.assertEquals(generated.size(), count);
        for (String string : generated) {
            KanjiLib.assertTrue(string.matches(pattern));
        }

        generated.forEach(pw::println);
    }

    private static void task2(int from, int to, int count, String pattern) {
        List<String> generated = Task2.generate(from, to, count);
        KanjiLib.assertEquals(generated.size(), count);
        for (String string : generated) {
            KanjiLib.assertTrue(string.matches(pattern));
        }
    }

    private static void task3a(int from, int to, int count) throws InterruptedException {
        Task3 task3 = new Task3(from, to, count);
        List<String> generated = task3.get();
        KanjiLib.assertEquals(generated.size(), count);
    }

    private static void task3b(int from, int to, int count, String pattern) throws InterruptedException {
        Task3 task3 = new Task3(from, to, count);
        task3.interrupt();
        Thread.sleep(1);

        List<String> generated = task3.get();
        KanjiLib.assertTrue(generated.size() <= count);
        for (String string : generated) {
            KanjiLib.assertTrue(string.matches(pattern));
        }
    }

    private static void task3c(int from, int to, int count, String pattern) throws InterruptedException {
        Task3 task3 = new Task3(from, to, count);
        List<Thread> ts = task3.getThreads();
        Random r = new Random();
        ts.get(r.nextInt(ts.size())).interrupt();
        Thread.sleep(1);

        List<String> generated = task3.get();
        KanjiLib.assertTrue(generated.size() <= count);
        for (String string : generated) {
            KanjiLib.assertTrue(string.matches(pattern));
        }
        generated.forEach(System.out::println);
    }
}
