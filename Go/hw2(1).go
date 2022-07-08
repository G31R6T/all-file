package main

import (
	"fmt"
	"strconv"
	"sync"
)

func handleInt(n int) int {
	return (n + 2) * 3
}

func main() {
	ints := []int{1, 2, 3, -4, 5}

	var mu sync.Mutex
	var wg sync.WaitGroup

	intMap := make(map[int]string)

	for _, v := range ints {
		wg.Add(1)
		go func(n int) {
			defer wg.Done()
			mu.Lock()
			intMap[n] = strconv.Itoa(handleInt(n))
			mu.Unlock()
		}(v)
	}

	wg.Wait()
	fmt.Println(intMap)
}
