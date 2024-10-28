//> using scala 3.2
//> using dep "io.circe::circe-generic:0.14.5"

//> using dep "dev.hnaderi::named-codec-circe:0.1.0"
//> using dep "dev.hnaderi::lepus-std:0.3.0"
//> using dep "dev.hnaderi::lepus-circe:0.3.0"

package example

import cats.effect.IO
import cats.effect.IOApp
import com.comcast.ip4s.port
import lepus.client.*
import lepus.protocol.domains.*

object HelloWorld extends IOApp.Simple {

  private val exchange = ExchangeName.default

  def app(con: Connection[IO]) = con.channel.use(ch =>
    for {
      _ <- IO.println(con.capabilities.toFieldTable)
      _ <- ch.exchange.declare(ExchangeName("events"), ExchangeType.Topic)
      q <- ch.queue.declare(QueueName("handler-inbox"), autoDelete = true)
      _ <- ch.queue.bind(QueueName("handler-inbox"), ExchangeName("events"), ShortString("some.topic"))
      q <- IO.fromOption(q)(new Exception())

      _ <- ch.messaging.publish(
        ExchangeName("events"),
        routingKey = ShortString("some.topic"),
        Message("Something happend!")
      )

      print = ch.messaging
        .consume[String](q.queue)
        .evalMap(msg => IO.println("Received Message "+msg))
        .printlns

    } yield ()
  )

  private val connect = LepusClient[IO](port= port"5673", username="shamalw", password="secret", vhost=Path("paglabs2024"), debug = true)


  override def run: IO[Unit] = connect.use(app)

}