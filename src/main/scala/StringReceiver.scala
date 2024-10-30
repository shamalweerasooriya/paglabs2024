import cats.effect.{IO, IOApp}
import helpers.RabbitConnection.Receiver
import lepus.protocol.domains.QueueName

object StringReceiver extends IOApp.Simple{
  private val receiver = Receiver(QueueName("messages"))
  override def run: IO[Unit] = receiver._2.use(receiver._1.app)
}
