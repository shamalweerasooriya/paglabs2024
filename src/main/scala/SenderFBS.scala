////> using scala 3.2
////> using dep "io.circe::circe-generic:0.14.5"
//
////> using dep "dev.hnaderi::named-codec-circe:0.1.0"
////> using dep "dev.hnaderi::lepus-std:0.3.0"
////> using dep "dev.hnaderi::lepus-circe:0.3.0"
//
import cats.effect.IO
import cats.effect.IOApp
import com.comcast.ip4s.port
import helpers.FlatbufferHelper
import helpers.RabbitConnection.Sender
import lepus.client.*
import lepus.protocol.domains.*

object SenderFBS extends IOApp.Simple {
  private val sender = Sender[Array[Byte]](QueueName("handler-inbox"), ExchangeName("events"), ShortString("some.topic"), FlatbufferHelper().Encode())
  override def run: IO[Unit] = sender._2.use(sender._1.app)
}



