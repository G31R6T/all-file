package main
//lizhipeng G31R6T
import (
	"fmt"
	"github.com/streadway/amqp"
	"log"
	"strconv"
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

	q, err := chi.QueueDeclare("intQueue", false, true, false, false, nil)
	failOnError(err, "Failed to declare a queue")

	err = chi.QueueBind(q.Name, "int", "p4Exchange", false, nil)
	failOnError(err, "Failed to bind a queue")

	msgs, err := chi.Consume(q.Name, "", false, false, false, false, nil)
	failOnError(err, "Failed to register a consumer")

	forever := make(chan bool)

	//function
	go func() {
		for d := range msgs {
			bodyString :=  string(d.Body)
			result := intDouble(bodyString)
			err = cho.Publish(
				"p4Exchange", d.ReplyTo, false, false,
				amqp.Publishing{
					ContentType:   "text/plain",
					CorrelationId: d.CorrelationId,
					Body:          []byte(result),
				})
			failOnError(err, "Failed to publish a message")
			fmt.Println("Received job:", string(d.Body), "Published response:", result)
			d.Ack(false)
		}
	}()
	fmt.Println("Waiting for jobs")
	<-forever
}

func failOnError(err error, msg string) {
	if err != nil {
		log.Fatalf("%s: %s", msg, err)
	}
}

func intDouble(s string) string {
	i, _ := strconv.Atoi(s)
	return strconv.Itoa(i * 2)
}
