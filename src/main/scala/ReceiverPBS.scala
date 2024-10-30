////> using scala 3.2
////> using dep "io.circe::circe-generic:0.14.5"
//
////> using dep "dev.hnaderi::named-codec-circe:0.1.0"
////> using dep "dev.hnaderi::lepus-std:0.3.0"
////> using dep "dev.hnaderi::lepus-circe:0.3.0"
//

import cats.effect.{IO, IOApp}
import com.comcast.ip4s.port
import helpers.ProtobuffHelper
import lepus.client.*
import lepus.protocol.domains.*

object ReceiverPBS extends IOApp.Simple {

  private val exchange = ExchangeName.default

  def appReceiver(con: Connection[IO]) = con.channel.use(ch =>
    for {
      message <- ch.messaging
        .consume[Array[Byte]](QueueName("handler-inbox"))
        .evalMap { message =>
          IO {
            println(s"Received raw message for Protobuff: ${message.message.payload.map("%02X".format(_)).mkString(" ")}")
            try {
              ProtobuffHelper().read(message.message.payload)
            } catch {
              case e: Exception =>
                println(s"Error processing message: ${e}")
                println(s"Buffer content: ${message.message.payload.map("%02X".format(_)).mkString(" ")}")
            }
          }
        }
        .compile.drain

    } yield ()
  )

  private val connect = LepusClient[IO](port = port"5673", username = "shamalw", password = "secret", vhost = Path("paglabs24"), debug = true)

  override def run: IO[Unit] = connect.use(appReceiver)

}
