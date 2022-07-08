module Progress_Task_8

import StdEnv

:: Tree a = Node a (Tree a) (Tree a) | Leaf

bestTree :: (Tree Int)
bestTree = Node 10(Node 6(Node 1 Leaf(Node 5(Node 2 Leaf(Node 4(Node 3 Leaf Leaf)Leaf))Leaf))Leaf)(Node 14(Node 11 Leaf(Node 13(Node 12 Leaf Leaf)Leaf))(Node 17(Node 15 Leaf(Node 16 Leaf Leaf))(Node 19(Node 18 Leaf Leaf)(Node 20 Leaf Leaf))))
ourTree :: (Tree Int)
ourTree = Node 15(Node 3(Node 1 Leaf Leaf)(Node 10(Node 7 Leaf (Node 8 Leaf Leaf))(Node 13 (Node 11 Leaf Leaf) Leaf)))(Node 20 (Node 18 Leaf (Node 19 Leaf Leaf)) (Node 21 Leaf (Node 26 (Node 24 Leaf Leaf) (Node 28 Leaf Leaf))))
shortTree :: (Tree Int)
shortTree = Node 14(Node 11 Leaf(Node 13 Leaf Leaf))(Node 17(Node 15 Leaf Leaf)Leaf)
noTree :: (Tree Int)
noTree = Leaf
unitTree :: (Tree Int)
unitTree = Node 1337 Leaf Leaf


/*
Write a function that takes a tree as a parameter
and returns a list of the numbers of the nodes whose children are both Leaf.
An empty tree will return [] and a single element tree will return a list of one element
*/

isLeaf :: (Tree Int)->Bool
isLeaf Leaf = True
isLeaf _ =False

leaves :: (Tree Int) -> [Int]
leaves Leaf = []
leaves (Node x l r)
| ( isLeaf l) && (isLeaf r) = [x] ++ (leaves l)++(leaves r)
= (leaves l)++(leaves r)


//Start = leaves bestTree //[3,12,16,18,20]
//Start = leaves ourTree //[1,8,11,19,24,28]
Start = leaves unitTree //[1337]
// Start = leaves noTree //[]

