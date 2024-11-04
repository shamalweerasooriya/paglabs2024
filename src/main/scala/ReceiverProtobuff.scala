////> using scala 3.2
////> using dep "io.circe::circe-generic:0.14.5"
//
////> using dep "dev.hnaderi::named-codec-circe:0.1.0"
////> using dep "dev.hnaderi::lepus-std:0.3.0"
////> using dep "dev.hnaderi::lepus-circe:0.3.0"
//

import cats.effect.{IO, IOApp}
import helpers.ProtobuffHelper
import helpers.RabbitConnection.ReceiverPBS
import lepus.client.*
import lepus.protocol.domains.*


object ReceiverProtobuff extends IOApp.Simple {
  private val receiver = ReceiverPBS(QueueName("handler-inbox-protobuff"), ProtobuffHelper().Read)

  override def run: IO[Unit] = receiver._2.use(receiver._1.app)
}