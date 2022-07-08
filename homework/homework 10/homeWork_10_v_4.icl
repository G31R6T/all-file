module homeWork_10_v_4
import StdEnv



bestTree  = Node 10(Node 6(Node 1 Leaf(Node 5(Node 2 Leaf(Node 4(Node 3 Leaf Leaf)Leaf))Leaf))Leaf)(Node 14(Node 11 Leaf(Node 13(Node 12 Leaf Leaf)Leaf))(Node 17(Node 15 Leaf(Node 16 Leaf Leaf))(Node 19(Node 18 Leaf Leaf)(Node 20 Leaf Leaf))))
ourTree   = Node 15(Node 3(Node 1 Leaf Leaf)(Node 10(Node 7 Leaf (Node 8 Leaf Leaf))(Node 13 (Node 11 Leaf Leaf) Leaf)))(Node 20 (Node 18 Leaf (Node 19 Leaf Leaf)) (Node 21 Leaf (Node 26 (Node 24 Leaf Leaf) (Node 28 Leaf Leaf))))
shortTree = Node 14(Node 11 Leaf(Node 13 Leaf Leaf))(Node 17(Node 15 Leaf Leaf)Leaf)
noTree = Leaf
unitTree = Node 1337 Leaf Leaf

:: Tree a = Node a (Tree a) (Tree a) | Leaf

/*1- 
    * Define a type tree.
    * Write a function that takes a tree as a parameter
    * and returns a list of the numbers of the nodes whose children are both Leaf.
    * An empty tree will return [] and a single element tree will return a list of one element
*/

leaves :: (Tree Int) -> [Int]
leaves Leaf = []
leaves (Node x Leaf Leaf) = [x]
leaves (Node x l r) = leaves l ++ leaves r
//Start = leaves bestTree //[3,12,16,18,20]
//Start = leaves ourTree //[1,8,11,19,24,28]
//Start = leaves unitTree //[1337]
//Start = leaves noTree //[]






/* Write a function that takes exression tree and converts it 
 * string representation of this expression. Each node from expression
 * tree, contains either single integer value or 2 expresion trees and an
 * operator to combine them. DO NOT FORGET TO USE BRACKETS!
 * Ex.:
 *         +
 *        / \
 *       1   *      -> "(1 + (2 * 3))"
 *          / \
 *         2   3 
 */

:: Expr = Add | Sub | Mul | Div
:: ExprTree = ExprNode ExprTree Expr ExprTree | ValueNode Int

e1 = (ExprNode (ValueNode 1) Add (ValueNode 2))
e2 = (ExprNode (ExprNode (ValueNode 10) Sub (ValueNode 1)) Div (ValueNode 3))
e3 = (ExprNode e1 Mul e2)

check :: Expr -> String
check Add = " + "
check Mul = " * "
check Div = " / "
check Sub = " - "

extractExpresion :: ExprTree -> String
extractExpresion (ValueNode x) = toString x
extractExpresion (ExprNode l opr r) = "(" +++ (extractExpresion l) +++ (check opr) +++  (extractExpresion r) +++ ")"


//Start = extractExpresion (ValueNode 3) // "3"
//Start = extractExpresion e1 // "(1 + 2)"
Start = extractExpresion e2 // "((10 - 1) / 3)"
//Start = extractExpresion e3 // "((1 + 2) * ((10 - 1) / 3))"
