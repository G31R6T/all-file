package main

import (
    "fmt"
    "github.com/streadway/amqp"
    "log"
    "strconv"
    "sync"
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
        "p3fanoutExchange", // name
        "fanout",           // type
        false,              // durable
        true,               // auto-deleted
        false,              // internal
        false,              // no-wait
        nil,                // arguments
    )
    printErrorAndExit(err, "Failed to declare an exchange")

    //Decalre and bind queue
    q, err := ch.QueueDeclare(
        "",    // name,,empty string let server generate id
        false, // durable
        true,  // delete when unused
        true,  // exclusive
        false, // no-wait
        nil,   // arguments
    )
    printErrorAndExit(err, "Failed to declare a queue")
    err = ch.QueueBind(
        q.Name,             // queue name
        "",                 // routing key
        "p3fanoutExchange", // exchange
        false,
        nil)
    printErrorAndExit(err, "Failed to bind a queue")

    //Consume
    msgs, err := ch.Consume(
        q.Name,   // queue
        "Double", // consumer,empty string let server generate id
        false,    // auto-ack
        false,    // exclusive
        false,    // no-local
        false,    // no-wait
        nil,      // args
    )
    printErrorAndExit(err, "Failed to register a consumer")

    var wg sync.WaitGroup

    for i := 0; i < 3; i++ {
        wg.Add(1)
        go func() {
            for d := range msgs {
                bodyString := string(d.Body)
                if bodyString == "END" {
                    err = ch.Cancel("Double", false)
                    printErrorAndExit(err, "Failed to cancel a consumer")
                    break
                }
                i, err := strconv.Atoi(bodyString)
                if err != nil {
                    printErrorAndExit(err, "Failed to convert")
                }
                fmt.Println("Received:", i, "Result:", i*2)
                d.Ack(false)
            }
            wg.Done()
        }()
    }

    fmt.Println("Wait for msgs")
    wg.Wait()
    fmt.Println("Finish")
}

func printErrorAndExit(err error, msg string) {
    if err != nil {
        log.Fatalln(msg, ":", err)
    }
}
