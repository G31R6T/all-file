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
        "p2Dexchange", // name
        "direct",    // type
        false,       // durable
        true,        // auto-deleted
        false,       // internal
        false,       // no-wait
        nil,         // arguments
    )
    printErrorAndExit(err, "Failed to declare an exchange")
    err = ch.ExchangeDeclare(
        "p2Fexchange", // name
        "fanout",    // type
        false,       // durable
        true,        // auto-deleted
        false,       // internal
        false,       // no-wait
        nil,         // arguments
    )
    printErrorAndExit(err, "Failed to declare an exchange")

    //Publish Messages
    //p2.1
    publishMsg(ch, "p2Dexchange", "c1", "private msg1")
    publishMsg(ch, "p2Dexchange", "c2", "private msg2")

    //p2.2
    publishMsg(ch, "p2Dexchange", "bc", "broadcast1")
    publishMsg(ch, "p2Dexchange", "bc", "broadcast2")

    //p2.3
    publishMsg(ch, "p2Fexchange", "anykey", "broadcast3")
    publishMsg(ch, "p2Fexchange", "anykey", "broadcast4")

    //p2.4
    publishMsg(ch, "p2Dexchange", "p2task", "task1")
    publishMsg(ch, "p2Dexchange", "p2task", "task2")
    publishMsg(ch, "p2Dexchange", "p2task", "task3")
    publishMsg(ch, "p2Dexchange", "p2task", "task4")
    publishMsg(ch, "p2Dexchange", "p2task", "task5")
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
