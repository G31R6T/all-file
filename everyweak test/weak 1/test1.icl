module test1
import StdEnv

//Write a function that takes Int

/*game x 
|x < 0 = x *x
|x > 0 = prod[1..x]
|x == 0 = x
Start = game 0*/

/*fac n 
|n == 0 = 1
|n>0 = n*fac(n-1)
Start = fac 5*/
add100::Real ->Real 
add100 x = x + 100.0
Start = add100 3