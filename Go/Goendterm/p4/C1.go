package main
//lizhipeng G31R6T
import (
	"fmt"
	"github.com/rs/xid"
	"github.com/streadway/amqp"
	"log"
	"strconv"
	"sync"
)

func main() {
	conn1, err := amqp.Dial("amqp://guest:guest@localhost:5672/")
	failOnError(err, "Failed to connect to RabbitMQ")
	defer conn1.Close()

	conn2, err := amqp.Dial("amqp://guest:guest@localhost:5672/")
	failOnError(err, "Failed to connect to RabbitMQ")
	defer conn2.Close()

	cho, err := conn1.Channel()
	failOnError(err, "Failed to open a channel")
	defer cho.Close()
	chi, err := conn2.Channel()
	failOnError(err, "Failed to open a channel")
	defer chi.Close()

	err = cho.ExchangeDeclare("p4Exchange", "direct", false, true, false, false, nil)
	failOnError(err, "Failed to declare an exchange")

	q, err := chi.QueueDeclare("", false, true, false, false, nil)
	failOnError(err, "Failed to declare a queue")

	err = chi.QueueBind(q.Name, q.Name, "p4Exchange", false, nil)
	failOnError(err, "Failed to bind a queue")

	msgs, err := chi.Consume(q.Name, "", false, false, false, false, nil)
	failOnError(err, "Failed to register a consumer")

	var jobCorr = make(map[string]string)
	var mu sync.Mutex

    // function for
	go func() {
		for d := range msgs {
			mu.Lock()
			v, ok := jobCorr[d.CorrelationId]
			if ok {
				fmt.Println("Job:", v, "Got response:"+string(d.Body))
				delete(jobCorr, d.CorrelationId)
			} else {
				fmt.Println("Got a not related msg")
			}
			mu.Unlock()
			d.Ack(false)
		}
	}()

	//Publish Messages
	jobs := []string{"123", "abc", "abb", "ccd", "456"}

	for _, job := range jobs {
		routingKey := "string"
		if isNum(job) {
			routingKey = "int"
		}
		var corrId = randomString()
		err := cho.Publish("p4Exchange", routingKey, false, false,
			amqp.Publishing{
				ContentType:   "text/plain",
				CorrelationId: corrId,
				ReplyTo:       q.Name,
				Body:          []byte(job),
			})
		failOnError(err, "Failed to publish")
		fmt.Println("Published job:" + job)
		mu.Lock()
		jobCorr[corrId] = job
		mu.Unlock()
	}

	forever := make(chan bool)
	<-forever
}
func failOnError(err error, msg string) {
	if err != nil {
		log.Fatalf("%s: %s", msg, err)
	}
}

func randomString() string {
	guid := xid.New()
	return guid.String()
}

func isNum(s string) bool {
	_, err := strconv.ParseFloat(s, 64)
	return err == nil
}
