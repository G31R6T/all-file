module midterm
import StdEnv


// 1. Generate the first 10 positive elements of a list in which a number is multiple of 5 but is not multiple of 10.

glist :: [Int]
glist = take 10 ([5*x \\ x <- [1..] | not (isEven x) ] )

glist1 = take 10 [x \\ x<-[1..]|(x rem 5==0) &&(x rem 10 <>0)]

glist2 = [5*x\\x<-[1,3..19]]

Start = glist

// 2. Compute the product of the elements of the sublists of a list (you MUST use map)

multiply :: [Int] -> Int
multiply [] = 1
multiply list =  foldr (*) 1 list

plist :: [[Int]] -> [Int]
plist list = map multiply list

plist1 x = map prod x

//Start = plist [[1, 2, 3], [3, 4], [5, 7, 8, 9], []]

// 3. Given a list of tuples make a tuple of 2 lists like:

clist :: [(Int, Int)] -> ([Int], [Int])
clist list = unzip list

h1 [] = []
h1 [(x,y):xs] =   [x] ++ h1 xs
d1 [] = []
d1 [(x,y):xs] =   [y] ++ d1 xs
clist1 x = (h1 x,d1 x)

clist2 l = ([fst x \\ x<-l], [snd x \\ x<-l])

//Start = clist [(1, 2), (3,1), (8, 4), (5, 7), (8, 9)] // ([1,3,8,5,8],[2,1,4,7,9])

// 4. Insert x as second element in every sublist of a list.
// if the sublist was empty then x will be the only element in the new sublist.
// [[1,2], [3,4,5], [6,5,9,7], [], [8]] 100 -> [[1,100,2], [3,100,4,5], [6,100,5,9,7], [100], [8,100]]
insertx :: [Int] Int -> [Int]
insertx [] x = [x]
insertx [n:ns] x = [n,x:ns]

xlist :: [[Int]] Int -> [[Int]]
xlist list x = map (\n = insertx n x) list

ins :: Int [Int] -> [Int]
ins n [] = [n]
ins n x = [hd x] ++ [n] ++ (drop 1 x)

xlist2 x n = map (ins n) x

xlist1 [] n = [n]
xlist1 [x] n = [x,n]
xlist1 [x,y:xs] n = [x,n,y: xs]

xlist3 [] _ = []
xlist3 [x:xs] n = [xlist1 x n : xlist3 xs n]

//Start = xlist [[1,2], [3,4,5], [6,5,9,7], [], [8]] 100

// 5. Generate pairs like in the following:
// [[1,2,3], [4,5], [6,7,8], []] -> [(1,6),(2,9),(3,21),(4,0)]

fpair` :: [[Int]] Int -> [(Int, Int)]
fpair` [] y = []
fpair` [x:xs] y = [(y+1, (foldr (+) 0 x)): fpair` xs (y+1)]

fpair :: [[Int]] -> [(Int, Int)]
fpair list = fpair` list 0

fpair1 x = zip([1..], map sum x)


fpair11 [] = 0
fpair11 [x:xs] = x + fpair11 xs
fpair22 [] = []
fpair22 [x:xs] = [fpair11 x : fpair22 xs]
fpair2 x = [(x,y) \\ x<-[1..] & y<-fpair22 x]

fpair3 l = [(x,y) \\ x<-[1..] & y<-(map sum l)]

	
//Start = fpair1 [[1,2,3],[4,5],[6,7,8],[]]


// 6. Extract the second element of each sublist of a list (if there is no second element, ignore that sublist)
// e.g. [[1,2,3], [3,4,5,6], [], [5,7,8], [1], [8,9]]-> [2,4,7,9]

qlist :: [[Int]] -> [Int]
qlist [] = []
qlist [x:xs] 
| length x >1 = [ last (take 2 x) : qlist xs]
= qlist xs


extr:: [Int] -> [Int]
extr [] = []
extr [x:xs] = [x]++ (drop 1 xs)

qlist1 x = flatten(map extr x)


cd [] = []
cd [x:xs] 
|x==[] = cd xs
|length x ==1 = cd xs
=[x:cd xs] 

qlist11 [x,y:xs] = y 
qlist2 [] = []
qlist2 [x:xs] =map qlist11 (cd [x:xs]) 


qlist3 l = map second ll 
where ll = [x \\ x <-l | length x <> 0  && length x <> 1]

second :: [Int] -> Int
second x = x!!1


//Start = qlist [[1,2,3], [3,4,5,6], [], [5,7,8], [1], [8,9]]


// 7. Check if a list contains 2 equal elements one after the other
// (it can be anywhere in the list)
// for [1,2,3,3,3,2,4,5] is True for [1 .. 5] is False

dlist :: [Int] -> Bool
dlist [] = False
dlist [x] = False
dlist [x,y:xs]
| x==y = True
= dlist [y:xs]


dlist1 x =  foldr (||) False (map check (zip (x, tl x)))

check :: (Int,Int) -> Bool
check (x,y) = (x==y)

//Start = dlist [1,2,3,4,3,2,4,8,5,5]


// 8. Generate the following list
// [[1],[1,2],[1,2,3],[1,2,3,4],[1,2,3,4,5],[1,2,3,4,5,6],[1,2,3,4,5,6,7],[1,2,3,4,5,6,7,8],[1,2,3,4,5,6,7,8,9],[1,2,3,4,5,6,7,8,9,10]]

nlist :: Int -> [[Int]]
nlist n = [x \\ y <- [1..n], x<-[[1..y]]]

nlist1 n = take 10[[x..y] \\ x<-[1..n],y<-[1..n] ]

nlist2 n = [[x\\x<-[1..y]]\\y<-[1..n]]


f :: Int -> [[Int]]
f n 
| n > 0 = [[1..n] : f (n-1)]
= []

nlist3 n = reverse (f n)


//Start = nlist 10