module firstlab

import StdEnv


f :: Int -> Int

f x = x*2 

 
//Start = f 42

g :: Int -> Int

g x = x / 2

//Start = g 5  // 2

gr :: Real -> Real

gr x = x / 2.0

//Start = gr 5.0  // 2.5

h :: Int Int -> Int

h a b = a + b

//Start = h 4 5

//Start = h 5 66

h3 :: Int Int Int -> Int

h3 x y z = x + 2*y*z

//Start = h3 1 2 3

h3y :: Real Real Real -> Int

h3y x y z = toInt (x + 2.0*y*z)

//Start = h3y 1.0 2.0 3.4

b :: Bool -> Bool
b x = not x
//Start = b False

st :: String String String -> String
st a b c = a +++ b +++ c

//Start = st "Hello" " World!" " form FP class"

c :: Char -> Char
c x = x

//Start = c '%'

quad :: Real Real Real Real -> Real

quad a b c x = a*x*x + b*x + c

//Start = quad 1.0 2.0 1.0 3.0

// homework solve a quadratic eq. Given the coeficients find the roots!

//Start = abs -4

myabs :: Int -> Int
myabs x
| x < 0 = ~x  //unary
| x == 0 = 0
| x > 0 = x

//Start = myabs -4

div2 :: Int -> Bool
div2 x = (x rem 2 == 0)  // remainder

//Start = div2 24

div22 :: Int -> Bool
div22 x = (x/2)*2 == x

//Start = div22 7

//Exercises lab 1
// 1. Define a function maxi with two arguments that delivers the maximum of the two.
maxi :: Int Int -> Int
maxi x y 
| x > y = x
= y

//Start = maxi 34 56


// 2. Define a function mini that has two arguments that delivers the minimum of the two.
mini :: Int Int -> Int
mini x y 
| x < y = x
| x == y = y     // = y
| x > y = y 

//Start = mini 34 56


// 3. Triple a number.
triple :: Int -> Int
triple x = 3*x

//Start = triple 3


// 4. Check if a number is odd.
isoddnr :: Int -> Bool  // isEven, isOdd built in
isoddnr x = ((x rem 2) == 1)

//Start = isoddnr 6


// 5. Check if a number is the sum of two other given numbers.
issum :: Int Int Int -> Bool
issum a b c = (a == b + c) || (b == a + c) || (c == a+b)

//Start = issum 10 6 3
//Start = issum 10 6 4

// 6. Add 100 to a number.
add100 :: Int -> Int
add100 x = x + 100

//Start = add100 5


// 7. Check if a number is multiple of 10.
ismult10 :: Int -> Bool
ismult10 x = x rem 10 == 0

//Start = ismult10 20
//Start = ismult10 202


// 8. Add the numbers from 1..N in a recursive function.
addn :: Int -> Int
addn n
| n <= 0 = abort " N can not be negative"
| n == 1 = 1 
= n + addn (n-1)

//Start = addn -10
//Start = addn 5
// 5 + addn 4
// 5+4+ addn 3
// 5+4+ 3+ addn 2
// 5+4+ 3+ 2 + addn 1 
// 5+4+ 3+ 2 + 1
// 15

// 9. Compute the cube of a number
cube :: Int -> Int
cube x = x^3 // ^ caret

//Start = cube 4
cube2 x = x*x*x

// 10. Check if an integer is even - in two ways. To divide integer use /, for remainder use rem
evenl :: Int -> Bool
evenl x = (x/2)*2 == x
//Start = evenl 34
//Start = even1 45

//version 2.
//even2 :: Int -> Bool
//even2 x
//|x = ((x rem 2 ) == 0 )


//Start = even2 34
//Start = even2 45


// 11. Add the digits of a number e.g. for 123 is 6

//isum :: Int -> Int
//isum x
//|x<10 = x
//|x>10 = (x rem 10) + isum (x/10)
//Start = isum 1234

// 12. Check if a number is divisble by 9! (using isum)
//div9 :: Int -> Bool
//div9 x = isum x rem 9 == 0

// 13. Write a function that takes two arguments, say n and x, and computes their power,
// in 2 versions - with recursion and without recursion.



// 14. use 13. to write a function that squares its argument.


// 15. Compute n! = n*(n-1)! recursively.


