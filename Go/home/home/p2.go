package main

import (
	"fmt"
	"strconv"
	"sync"
)

//Create a function handleInt which takes an integer n and returns(n+2)*3 .
func handleInt(n int) int{
	return (n+2)*3
}
func main(){
	//Create a slice of int. Its name is ints. It has five initial elements:1,2,3,-4,5.
	var ints = []int{1,2,3,-4,5}
	
//Create a map intMap which maps the int to string.
	var intMap = map[int]string{1:"one",2:"two",3:"three",4:"four",5:"five"}
	//For each element in the ints, we start a new goroutine to write the result of double as a string into the intMap.
	for _,v := range ints{
		go func(v int){
			intMap[v] = strconv.Itoa(handleInt(v))
		}(v)
	}
	//The key is the original int, the value is the result of double as a string
	for k,v := range intMap{
		fmt.Println(k,v)
	}
	//Use the Mutex to deal with the mutual exclusion problem when accessing the intMap.
	var mutex = &sync.Mutex{}
	for _,v := range ints{
		go func(v int){
			mutex.Lock()
			intMap[v] = strconv.Itoa(handleInt(v))
			mutex.Unlock()
		}(v)
	}
	//Use the WaitGroup, let the main function wait until all the created goroutines finish.
	var wg sync.WaitGroup
	wg.Add(len(ints))
	for _,v := range ints{
		go func(v int){
			intMap[v] = strconv.Itoa(handleInt(v))
			wg.Done()
		}(v)
	}
	wg.Wait()
	for k,v := range intMap{
		fmt.Println(k,v)
	}
	

}