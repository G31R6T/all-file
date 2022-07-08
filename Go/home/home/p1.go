package main
//G31R6T  lizhipeng
import "fmt"

func main() {
	var receivedCount uint64
	c := make(chan int)
	// creates name is go1 goroutines
	go func() {
		for i := 0; i < 10; i++ {
			c <- i
			//For each sending, g1 will print out ”g1 sent < n >”.
			fmt.Println("g1 sent", i)
			// After sending the numbers, g1 do an operation to indicate that no more values will be sent to c.
			if i == 9 {
				close(c)
			}
		}
		close(c)
	}()
	// creates name is go2 goroutines
	//g2 use a for range to receive messages from the channel c.
	go func() {
		for i := range c {
			fmt.Println("g2 received", i)
			receivedCount++
			if receivedCount == 10 {
				break
			}
		}
	}()
//The main function wait for g1 and g2 and print out ”Received count:” and receivedCount.
	for i := 0; i < 10; i++ {
		fmt.Println("Received count:", receivedCount)
	}
}
