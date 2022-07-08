module homeWork_5_v_5
import StdEnv


/* Write a function that takes a list of integer pairs and sorts
 * it in ascending order. Pairs are given as a 2 element tuple.
 * Use first element to compare tuples, if first elements are equal
 * than use second element for comparison.
 * For example:
 * (1, 2) < (2, 3)
 * (1, 2) < (2, 0)
 * (1, 2) < (1, 3)
 * (1, 2) = (1, 2)
 * (1, 2) > (1, 1)
 * (1, 2) > (0, 3)
 * (1, 2) > (0, 0)
 */

pairSort :: [(Int, Int)] -> [(Int, Int)]
pairSort [] = []
pairSort list = sort list
//Start = pairSort [(1, 2), (2, 3), (2, 0), (1, 1), (0, 0), (1, 2), (1, 5)] // [(0,0),(1,1),(1,2),(1,2),(1,5),(2,0),(2,3)]
//Start = pairSort [] // []
//Start = pairSort [(0, 0)] // [(0, 0)]








/* Write a function that takes a list of string and int pairs
 * and returns list of sorted string based on their integer 
 * paired value.
 * For example:
 * [("A", 2), ("B", 1), ("C", 3)] -> ["B", "A", "C"]
 * "B" comes first as it is paired with smallest value 1,
 * it is followed by "A" and at last we have "C" as it has
 * highest value 3.
 * Assume that all integers are unique!
 */

comparePair :: (String,Int) (String,Int) ->Bool
comparePair (x1,y1) (x2,y2)
= y1>y2

pairSortHelper :: [(String, Int)] -> [(String, Int)]
pairSortHelper []=[]
pairSortHelper [a:res]
= pairSortHelper [x\\x<-res| (comparePair a x)] ++ [a] ++ pairSortHelper [x\\x<-res| (comparePair x a)]

pairSort :: [(String, Int)] -> [String]
pairSort aList
= [x\\(x,y)<-pairSortHelper aList]
//Start = pairSort [("A", 2), ("B", 1), ("C", 3)] // ["B","A","C"]
// Start = pairSort [("B", 2), ("A", 1), ("C", 0)] // ["C","A","B"]
// Start = pairSort [] // []


