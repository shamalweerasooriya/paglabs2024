package helpers.RabbitConnection

import cats.effect.IO
import helpers.RabbitConnection
import lepus.client.*
import lepus.protocol.domains.*

object Sender {
  private val connect = RabbitConnection()

  def apply[A](queueName: QueueName, exchangeName: ExchangeName, routingKey: ShortString, message: Array[Byte]) = {
    val sender = new Sender[A](queueName, exchangeName, routingKey, message)
    (sender, connect)
  }
}

class Sender[A](val queueName: QueueName, val exchangeName: ExchangeName, val routingKey: ShortString, val message: Array[Byte]) {
  def app(con: Connection[IO]) = con.channel.use(ch =>
    for {
      _ <- IO.println(con.capabilities.toFieldTable)
      _ <- ch.exchange.declare(exchangeName, ExchangeType.Topic)
      q <- ch.queue.declare(queueName, autoDelete = false)
      _ <- ch.queue.bind(queueName, exchangeName, ShortString("some.topic"))
      qOpt <- IO.fromOption(q)(new Exception("Queue declaration failed"))

      _ <- ch.messaging.publish(
        exchangeName,
        routingKey = ShortString("some.topic"),
        Message(message)
      )
    } yield ()
  )
}