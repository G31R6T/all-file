package main
//lizhipeng G31R6T
import (
	"fmt"
	"sync"
	"sync/atomic"
)

func main() {
	c := make(chan int, 5)
	var wg sync.WaitGroup

	var receivedCount uint64

	wg.Add(1)
	go func(){
		defer wg.Done()
		for i:= 0; i < 10; i++ {
			c <- i
			fmt.Println("g1 sent ", i)
		}
		close(c)
	}()
	
	wg.Add(1)
	go func(){
		defer wg.Done()
		for r:= range c {
			fmt.Println("g2 received ", r)
			atomic.AddUint64(&receivedCount, 1)
		}
	}()
	
	wg.Wait()
    fmt.Println("Received count:", receivedCount)
}




