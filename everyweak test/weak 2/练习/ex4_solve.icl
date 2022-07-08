module ex4_solve

import StdEnv

// Compute the nth Fibonacci number - try more versions
fib :: Int -> Int
fib 0 = 1
fib 1 = 1
fib n = fib (n - 1) + fib (n - 2)

// Start = fib 5
fib1 :: Int -> (Int, Int)
fib1 0 = (1,1)
fib1 1 = (1,1)
fib1 n = (b,a+b)
where
	(a,b) = fib1 (n-1)

// Start = fib1 8
fib2 :: Int Int Int -> Int
fib2 a b 0 = a
fib2 a b c = fib2 b (a+b) (c-1)

// Start = fib2 1 1 10

fib3 :: Int -> Int
fib3 n = fibAux n 1 1

fibAux 0 a b         = a
fibAux i a b | i > 0 = fibAux (i-1) b (a+b)

// Start = fib3 8
 
// Exercises
  
// 1. Compute the product of the elements of a list
product :: [Int] -> Int
product [] =  1
product [x:xs] = x * product xs

//Start = product [1..5] // 120


// 2. delete the elements equal to 5
not_five :: [Int] -> [Int]
not_five [] = []
not_five [x:xs] 
| x == 5 = not_five xs
= [x: not_five xs]

//Start = not_five [5,4,5,4,3]  // [4,4,3]

not_five2 :: [Int] -> [Int]
not_five2 [] = []
not_five2 [5:xs] = not_five2 xs
not_five2 [x:xs] = [ x : not_five2 xs] 

//Start = not_five2 [5,4,5,4,3]  // [4,4,3]


// 3. Delete an element n from a list
del :: Int [Int] -> [Int]
del n [] = []
del n [x:xs]
| n==x = del n xs
= [x: del n xs]

//Start = del 5 [1, 5, 6, 7, 5, 8, 5] // [1, 6, 7, 8]


// 4. write a funtion with the patterns depending on the parameter:
// if the param is [] then is equal to 20, if is a two element list starting with 4 then is 30
// if is a two element list ending with 5 then is 40, in all other cases is 50, 
// the order of the patterns is important
gp :: [Int] -> Int
gp [] = 20
gp [4 ,_ ] = 30
gp [ _, 5] = 40
gp _ =50

//Start = gp [4, 6] // 30
//Start = gp [4, 5] // 30
//Start = gp [1..10]


// 5. write a funtion which returns true if a is divisible by b
div_by :: Int Int -> Bool
div_by a b = a rem b == 0

//Start = div_by 16 4      // True


// 6. write a funtion which returns true if a is divisible by b or vice versa
div_any :: Int Int -> Bool
div_any a b = div_by a b || div_by b a

//a rem b == 0 || b rem a == 0

//Start = div_any 4 16     // True


// 7. sumsq n returns 1*1 + 2*2 + ... + n*n - with a pattern for 0
sumsq :: Int -> Int
sumsq 0 = 0
sumsq n = n*n + sumsq (n-1)

//Start = sumsq 3 // 14


// version 2. - without pattern for 0
sums :: Int -> Int
sums n
| n == 0 = 0
= n*n + sums (n-1)

//Start = sums 3
  

// 8. check if a number is palindrom e.g.12321
p :: Int -> [Int]
p n = digits n []

digits :: Int [Int] -> [Int]
digits 0 x = x
digits n x = digits (n/10) [n rem 10 : x]

//Start = digits 12321 []
// digits 12321 []
// digits 1232 [1:[]]
// digits 123 [2:[1:[]]]
// digits 12 [3:[2:[1:[]]]]
// digits 1  [2:[3:[2:[1:[]]]]]
// digits 0  [1:[2:[3:[2:[1:[]]]]]]
// digits 0 [1,2,3,2,1]
// [1,2,3,2,1]

pali :: Int -> Bool
pali x = y == reverse y
where y = p x

//Start = pali 12321 // True
//Start = pali 12345


// 9. filter the elements that are satisfying a condition
f :: Int -> Bool
f x = x rem 5 == 0

filter` :: (Int -> Bool) [Int] -> [Int]
filter` p [] = []
filter` p [x:xs] 
| p x = [ x : filter` p xs]
= filter` p xs 

//Start = filter` f [1..15]
//Start = filter` isEven [1..10]

Start = filter` ((<>) 5) [1, 5, 6, 7, 5, 8, 5]  // [1, 6, 7, 8] 