module homeWork_8_v_2
import StdEnv


/*
 * Complete the function shiftF3 that takes a binary tree of String, and makes the capital case letters small case letters,
 * vice versa, in every node.
*/



:: Tree a = Node a (Tree a) (Tree a) | Leaf


treeBig   = Node "gorgeous" (Node "incREDIBLE"(Node "I am NOT SCREAMING") Leaf ) ( Node (Node "Tariq" Leaf Leaf) (Node "BBba" (Node "AAAAA" Leaf Leaf) Leaf))
treesmall = Node "tiny as HELL" Leaf Leaf
treeNone  = Node "" Leaf Leaf


shiftF3 :: (Tree String) -> (Tree String)


// Start = shiftF3 treeBig //  Node "GORGEOUS" (Node "INCredible"(Node "i AM not screaming") Leaf ) ( Node (Node "tARIQ" Leaf Leaf) (Node "bbBA" (Node "aaaaa" Leaf Leaf) Leaf))
// Start = shiftF3 treesmall // Node "TINY AS hell" Leaf Leaf
// Start = shiftF3 treeNone // Node "" Leaf Leaf
// Start = shiftF3 Leaf // Leaf







:: Employee = {name::String, job ::String,salary::Int}
 
 
emp1 :: Employee 
emp1 = {name = "Abood", job = "Teacher", salary = 100}
 
emp2 :: Employee 
emp2 = {name = "Ali", job = "Dressmaker", salary = 500}
 
emp3 :: Employee 
emp3 = {name = "Saleh", job = "Driver", salary = 250}

emp4 :: Employee 
emp4 = {name = "Othman", job = "Chef", salary = 999}

emp5 :: Employee 
emp5 = {name = "Mohammed", job = "Programmer", salary = 1000}

emp6 :: Employee 
emp6 = {name = "Ahmed", job = "Doctor", salary = 900}


/*-2
     * Given an array of 'Employees', check if the job 
     * of the employee with the highest salary is
     * 'Programmer'.
     * Assume that the given array is not empty.
*/
check :: {Employee} -> Bool

// Start = check {emp6,emp5,emp4 , emp3, emp1} // True 
// Start = check {emp6,emp4 , emp3, emp1}      // False 
// Start = check {emp5, emp1} // True 
// Start = check {emp1} // False 

