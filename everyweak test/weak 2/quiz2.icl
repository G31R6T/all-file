module quiz2
import StdEnv


/* Given a list and a number K, check if all the numbers
?* from the list are smaller than K or divisble by K.

?* Assume that all given numbers are positive.

?* Ex.:

?* If k = 5, [3, 10] is a good list, because each number is either smaller or divisible by k.

?* If k = 5, [2, 8, 15] is a bad list. While 2 and 15 are good numbers, 8 is neither smaller or divisble by k.

?*/
helper :: Int Int -> Bool
helper x k
| x <= k = True
| x rem k == 0 = True
=False
 
kGood :: [Int] Int -> Bool
kGood [] k = True
kGood [x:xs] k
|not(helper x k ) = False
|helper x k = (kGood (t1 [x:xs]) k)
=True


//Start = kGood [] 5 // True

Start = kGood [3,5,15,1] 5 // True

// Start = kGood [1,2,3] 4 // True ?

// Start = kGood [9, 30, 333] 3 // True

// Start = kGood [2, 8, 10] 5 // False

// Start = kGood [1, 7, 12] 2 // False