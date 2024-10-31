import cats.effect.{IO, IOApp}
import helpers.ProtobuffHelper
import helpers.RabbitConnection.SenderPBS
import lepus.client.*
import lepus.protocol.domains.*

object SenderProtobuff extends IOApp.Simple {
  private val sender = SenderPBS[Array[Byte]](QueueName("handler-inbox-protobuff"), ExchangeName("events"), ShortString("some.topic"), ProtobuffHelper().Encode())

  override def run: IO[Unit] = sender._2.use(sender._1.app)
}




