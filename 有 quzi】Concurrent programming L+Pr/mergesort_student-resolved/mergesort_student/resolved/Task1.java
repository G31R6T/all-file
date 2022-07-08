import java.util.Arrays;

/* Task1: slicing and merging on 1 thread, sorting slices is parralelized */
public class Task1 {

    /* Create new sorted array by merging 2 smaller sorted arrays */
    private static int[] merge(int[] arr1, int[] arr2) {
        // TODO: merge sorted arrays 'arr1' and 'arr2'
        int newLength = arr1.length + arr2.length;

        int[] merge = Arrays.copyOf(arr1, newLength);

        // copy arr2
        for (int i = arr1.length; i < newLength; i++) {
            merge[i] = arr2[i - arr1.length];
        }

        return merge;
    }

    /* Creates an array of arrays by slicing a bigger array into smaller chunks */
    private static int[][] slice(int[] arr, int k) {
        //TODO: cut 'arr' into 'k' smaller arrays
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


    /* Creates a sorted version of any int array */
    public static int[] sort(int[] array) throws InterruptedException {

        /* Initialize variables */
        // TODO: check available processors and create necessary variables
        int pieces = Runtime.getRuntime().availableProcessors();

        /* Turn initial array into array of smaller arrays */
        // TODO: use 'slice()' method to cut 'array' into smaller bits
        int[][] slice = slice(array, pieces);

        /* parralelized sort on the smaller arrays */
        // TODO: use multiple threads to sort smaller arrays (Arrays.sort())
        SortThread[] sortThreads = new SortThread[pieces];
        for (int i = 0; i < pieces; i++) {
            sortThreads[i] = new SortThread(slice[i]);
            sortThreads[i].start();
        }

        // wait sort threads to finish sorting
        for (int i = 0; i < pieces; i++) {
            sortThreads[i].join();
        }

        /* Merge sorted smaller arrays into a singular larger one */
        // TODO: merge into one big array using 'merge()' multiple times
        //       create an empty array called 'sorted' and in a for cycle use
        //       'merge(sorted, arr2d[i])' where arr2d is an array of sorted arrays
        int[] result = new int[0];
        for (int i = 0; i < pieces; i++) {
            result = merge(result, sortThreads[i].getArrToSort());
        }

        /* Return fully sorted array */
        // TODO: return the sorted array and delete all lines starting with '//'
        Arrays.sort(result);
        // if array is very large, no printing
        System.out.println("task1 sorted result:" + Arrays.toString(result));
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
            Arrays.sort(arrToSort);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = {1, 7, 2, 5, 11, 3, 12, 6, 14, 4, 10, 15, 8, 17, 16, 9, 13, 18};
        Task1.sort(arr);
    }
}
