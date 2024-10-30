////> using scala 3.2
////> using dep "io.circe::circe-generic:0.14.5"
//
////> using dep "dev.hnaderi::named-codec-circe:0.1.0"
////> using dep "dev.hnaderi::lepus-std:0.3.0"
////> using dep "dev.hnaderi::lepus-circe:0.3.0"
//
import cats.effect.{IO, IOApp}
import com.comcast.ip4s.port
import helpers.FlatbufferHelper
import helpers.RabbitConnection.Receiver
import lepus.client.*
import lepus.protocol.domains.*

import java.nio.ByteBuffer

object ReceiverFBS extends IOApp.Simple {
  private val receiver = Receiver(QueueName("flatbuffer-inbox"), FlatbufferHelper.Read)
  override def run: IO[Unit] = receiver._2.use(receiver._1.app)
}


