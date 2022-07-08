package main
//lizhipeng G31R6T
import (
	"fmt"
)

type request struct {
	first int
	second int
}

func product(req request) int {
	return req.first * req.second
}

func main() {
	r1 := request{2,3}
	fmt.Println(product(r1))

}


