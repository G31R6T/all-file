module homeWork_2_v_5
import StdEnv


/*1-

    Given a number consists of exactly five digits,  write the function 'isOddPalindrome ' which decides whether the given number is palindrome and odd number or not.

    A number is palindrome if it remains the same when its digits are reversed.

    Example :

    If the given number is 12321, the output should be True, because when the digits of 12321 are reversed, we get the same number and it's also an  odd number.*/

isOddPalindrome :: Int -> Bool
isOddPalindrome x = (isOdd x) && (isPalindrome x)

isPalindrome :: Int -> Bool
isPalindrome x
| x<0 = False
| x<10 = True
| x == (palindrome x 0 ) = True
= False

palindrome :: Int Int -> Int
palindrome a b
| a<=0 = b
= palindrome (a/10) (b*10+(a rem 10))
 

//Start = isOddPalindrome 12321 // True

//Start = isOddPalindrome 13222 //  False

//Start = isOddPalindrome 22222 // False

//Start = isOddPalindrome 23232 // False

//Start = isOddPalindrome 75557 // True 







/*2- Given a list of integers, check if the numbers in the list satisfy the following condition:
     the number and the position of it  in the list are both  odd or both  even. 
     
     Check the first one whether the position is even and number is even
     then the second one whether is odd
     use recursion
*/

//check :: [Int] -> Bool 
check :: [Int] -> Bool
check[]=0==0
check [first,second:rest] = (first rem 2==0) && (second rem 2==1) && check rest
//check x = sum[a\\a<-x & b<-[0..]| ((isOdd a) && (isOdd b)) || ((isEven b) && (isEven a))] == (sum x)


//Start = check [0,1,2,3,4,5,6] // True
//Start = check [0,1,2,4,4,5] // False 
//Start = check [0,1,2] // True 
//Start = check [2,1] // True 
Start = check [1] // False
//Start = check[] // True 

