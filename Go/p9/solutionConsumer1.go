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

	////Private messages
	//Decalre and bind queue
	q1, err := ch.QueueDeclare(
		"",    // name,,empty string let server generate id
		false, // durable
		true,  // delete when unused
		true,  // exclusive
		false, // no-wait
		nil,   // arguments
	)
	printErrorAndExit(err, "Failed to declare a queue")
	err = ch.QueueBind(
		q1.Name,            // queue name
		"c1",               // routing key
		"solutionExchange", // exchange
		false,
		nil)
	printErrorAndExit(err, "Failed to bind a queue")

	//Consume
	msgs1, err := ch.Consume(
		q1.Name, // queue
		"",      // consumer,empty string let server generate id
		false,   // auto-ack
		false,   // exclusive
		false,   // no-local
		false,   // no-wait
		nil,     // args
	)
	printErrorAndExit(err, "Failed to register a consumer")

	go func() {
		for d := range msgs1 {
			bodyString := string(d.Body)
			fmt.Println("Received private message:", bodyString)
			d.Ack(false)
		}
	}()

	////Broadcast messages
	//Decalre and bind queue
	q2, err := ch.QueueDeclare(
		"",    // name,,empty string let server generate id
		false, // durable
		true,  // delete when unused
		true,  // exclusive
		false, // no-wait
		nil,   // arguments
	)
	printErrorAndExit(err, "Failed to declare a queue")
	err = ch.QueueBind(
		q2.Name,            // queue name
		"broadcast",        // routing key
		"solutionExchange", // exchange
		false,
		nil)
	printErrorAndExit(err, "Failed to bind a queue")

	//Consume
	msgs2, err := ch.Consume(
		q2.Name, // queue
		"",      // consumer,empty string let server generate id
		false,   // auto-ack
		false,   // exclusive
		false,   // no-local
		false,   // no-wait
		nil,     // args
	)
	printErrorAndExit(err, "Failed to register a consumer")

	go func() {
		for d := range msgs2 {
			bodyString := string(d.Body)
			fmt.Println("Received broadcast message:", bodyString)
			d.Ack(false)
		}
	}()

	////Task messages
	//Decalre and bind queue
	q3, err := ch.QueueDeclare(
		"SharedQ", // name,,empty string let server generate id
		false,     // durable
		true,      // delete when unused
		false,     // exclusive
		false,     // no-wait
		nil,       // arguments
	)
	printErrorAndExit(err, "Failed to declare a queue")
	err = ch.QueueBind(
		q3.Name,            // queue name
		"task",             // routing key
		"solutionExchange", // exchange
		false,
		nil)
	printErrorAndExit(err, "Failed to bind a queue")

	//Consume
	msgs3, err := ch.Consume(
		q3.Name, // queue
		"",      // consumer,empty string let server generate id
		false,   // auto-ack
		false,   // exclusive
		false,   // no-local
		false,   // no-wait
		nil,     // args
	)
	printErrorAndExit(err, "Failed to register a consumer")

	go func() {
		for d := range msgs3 {
			bodyString := string(d.Body)
			fmt.Println("Received task message:", bodyString)
			d.Ack(false)
		}
	}()

	fmt.Println("Waiting for msgs")
	forever := make(chan bool)
	<-forever
}

func printErrorAndExit(err error, msg string) {
	if err != nil {
		log.Fatalln(msg, ":", err)
	}
}
