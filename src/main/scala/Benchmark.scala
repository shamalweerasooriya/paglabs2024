import cats.effect.{IO, IOApp}
import helpers.{FlatbufferHelper, ProtobuffHelper}

object Benchmark extends IOApp.Simple {

  private val Iterations: Int = 10000
  private var encodedMessagesF: Array[Array[Byte]] = Array.empty
  private var encodedMessagesP: Array[Array[Byte]] = Array.empty

  override def run: IO[Unit] = {
    for {
      encodeTime <- IO(encodingTime)
      _ <- IO(println(s"Total time taken for Serialization of Flatbuffer = $encodeTime ms"))
      decodeTime <- IO(decodingTime)
      _ <- IO(println(s"Total time taken for Accessing of Flatbuffer = $decodeTime ms"))
      avgSize = encodedMessagesF.flatten.length / Iterations
      _ <- IO(println(s"Wire format size of Flatbuffer = $avgSize bytes"))

      encodeTimeProtobuf <- IO(encodingTimeProtoBuf)
      _ <- IO(println(s"Total time taken for Serialization of Protobuf = $encodeTimeProtobuf ms"))
      decodeTime <- IO(decodingTimeProtoBuf)
      _ <- IO(println(s"Total time taken for Accessing of Protobuf = $decodeTime ms"))
      avgSize = encodedMessagesP.flatten.length / Iterations
      _ <- IO(println(s"Wire format size of Protobuf = $avgSize bytes"))

    } yield ()
  }

  private def encodingTime: Long = {
    val startTime = System.currentTimeMillis()
    encodedMessagesF = Array.fill(Iterations)(FlatbufferHelper().Encode())
    System.currentTimeMillis() - startTime
  }

  private def encodingTimeProtoBuf: Long = {
    val startTime = System.currentTimeMillis()
    encodedMessagesP = Array.fill(Iterations)(ProtobuffHelper().Encode())
    System.currentTimeMillis() - startTime
  }

  private def decodingTime: Long = {
    val startTime = System.currentTimeMillis()
    encodedMessagesF.foreach { payload =>
      if (payload.nonEmpty) FlatbufferHelper().Read(payload)
    }
    System.currentTimeMillis() - startTime
  }

  private def decodingTimeProtoBuf: Long = {
    val startTime = System.currentTimeMillis()
    encodedMessagesP.foreach { payload =>
      if (payload.nonEmpty) ProtobuffHelper().Read(payload)
    }
    System.currentTimeMillis() - startTime
  }
}
