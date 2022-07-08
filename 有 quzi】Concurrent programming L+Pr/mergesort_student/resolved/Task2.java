import java.util.Arrays;

/* Task2: no slicing, no bullshit memcopy, parralelized merge */
public class Task2 {

    /* Create new sorted array by merging 2 smaller sorted arrays */
    private static void merge(int[] src, int[] dst, int idx1, int idx2, int end) {
        // TODO: 'src' is sorted between [idx1,idx2) and [idx2,end)
        //       copy both to 'dst' in a way that [idx1,end) is sorted for 'dst'
        // Note: 'idx1' is the starting point of the 1st array
        //       'idx2' is the starting point of the 2nd array
        //       'end' is the end of the 2nd array (exclusive)
        //       There are no elements between the first and second arrays
        //       'src' is the source, this is where the 2 smaller sorted arrays are
        //       'dst' is the destination, this is where you have to move data
        //       Merge the 2 smaller arrays using the same methodology as in 'Task1'

        int[] ints = Arrays.copyOfRange(dst, idx1, end);

        // sort
        Arrays.sort(ints);

        // copy into dest
        for (int i = 0; i < ints.length; i++) {
            dst[idx1 + i] = ints[i];
        }
    }


    /* Recursive core, calls a sibling thread until max depth is reached */
    public static void kernel(int[] src, int[] dst, int start, int end, int depth) throws InterruptedException {

        /* Single thread sort if bottom has been reached */
        // TODO: simply sort the array using 'Arrays.sort()' if depth is zero.

        if (depth == 0) {
            Arrays.sort(src);
        }


        /* Otherwise split task into two recursively */
        // TODO: summon another thread and recursively sort left and right half
        // NOTE: don't forget to make recursive call with 'depth-1'
        sortThread = new SortThread(src, dst, depth, 1, start, end);
        sortThread.start();
        sortThread.join();
    }

    private static SortThread sortThread;

    /* Creates a sorted version of any int array */
    public static int[] sort(int[] array) throws InterruptedException {

        /* Initialize variables */
        // TODO: Create 'src' and 'dst' arrays
        int[] src = Arrays.copyOf(array, array.length);
        int[] dest = new int[array.length];

        /* Calculate optimal depth */
        /*int minSize = 1000;
        int procNum = Runtime.getRuntime().availableProcessors();
        int procDepth = (int) Math.ceil(Math.log(procNum) / Math.log(2));
        int arrDepth = (int) (Math.log(array.length / minSize) / Math.log(2));
        int optDepth = Math.max(0, Math.min(procDepth, arrDepth));*/

        /* Launch recursive core */
        // TODO: launch kernel, call with 'optDepth' (not 'optDepth-1')
        kernel(src, dest, 0, src.length - 1, 3);

        // TODO: return src or dst depending on the parity of the used depth
        // TODO: delete all lines starting with '//'
        System.out.println("task2 sorted result" + Arrays.toString(sortThread.getDest()));
        return sortThread.getDest();

    }

    /**
     * sort thread
     */
    static class SortThread extends Thread implements Runnable {

        // the array to sort
        int[] src;

        //  tree deep, the deep currrent thread is
        int maxDeep, currentDeep;

        // the range of elements in origin array - need to sort
        int from, to;

        // the sorting result of elements between from and to
        int[] dest;

        public SortThread(int[] originArray, int[] dest, int maxDeep, int currentDeep, int from, int to) {
            this.src = originArray;
            this.dest = dest;

            this.maxDeep = maxDeep;
            this.currentDeep = currentDeep;

            this.from = from;
            this.to = to;
        }

        public int[] getDest() {
            return dest;
        }

        public void setDest(int[] dest) {
            this.dest = dest;
        }

        @Override
        public void run() {

            if (currentDeep != maxDeep) {
                // split to two smaller array
                // length of left array
                int length1 = (to - from + 1) / 2;

                SortThread leftSortThread = new SortThread(src, dest, maxDeep, currentDeep + 1, from, from + length1 - 1);
                SortThread rightSortThread = new SortThread(src, dest, maxDeep, currentDeep + 1, from + length1, to);

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
                merge(src, dest, from, from + length1, to);
            } else {
                // copy from range
                int[] copy = Arrays.copyOfRange(src, from, to + 1);
                Arrays.sort(copy);
                // copy sorted result to dest
                for (int i = 0; i < copy.length; i++) {
                    dest[from + i] = copy[i];
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[] ints = {3, 1, 2, 11, 9, 4, 6, 10, 5, 7, 8, 12, 13};
        System.out.println("array after sorted: " + Arrays.toString(Task2.sort(ints)));
    }
}
