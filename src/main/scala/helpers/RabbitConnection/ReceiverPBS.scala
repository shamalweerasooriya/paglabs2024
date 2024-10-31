package helpers.RabbitConnection

import cats.effect.IO
import helpers.RabbitConnection
import lepus.client.*
import lepus.protocol.domains.QueueName

object ReceiverPBS {
  private val connect = RabbitConnection()

  def apply(queueName: QueueName, reader: Array[Byte] => String = (a: Array[Byte]) => new String(a)) = {
    val receiver = new ReceiverPBS(queueName, reader)
    (receiver, connect)
  }
}

class ReceiverPBS(val queueName: QueueName, val reader: (Array[Byte] => String) = (a: Array[Byte]) => new String(a)) {
  def app(con: Connection[IO]) = con.channel.use(ch =>
    for {
      message <- ch.messaging
        .consume[Array[Byte]](queueName)
        .evalMap { message =>
          println(reader(message.message.payload))
          IO(println(s"Received message: $message"))
        }
        .compile.drain

    } yield ()
  )
}
