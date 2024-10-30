import cats.effect.{IO, IOApp}
import helpers.RabbitConnection.Sender
import lepus.protocol.domains.{ExchangeName, QueueName, ShortString}

object StringSender extends IOApp.Simple{
  private val sender = Sender(QueueName("handler-inbox"), ExchangeName("events"), ShortString("some.topic"), "Hello, World!".getBytes)
  override def run: IO[Unit] = sender._2.use(sender._1.app)
}
