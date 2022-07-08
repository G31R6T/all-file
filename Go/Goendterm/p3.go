package main
//G31R6T lizhipeng
import (
    "fmt"
    "strconv"
    "sync"
)


var p3fanoutExchange = make(chan string)
var wg sync.WaitGroup

func pulisher() {
    defer wg.Done()
    for i := 0; i <= 10; i++ {
        p3fanoutExchange <- strconv.Itoa(i)
    }
    p3fanoutExchange <- "END"
}

func solutionIncrease() {
    defer wg.Done()
    for i := 0; i < 3; i++ {
        wg.Add(1)
        go func () {
            defer wg.Done()
            L: for {
                select {
                case msg, ok := <- p3fanoutExchange:
                    if !ok {
                        break L
                    }
                    rint, err := strconv.Atoi(msg)
                    if err == nil {
                        fmt.Println("Received: ", rint, "Result:", rint+1)
                    } else {
                        close(p3fanoutExchange)
                        break L
                    }
                }
            }
        }()
    }
}
func solutionDouble() {
    defer wg.Done()
    for i := 0; i < 3; i++ {
        wg.Add(1)
        go func () {
            defer wg.Done()
            L: for {
                select {
                case msg, ok := <- p3fanoutExchange:
                    if !ok {
                        break L
                    }
                    rint, err := strconv.Atoi(msg)
                    if err == nil {
                        fmt.Println("Received: ", rint, "Result:", rint*2)
                    } else {
                        close(p3fanoutExchange)
                        break L
                    }
                }
            }
        }()
    }
}
func main() {
    wg.Add(1)
    go pulisher()

    wg.Add(1)
    go solutionDouble();
    wg.Wait();
}


