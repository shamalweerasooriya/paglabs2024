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
import lepus.client.*
import lepus.protocol.domains.*

object SenderFBS extends IOApp.Simple {

  private val exchange = ExchangeName.default

  private val connect = LepusClient[IO](port= port"5673", username="shamalw", password="secret", vhost=Path("paglabs24"), debug = true)


  override def run: IO[Unit] = connect.use(app)

}

def getFBSMessage(): Array[Byte] = {
  FlatbufferHelper().Encode()
}
def app(con: Connection[IO]) = con.channel.use(ch =>
  for {
    _ <- IO.println(con.capabilities.toFieldTable)
    _ <- ch.exchange.declare(ExchangeName("events"), ExchangeType.Topic)
    q <- ch.queue.declare(QueueName("handler-inbox"), autoDelete = false)
    _ <- ch.queue.bind(QueueName("handler-inbox"), ExchangeName("events"), ShortString("some.topic"))
    qOpt <- IO.fromOption(q)(new Exception("Queue declaration failed"))

    _ <- ch.messaging.publish(
      ExchangeName("events"),
      routingKey = ShortString("some.topic"),
      Message(getFBSMessage())
    )
  } yield ()
)


