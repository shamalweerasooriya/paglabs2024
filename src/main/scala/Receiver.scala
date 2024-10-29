////> using scala 3.2
////> using dep "io.circe::circe-generic:0.14.5"
//
////> using dep "dev.hnaderi::named-codec-circe:0.1.0"
////> using dep "dev.hnaderi::lepus-std:0.3.0"
////> using dep "dev.hnaderi::lepus-circe:0.3.0"
//
package example

import cats.effect.IO
import cats.effect.IOApp
import lepus.client.*
import lepus.protocol.domains.*

object Receiver extends IOApp.Simple {

  private val connect = RabbitConnection()
  def app(con: Connection[IO]) = con.channel.use(ch =>
    for {
      message <- ch.messaging
        .consume[String](QueueName("handler-inbox"))
        .evalMap { message =>
          IO(println(s"Received message: ${message.message}"))
        }
        .compile.drain

    } yield ()
  )

  override def run: IO[Unit] = connect.use(app)

}


