package main

import (
	"fmt"
	"sync"
	"sync/atomic"
	"time"
)

func main() {
	var wg sync.WaitGroup
	c := make(chan int)
	var receivedCount uint64

	wg.Add(1)
	go func() {
		defer wg.Done()
		for i := 0; i < 10; i++ {
			c <- i
			fmt.Println("g1 sent ", i)
		}
		close(c)
		fmt.Println("g1 no numbers to c!")
	}()

	wg.Add(1)
	go func() {
		defer wg.Done()
		for r := range c {
			time.Sleep(time.Second * 1)
			atomic.AddUint64(&receivedCount, 1)
			fmt.Println("g2 received ", r)
		}
	}()
	wg.Wait()
	fmt.Println("Received count:", receivedCount)
}
