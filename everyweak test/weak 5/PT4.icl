module PT4
import StdEnv 


/*
    1. Given the list of tuples, where the first element is the list of numbers, the second
    element is a bound (Int) and the third one is the switch (Bool). If the switch is true
    remove all elements greater than bound from the list, if the switch is false remove all
    elements less than bound.
*/

dualFilter :: [([Int],Int,Bool)] -> [[Int]]

// Start = dualFilter [([1..10], 5, True), ([1..10], 5, False)] // [[1,2,3,4,5],[5,6,7,8,9,10]]
// Start = dualFilter [([3,5..20], 3, True), ([], 4, False), ([1..5], 5, True)] // [[3],[],[1,2,3,4,5]]
// Start = dualFilter [] // []
Start = dualFilter [([1,3,8,2,12,45,5,1,3,5,81,12], 10, True)] // [[1,3,8,2,5,1,3,5]]
