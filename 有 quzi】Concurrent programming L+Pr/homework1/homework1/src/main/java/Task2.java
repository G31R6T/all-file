import java.util.Arrays;

/**
 * <p>
 * Test2.java
 * </p>
 */
public class Task2 {

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
     * require that cup number is 2^N
     */
    public static int[] sort(int[] array) throws InterruptedException {

        // get number of processors
        int pieces = Runtime.getRuntime().availableProcessors();

        // the tree deep
        int maxDeep = 0;

        // compute deep
        for (int i = 1; Math.pow(2, i) <= pieces; i++) {
            if (Math.pow(2, i) == pieces) {
                maxDeep = i + 1;
                break;
            }
        }

        if(maxDeep==0){
            throw new IllegalStateException("error processor number");
        }

        SortThread sortThread = new SortThread(array, maxDeep, 1, 0, array.length - 1);
        sortThread.start();
        sortThread.join();
        return sortThread.getSorted();
    }

    /**
     * sort thread
     */
    static class SortThread extends Thread implements Runnable {

        // the array to sort
        int[] originArray;

        //  tree deep, the deep currrent thread is
        int maxDeep, currentDeep;

        // the range of elements in origin array - need to sort
        int from, to;

        // the sorting result of elements between from and to
        int[] sorted;

        public SortThread(int[] originArray, int maxDeep, int currentDeep, int from, int to) {
            this.originArray = originArray;

            this.maxDeep = maxDeep;
            this.currentDeep = currentDeep;

            this.from = from;
            this.to = to;
        }

        public int[] getSorted() {
            return sorted;
        }

        public void setSorted(int[] sorted) {
            this.sorted = sorted;
        }

        @Override
        public void run() {

            if (currentDeep != maxDeep) {
                // split to two smaller array
                // length of left array
                int length1 = (to - from + 1) / 2;

                SortThread leftSortThread = new SortThread(originArray, maxDeep, currentDeep + 1, from, from + length1 - 1);
                SortThread rightSortThread = new SortThread(originArray, maxDeep, currentDeep + 1, from + length1, to);

                leftSortThread.run();
                rightSortThread.run();

                // wait for leftSortThread to finish
                try {
                    leftSortThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // wait for rightSortThread to finish
                try {
                    rightSortThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // merge two child array
                int[] merge = merge(leftSortThread.getSorted(), rightSortThread.getSorted());

                // sort merged array
                Arrays.sort(merge);

                // copy to origin array
                for (int i = 0; i < merge.length; i++) {
                    originArray[from + i] = merge[i];
                }

                this.sorted = merge;

            } else {
                // copy from range
                int[] copy = Arrays.copyOfRange(originArray, from, to + 1);
                Arrays.sort(copy);
                this.sorted = copy;
            }
        }
    }
}
