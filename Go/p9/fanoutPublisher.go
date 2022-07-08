package main

import (
	"fmt"
	"github.com/streadway/amqp"
	"log"
)

func main() {
	//Connection
	conn, err := amqp.Dial("amqp://guest:guest@localhost:5672/")
	printErrorAndExit(err, "Failed to connect to RabbitMQ")
	defer conn.Close()

	//Channel
	ch, err := conn.Channel()
	printErrorAndExit(err, "Failed to open a channel")
	defer ch.Close()

	//Exchange
	err = ch.ExchangeDeclare(
		"fanoutExchange", // name
		"fanout",         // type
		false,            // durable
		true,             // auto-deleted
		false,            // internal
		false,            // no-wait
		nil,              // arguments
	)
	printErrorAndExit(err, "Failed to declare an exchange")

	//Publish Messages
	publishMsg(ch, "fanoutExchange", "anykey1", "msg")
}

func printErrorAndExit(err error, msg string) {
	if err != nil {
		log.Fatalln(msg, ":", err)
	}
}

func publishMsg(c *amqp.Channel, ex string, key string, msg string) {
	body := msg
	err := (*c).Publish(
		ex,    // exchange
		key,   // routing key
		false, // mandatory
		false, // immediate
		amqp.Publishing{
			ContentType: "text/plain",
			Body:        []byte(body),
		})
	printErrorAndExit(err, "Failed to publish a message")
	fmt.Println("Sent: ", body)
}
