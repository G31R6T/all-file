module homeWork_extra_v_4
import StdEnv


/* Write a function that gets a text (string)
 * an integer key and returns encrypted text back.
 * Encryption should be done with Caesar Cipher. 
 * Encryption with Caesar code is based on an alphabet shift.
 * For example if key is 3, every character is changed with one
 * 3 steps right from it:
 * Plain Alphabet         - ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * Caesar Alphabet, Key=3 - DEFGHIJKLMNOPQRSTUVWXYZABC
 *
 * So, if the text is "apple" and key is 3, encrypted text
 * would be 'dssoh'.
 * 'a' +3 -> 'd'
 * 'p' +3 -> 's'
 * 'l' +3 -> 'o'
 * 'e' +3 -> 'h'
 *
 * You can assume that text contains only lower case letters
 * but the key can be any integer. It can be greater than 26
 * or negative. Negative keys rotate alphabet to the left.
 */

// caesar :: String Int -> String

// Start = caesar "apple" 3 // "dssoh"
// Start = caesar "apple" -5 // "vkkgz"
// Start = caesar "apple" 30 // "ettpi"
// Start = caesar "apple" 26 // "apple"
// Start = caesar "caesar" 7 // "jhlzhy"






/*1. Smallest subArray
    * Given a list of integer numbers and an integer, find the smallest consecutive
    * subarray the sum of which is greater than the given integer.
    * Example :
            Integer =  97
            Smallest subarray in  [ 1, 5, 20, 70, 8] whose sum is greater than 97 is  [20, 70, 8]
            the output should be [20, 70, 8]  
*/
// lengthOfSmallestSubArray ::  [Int] Int -> [Int]


// Start = lengthOfSmallestSubArray [1, 5, 20, 70, 8] 97 // [20, 70, 8]
// Start = lengthOfSmallestSubArray [1..100] 56 // [28,29]
// Start = lengthOfSmallestSubArray [1..23] 275 // [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
