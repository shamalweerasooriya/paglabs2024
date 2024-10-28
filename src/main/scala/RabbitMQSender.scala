import cats.effect.IO
import com.comcast.ip4s.* // 1

import lepus.client.* // 2
import lepus.protocol.domains.*  // 3

object RabbitMQSender extends App {
  val connection = LepusClient[IO]( // 1
    host = host"localhost",
    port = port"5673",
    username = "shamalw",
    password = "secret",
    vhost = Path("/paglabs2024"), // 2
    config = ConnectionConfig.default,
    debug = false
  )

  val channel = for {
    con <- connection
    ch <- con.channel
  } yield ch

  val app1 = channel.use(ch =>
    ch.exchange.declare(ExchangeName("events"), ExchangeType.Topic) >>
      ch.queue.declare(QueueName("handler-inbox")) >>
      ch.queue.bind(QueueName("handler-inbox"), ExchangeName("events"), ShortString("#"))
  )
  
  val publisher1 = channel.use(ch =>
    ch.messaging.publish(
      ExchangeName("notifications"),
      routingKey = ShortString("some.topic"),
      Message("Something happend!")
    )
  )

  Thread.sleep(20000)
}
