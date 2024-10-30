import cats.effect.{IO, IOApp}
import helpers.RabbitConnection.Sender
import lepus.protocol.domains.{ExchangeName, QueueName, ShortString}

object StringSender extends IOApp.Simple{
  private val sender = Sender(QueueName("messages"), ExchangeName("events"), ShortString("messages.from.fluffy.grapefruit"), "APT, APT".getBytes)
  override def run: IO[Unit] = sender._2.use(sender._1.app)
}
