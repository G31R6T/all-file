module homeWork_3_v_4
import StdEnv


/* Write a function that takes list and number K as arguments.
 * Function should return true if all POSITIVE numbers in the list
 * are divisible by K.
 * For example:
 * [4,8,4,12], K=4 - should return true as all numbers are divisble by K.
 * [5, -1, 15], K=5 - should return true as well. -1 is not divisble by K,
 *                    but it is not positive and we ignore it.
 *
 * Assume that K always is positive and [] list satisfies condition for every K.
 */
isPositiveDivisible :: [Int] Int -> Bool
isPositiveDivisible x k 
| x == [] = True
= ((hd x) rem k) == 0 && isPositiveDivisible (tl x) k

//Start = isPositiveDivisible [4,8,4,12] 4 // True
// Start = isPositiveDivisible [5, -1, 15] 5 // True
//Start = isPositiveDivisible [-5, 2] 5 // False
// Start = isPositiveDivisible [8,3,10] 2 // False
//Start = isPositiveDivisible [] 10 // True 






/*
2-  
    * Given a list of integers representing temperatures in a concrete day, implement 
    * the function 'closestToZero' which does the following:  
    * Find the temperature closest to 0 among the given list, If two numbers are equally close
    * to zero, positive integer has to be considered closest to zero (for instance, if the 
    * temperatures are -5 and 5, then return 5).
    * Example:
            If the given list of temperatures is [1,-2,-8,4,5], the output should be 1 becuase its 
            the closest to zero;
*/

closestToZero :: [Int] -> Int
closestToZero x 
| helper4 x == 9999999 = 0
= helper4 x

helper4 :: [Int] -> Int
helper4 x 
|x == [] = 9999999
= helper3 (hd x) (helper4 (tl x))

helper3 :: Int Int -> Int
helper3 x y  = min (abs x) (abs y)
//Start = closestToZero [1,-2,-8,4,5] // 1 
// Start = closestToZero [5,-5,8,7,-7,12,-45,-17,10] // 5
// Start = closestToZero [] // 0

