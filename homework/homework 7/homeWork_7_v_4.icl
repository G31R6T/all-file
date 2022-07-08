module homeWork_7_v_4
import StdEnv



/* Write a `leftmostTopPoint` that takes list of Points and
 * returns the point with highest `y` coordinate and if there
 * are several points with same highest `y` coordinate choose
 * one with lowest `x` coordinate. (Leftmost point among highest
 * points.)
 * Assume that list is not empty.
 */

:: Point = { x :: Real ,y :: Real}

topLeftPoint :: [Point] -> Point
topLeftPoint list = {x=(minList [ a.x \\ a <- (filter (\a = a.y == (maxList [ a.y \\ a <- list]))list)]),y=(maxList [ a.y \\ a <- list])}

//Start = topLeftPoint [{x=1.0,y=0.0}, {x=0.0, y=1.0}, {x=(-1.0), y=1.0}, {x=(-2.0), y=(-1.0)}] // {x=-1, y=1}
//Start = topLeftPoint [{x=0.0, y=0.0}] // {x=0, y=0}
//Start = topLeftPoint [{x=3.0, y=3.0}, {x=3.0, y=3.0}, {x=0.0, y=1.0}] // {x=3, y=3}







/* 2.
Given an array of Persons, write a function that calculates the BMI of each Person
BMI: body mass index = m / h^2
m = mass (in kilograms)
h = height (in meters)
note: the mass given in the records are in pounds, you need to convert before using the formula
hint: 1 pound = 0.453592kg
*/

//heightInCM :: {Person} -> {Person}
//heightInCM prsn = {{x & height = x.height * 100.0} \\ x <-: prsn}

//massInKG :: Person -> Person
//massInKG prsn = {prsn & mass = toReal(toInt((prsn.mass * 0.453592)))}

//calcBMI :: {Person} -> {Person}
//calcBMI prsn =  heightInCM { {(massInKG x) & bmi = ((massInKG x).mass) / (x.height)^2.0 } \\ x <-: prsn }


//Start = calcBMI {Rose,Jack,Emilia} // {(Person "Rose" 67 172 22.6473769605192),(Person "Jack" 72 193 19.3293779698784),(Person "Emilia" 55 160 21.484375)}
//Start = calcBMI {Leo,Grace,Harry} // {(Person "Leo" 39 175 12.734693877551),(Person "Grace" 51 165 18.732782369146),(Person "Harry" 77 180 23.7654320987654)}
//Start = calcBMI {} // {}
