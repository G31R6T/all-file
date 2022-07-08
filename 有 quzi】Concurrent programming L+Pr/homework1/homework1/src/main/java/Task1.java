import java.util.Arrays;

/**
 * <p>
 * Test1.java
 * </p>
 */
public class Task1 {

    /**
     * slice big array to smaller ones
     */
    public static int[][] slice(int[] arr, int k) {

        int length = arr.length;
        // the length of  (k-1) arrays
        int elementNumPerArray = length / k;

        // The extra element,add to the last array
        int extraElementNum = length % k;

        int[][] result = new int[k][];

        for (int i = 0; i < k; i++) {
            int[] copy;
            if (i != (k - 1)) {
                copy = Arrays.copyOfRange(arr, i * elementNumPerArray, i * elementNumPerArray + elementNumPerArray);
            } else {
                copy = Arrays.copyOfRange(arr, i * elementNumPerArray, i * elementNumPerArray + elementNumPerArray + extraElementNum);
            }

            result[i] = copy;
        }

        return result;
    }

    /**
     * merge arrays
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        int newLength = arr1.length + arr2.length;

        int[] merge = Arrays.copyOf(arr1, newLength);

        // copy arr2
        for (int i = arr1.length; i < newLength; i++) {
            merge[i] = arr2[i - arr1.length];
        }

        return merge;
    }

    /**
     * sort
     */
    public static int[] sort(int[] array) throws InterruptedException {

        int pieces = Runtime.getRuntime().availableProcessors();

        int[][] slice = slice(array, pieces);

        SortThread[] sortThreads = new SortThread[pieces];
        for (int i = 0; i < pieces; i++) {
            sortThreads[i] = new SortThread(slice[i]);
            sortThreads[i].start();
        }

        // wait sort threads to finish sorting
        for (int i = 0; i < pieces; i++) {
            sortThreads[i].join();
        }

        //merge
        int[] result = new int[0];
        for (int i = 0; i < pieces; i++) {
            result = merge(result, sortThreads[i].getArrToSort());
        }

        // sort
        Arrays.sort(result);

        // if array is very large, no printing
        System.out.println("result:" + Arrays.toString(result));
        return result;
    }

    /**
     * sort thread
     */
    static class SortThread extends Thread implements Runnable {

        // the array to sort
        int[] arrToSort;

        public SortThread(int[] arrToSort) {
            this.arrToSort = arrToSort;
        }

        public int[] getArrToSort() {
            return arrToSort;
        }

        public void setArrToSort(int[] arrToSort) {
            this.arrToSort = arrToSort;
        }

        @Override
        public void run() {
            System.out.println("thread " + Thread.currentThread().getName() + " is sorting array: " + Arrays.toString(arrToSort));
            Arrays.sort(arrToSort);
            System.out.println("thread " + Thread.currentThread().getName() + " sorted: " + Arrays.toString(arrToSort));
        }
    }
}
