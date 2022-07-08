implementation module Q

import StdEnv, StdDebug

// define your implementation of Q here

::	Q  = { nom :: Int
         , den :: Int
         }  

simplify :: Q -> Q
simplify {nom=n,den=d}
| d == 0 = abort " denominator is 0"
| d < 0 = { nom = ~n/g, den = ~d/g}
| otherwise = { nom = n/g, den = d/g}
   where g = gcdm n d

gcdm x y = gcdnat (abs x) (abs y)
   where gcdnat x 0 = x
         gcdnat x y = gcdnat y (x rem y)

mkQ :: Int Int -> Q
mkQ n d = simplify { nom = n, den = d }

equalQ :: Q Q -> Bool
equalQ x y = x.nom*y.den == y.nom*x.den

smallerQ :: Q Q -> Bool
smallerQ x y = x.nom*y.den < y.nom*x.den

plusQ :: Q Q -> Q
plusQ x y = mkQ (x.nom*y.den+y.nom*x.den) (x.den*y.den)

decrementQ :: Q Q -> Q
decrementQ x y = mkQ (x.nom*y.den - y.nom*x.den) (x.den*y.den)

timesQ :: Q Q -> Q
timesQ x y = mkQ (x.nom*y.nom) (x.den*y.den)

divideQ :: Q Q -> Q
divideQ x y = mkQ (x.nom*y.den) (x.den*y.nom)

absoluteQ :: Q -> Q
absoluteQ x = mkQ (abs x.nom) (abs x.den)

signOfQ :: Q -> Int
signOfQ x 
| y > 0.0 = 1
| y == 0.0 = 0
| y < 0.0 = -1
where y = toReal x.nom / toReal x.den

negateQ :: Q -> Q
negateQ x = mkQ (~x.nom) x.den

isIntQ :: Q -> Bool
isIntQ x = x.den == 1 

IQ :: Int -> Q
IQ x = mkQ x 1

QR :: Q -> Real
QR x = toReal x.nom / toReal x.den

QZero = { nom = 0, den = 1 }
QOne = { nom = 1, den = 1 }
q2 = { nom = 1, den = 2 }
q3 = { nom = 3, den = 4 }

//Start = simplify (mkQ 81 90)
//Start = mkQ 81 90
//Start = equalQ (mkQ 9 10) (mkQ 81 90)
//Start = smallerQ q2 q3
//Start = plusQ q2 QZero
//Start = decrementQ QZero q3
//Start = timesQ QOne q2
//Start = divideQ QOne q2
//Start = absoluteQ QZero
Start = signOfQ q2
//Start = negateQ q2
//Start = isIntQ QOne
//Start = IQ 4
//Start = QR q2

MyArray :: {Int}
MyArray = {1,3,5,7,9}
//Start = MyArray.[2] // 5
MapArray1 f a = {f e \\ e <-: a}
//Start :: {Int}
//Start = MapArray1 inc MyArray

:: Tree a = Node a (Tree a) (Tree a)
          | Leaf
atree = Node 2 (Node 1 Leaf Leaf) (Node 3 Leaf Leaf)

//Start = atree

sizeT :: (Tree Int) -> Int
sizeT Leaf = 0
sizeT (Node x l r) = 1 + sizeT l + sizeT r

//Start = sizeT (Node 4 (Node 2 (Node 1 Leaf Leaf) (Node 3 Leaf Leaf)) Leaf)