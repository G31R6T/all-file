module test

import StdEnv

/*list::[Int][Int] ->[Int]
list [] [] = []
list[x:xs] [y:ys] = [x+y : list xs+ys]
Start = list [1,2,3] [4,5,6]*/
f2 :: [Int] -> [Int]
f2 [] = []
f2 [x:xs]
| x > 0 = [2*x : f2 xs]
= f2 xs

//Start = f2 [1, 2, -2, 3, -4] // [2, 4, 6]