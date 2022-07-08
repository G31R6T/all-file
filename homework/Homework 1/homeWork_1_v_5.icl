module homeWork_1_v_5
import StdEnv


/*
Impelement the zeroFinder function that can detect the presence of the zero digit in a given number
The function returns True if the zero digit exists, and False if it doesn't
The zero can be located at any point in the number
*/
/*
zeroFinder :: Int -> Bool
zeroFinder x
|x == 0 = False
|((x rem 10) == 0) = True
= zeroFinder(x/10)
*/
zeroFinder :: Int -> Bool
zeroFinder x
|x ==0 = True
=next x
next x 
|x == 0 = False
|((x rem 10) == 0) = True
= next(x/10)


//Start = zeroFinder 154820    // True 
//Start = zeroFinder 150125   // True 
//Start = zeroFinder 101     // True 
//Start = zeroFinder 7989  // False 
//Start = zeroFinder 0 // True


/*
Impelement the function len that counts the number of digits in a number
*/

len :: Int -> Int
len x
|x == 0 = 0 
=len(x/10) + 1

//Start = len 12345    // 5 
//Start = len 0001   // 1
//Start = len 100     // 3 

