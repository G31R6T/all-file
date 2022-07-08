import org.junit.Test;

import java.util.Arrays;

/**
 * <p>
 * Tester.java
 * </p>
 */
public class Tester {

    /**
     * test of task1
     */
    @Test
    public void testTask1() throws InterruptedException {
        int[] arr = {1, 7, 2, 5, 11, 3, 12, 6, 14, 4, 10, 15, 8, 17, 16, 9, 13, 18};
        Task1.sort(arr);
    }

    /**
     * test of task2
     */
    @Test
    public void testTask2() throws InterruptedException {
        int[] ints = {3, 1, 2, 11, 9, 4, 6, 10, 5, 7, 8, 12,13};
        System.out.println("array after sorted: " + Arrays.toString(Task2.sort(ints)));

    }
}
