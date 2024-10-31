import cats.effect.{IO, IOApp}
import helpers.FlatbufferHelper

object Benchmark extends IOApp.Simple {

  private val Iterations: Int = 10000
  private var encodedMessages: Array[Array[Byte]] = Array.empty

  override def run: IO[Unit] = {
    for {
      encodeTime <- IO(encodingTime)
      _ <- IO(println(s"Total time taken for Serialization = $encodeTime ms"))
      decodeTime <- IO(decodingTime)
      _ <- IO(println(s"Total time taken for Accessing = $decodeTime ms"))
      avgSize = encodedMessages.flatten.length / Iterations
      _ <- IO(println(s"Wire format size = $avgSize bytes"))
    } yield ()
  }

  private def encodingTime: Long = {
    val startTime = System.currentTimeMillis()
    encodedMessages = Array.fill(Iterations)(FlatbufferHelper().Encode())
    System.currentTimeMillis() - startTime
  }

  private def decodingTime: Long = {
    val startTime = System.currentTimeMillis()
    encodedMessages.foreach { payload =>
      if (payload.nonEmpty) FlatbufferHelper().Read(payload)
    }
    System.currentTimeMillis() - startTime
  }
}
