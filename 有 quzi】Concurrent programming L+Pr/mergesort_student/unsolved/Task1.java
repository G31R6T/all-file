import java.util.Arrays;

/* Task1: slicing and merging on 1 thread, sorting slices is parralelized */
public class Task1 {

  /* Create new sorted array by merging 2 smaller sorted arrays */
  private static int[] merge(int[] arr1, int[] arr2) {
    // TODO: merge sorted arrays 'arr1' and 'arr2'
    return null;
  }

  /* Creates an array of arrays by slicing a bigger array into smaller chunks */
  private static int[][] slice(int[] arr, int k) {
    //TODO: cut 'arr' into 'k' smaller arrays
    return null;
  }


  /* Creates a sorted version of any int array */
  public static int[] sort(int[] array) {

    /* Initialize variables */
    // TODO: check available processors and create necessary variables

    /* Turn initial array into array of smaller arrays */
    // TODO: use 'slice()' method to cut 'array' into smaller bits

    /* parralelized sort on the smaller arrays */
    // TODO: use multiple threads to sort smaller arrays (Arrays.sort())

    /* Merge sorted smaller arrays into a singular larger one */
    // TODO: merge into one big array using 'merge()' multiple times
    //       create an empty array called 'sorted' and in a for cycle use
    //       'merge(sorted, arr2d[i])' where arr2d is an array of sorted arrays

    /* Return fully sorted array */
    // TODO: return the sorted array and delete all lines starting with '//'
    return null;
  }
}
