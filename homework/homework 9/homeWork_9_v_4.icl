module homeWork_9_v_4
import StdEnv


/* write a function  that takes 2 binary trees as arguments
 * and checks if the second tree can be the subtree of the
 * first one. A tree is a sub-tree of another tree if there
 * exists a node, starting from which nodes are identical.
 * Ex.:
 *     1
 *    / \
 *   2   3    has sub trees: 2,   3,   4,   5 and itself.
 *      / \                      / \
 *     4   5                    4   5 
 */ 

//:: BinTree a = BinNode a (BinTree a) (BinTree a) | TreeEnd

//bt1 = (BinNode 3 (BinNode 2 TreeEnd (BinNode 4 TreeEnd TreeEnd)) (BinNode 1 (BinNode 5 TreeEnd TreeEnd) TreeEnd))
// bt2 = (BinNode 0 TreeEnd TreeEnd)
// bt3 = (BinNode 10 (BinNode 11 bt1 bt2) (BinNode 12 TreeEnd bt2))

isSubtree :: (BinTree Int) (BinTree Int) -> Bool
isSubtree TreeEnd TreeEnd = True
isSubtree (BinNode x 1 r) TreeEnd = True
isSubtree TreeEnd (BinNode y 12 r2) = False
isSubtree (BinNode x 1 r) (BinNode y 12 r2)
|x <> y == (isSubtree 1 (BinNode y 12 r2)) || (isSubtree r (BinNode y 12 r2))
|x == y = (isSubtree 1 12 ) && (isSubtree r r2)





Start = isSubtree TreeEnd TreeEnd // True
// Start = isSubtree bt1 TreeEnd // True
// Start = isSubtree bt1 bt2 // False
// Start = isSubtree bt3 bt1 // True
// Start = isSubtree bt1 bt3 // False






/*
	2- Return the smallest element in a rose tree. 


*/

:: RoseTree = Node Int [RoseTree] 

r1 = Node 4 [Node 8 [], Node 12 [] , Node 2 [] ]
r2 = Node 3 [r1, Node 5 []]
r3 = Node 20 [Node 0 [], Node 5 []]


minRose :: RoseTree -> Int 

//Start :: RoseTree
// Start = minRose r1	// 2
// Start = minRose r2 	// 2
// Start = minRose r3 	// 0
// Start = minRose (Node 100 [r1, r2 ,r3]) // 0



