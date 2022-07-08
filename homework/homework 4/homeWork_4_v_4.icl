module homeWork_4_v_4
import StdEnv

/*
    * Given a list of triple tuples, each typle contains three pieces of information about the library patrons at Saskatoon Public Library (SPL) - patron's patron,
    * the number of books they have borrowed throughout the last year, and a Boolean value indicating whether they are under 20.
    a) Impelement the 'good_readers' function which returns a list of the patron names who borrowed more than 100 books last year.
    b) Impelement the 'not_teenagers' function which returns a list of the patron names who are not under 20.
    c) Suppose that a patron saves on average $11.95 by borrowing a book instead of buying it. Impelement the 'saved_amount' function
        which returns a list of the amount saved for each patron.
    d)Impelement the function 'names_and_saved_amount' which creates   a list of tuples  where each tuple consists of a patron's patron and the total amount
       he/she saved last year as in part (c)), but only include those patrons who are under 20.    
*/


// For the test cases.
SPL_Patrons = [
                ("Kim Tremblay", 134, True),
                ("Emily Wilson", 42, False),
                ("Jessica Smith", 215, True),
                ("Alex Roy", 151, True),
                ("Sarah Khan", 105, False),
                ("Samuel Lee", 220, True),
                ("William Brown", 24, False),
                ("Ayesha Qureshi", 199, True),
                ("David Martin", 56, True),
                ("Ajeet Patel",69, False)
              ]


good_readers :: [(String,Int,Bool)]  ->  [String]
good_readers [] = []
good_readers [(patron,books,age):rest] 
| books > 100 = [patron: good_readers rest]
= good_readers rest 


Start = good_readers SPL_Patrons  // ["Kim Tremblay","Jessica Smith","Alex Roy","Sarah Khan","Samuel Lee","Ayesha Qureshi"]    
                                           
not_teenagers :: [(String,Int,Bool)] ->  [String]
not_teenagers [] = []
not_teenagers [(patron,books,age):rest]
| age = not_teenagers rest
= [patron:not_teenagers rest]

//Start = not_teenagers SPL_Patrons // ["Emily Wilson","Sarah Khan","William Brown","Ajeet Patel"]

saved_amount :: [(String,Int,Bool)]  ->  [Real]
saved_amount [] = []
saved_amount [(patron,books,age):rest] = [11.95 * (toReal books): saved_amount rest]

//Start = saved_amount  SPL_Patrons // [1601.3,501.9,2569.25,1804.45,1254.75,2629,286.8,2378.05,669.2,824.55]

names_and_saved_amount :: [(String,Int,Bool)]  ->  [(String,Real)]
names_and_saved_amount [] = []
names_and_saved_amount [(patron,books,age):rest]
| not age = names_and_saved_amount rest
= [(patron, (11.95 * (toReal (books)))) : names_and_saved_amount rest]

//Start = names_and_saved_amount SPL_Patrons//[("Kim Tremblay",1601.3),("Jessica Smith",2569.25),("Alex Roy",1804.45),("Samuel Lee",2629),("Ayesha Qureshi",2378.05),("David Martin",669.2)]



/*Given a list of integers and an integer target, return a four element tuple that contains elements form the list that sum up to the value of the
given target
if there are no elements that sum up to the target, return an empty list
e.g: [1,2,3,4,142,32] 10 -> [(1,2,3,4)]
*/

fourSum :: [Int] Int  -> [(Int,Int,Int,Int)]
fourSum [] _ = []
fourSum [x] _ = []
fourSum [x,y] _ = []
fourSum [x,y,z] _ = []
fourSum [a,b,c,d:rest] n
| (a + b + c + d == n) = (fourSum rest n) ++ [(a,b,c,d)]
= fourSum rest n

//Start = fourSum [1,2,3,4,142,32] 10 // [(1,2,3,4)]
//Start = fourSum [1,1,1,5,2,2,2,2] 8 // [(2,2,2,2), (1,1,1,5)]
//Start = fourSum [1,1,1,5,2,2,2,2] 250 // []