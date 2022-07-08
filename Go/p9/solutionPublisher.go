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
		"solutionExchange", // name
		"direct",           // type
		false,              // durable
		true,               // auto-deleted
		false,              // internal
		false,              // no-wait
		nil,                // arguments
	)
	printErrorAndExit(err, "Failed to declare an exchange")

	//Publish Messages
	publishMsg(ch, "solutionExchange", "c1", "private msg1")
	publishMsg(ch, "solutionExchange", "c2", "private msg2")
	publishMsg(ch, "solutionExchange", "broadcast", "broadcast1")
	publishMsg(ch, "solutionExchange", "broadcast", "broadcast2")
	publishMsg(ch, "solutionExchange", "task", "task1")
	publishMsg(ch, "solutionExchange", "task", "task2")
	publishMsg(ch, "solutionExchange", "task", "task3")
	publishMsg(ch, "solutionExchange", "task", "task4")
	publishMsg(ch, "solutionExchange", "task", "task5")
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
