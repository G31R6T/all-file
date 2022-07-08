module eren_jaeger
import StdEnv

/* Write a function that takes a dictionary, a new item
 * and return updated dictionary. An item is represented
 * as a (Int, String) tuple and a dictionary is a list of
 * items - [(Int, String)]. In the dictionary items are 
 * ordered increasingly based on their key.
 * To update dictionary with a new key, value pair:
 *   * If key already exists, change it's assosiated value
 *     with a new one.
 *   * If key does not exists, add a new key, value item.
 *   * After ordering key ordering should stay increasing!
 */
 
 
updateDict :: [(Int, String)] (Int, String) -> [(Int, String)]
updateDict [] key_val = [key_val]
updateDict list key_val = filter (\x = fst x < fst key_val) list ++ [key_val] ++ filter (\x = fst x > fst key_val) list
//Start = updateDict [] (1, "AA") // [(1, "AA")]
//Start = updateDict [(1, "AA"), (5,"X")] (2,"2") // [(1, "AA"), (2,"2"), (5,"X")]
//Start = updateDict [(1, "AA"), (5, "X")] (5, "New") // [(1, "AA"), (5, "New")]
//Start = updateDict [(3, "_")] (1, "!") // [(1, "!"), (3, "_")]
//Start = updateDict [(3, "_")] (3, "!") // [(3, "!")]
//Start = updateDict [(3, "_")] (7, "!") // [(3, "_"), (7, "!")]









/*
	Given a list of tuples, remove the duplicated tuples. A duplicated
	tuple might contain the same elements but in different positions. 
	
	For instance:
	(1,4,6) (4,6,1) -> These are a duplicated tuple. 
	Therefore, one of them should be removed from the list.
	
	
	[(1,4,6), (6,4,1), (4,1,6)]  -> [(1,4,6)]
	
	Note that the output order does not matter. In other words, the last example would output
	any of the input tuples.
*/

removeSameTupAux :: [(Int,Int,Int)]-> [(Int,Int,Int)]
removeSameTupAux [] = []
removeSameTupAux list = [ (hd (sort [fst3 x, snd3 x , thd3 x]), (sort [fst3 x, snd3 x , thd3 x])!!1, last (sort [fst3 x, snd3 x , thd3 x])) \\ x <- list]

//removeSameTupAux2 :: [(Int,Int,Int)] [(Int,Int,Int)] -> [(Int,Int,Int)]
removeSameTupAux2 [] _ = []
removeSameTupAux2 list list2 = reverse [ x   \\ x <- list & y <-[1..] | not (isMember (tupleThisB (removeSameTupAux [x])) (drop y list2))]
where
    tupleThisB [(a,b,c)] = (a,b,c) 

//removeSameTup :: [(Int,Int,Int)] -> [(Int,Int,Int)]
removeSameTup [] = []
removeSameTup list = removeSameTupAux2 (reverse list) (reverse (removeSameTupAux list))


Start = removeSameTup [(1,4,6), (6,4,1), (4,1,6)]		// [(1,4,6)]
//Start = removeSameTup [(1,3,3), (2,2,1), (1,1,5)]		// [(1,3,3),(2,2,1),(1,1,5)]
//Start = removeSameTup [(1,3,3), (3,1,3), (3,3,3)]		// [(1,3,3),(3,3,3)]



