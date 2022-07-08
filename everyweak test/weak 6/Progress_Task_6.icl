module Progress_Task_6

import StdEnv


/*
Given a list of Three integer tuples, and a boolean condition function for a three integer
tuple, convert the tuples into two element tuples where the first part is equal to the first
part of the threee element tuple plus half of the second part and the third part is equal to the 
third part of the three element tuple plus half of the second part.
eg: checker [(10,6,14), (12,2,3)] (\x = True) => [(13,17), (13,4)] 

*/
convertTuple :: (Int,Int,Int) -> (Int,Int)
convertTuple (a,b,c)
=(a+b/2,c+b/2)

checker :: [(Int,Int,Int)] ((Int,Int,Int)->Bool)-> [(Int,Int)]
checker aList f
=map convertTuple (filter f aList) 
//Start = checker [(10,6,14), (12,2,3)] (\x = True) // [(13,17), (13,4)]
// Start = checker [(10,6,14), (12,2,3)] (\x = False) // []
// Start = checker [(10,6,14), (12,3,2)] (\(a,b,c) = a>b && b < c) // [(13,17)]
// Start = checker [] (\(a,b,c) = a>b && b < c) // []