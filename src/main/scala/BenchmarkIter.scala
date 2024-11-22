import cats.syntax.traverse._ // Explicitly use Cats' traverse
import cats.effect.{IO, IOApp}
import helpers.{FlatbufferHelper, ProtobuffHelper}
import java.io.{BufferedWriter, FileWriter}

object BenchmarkIter extends IOApp.Simple {

  private val Iterations: Int = 50000
  private val TotalRuns: Int = 50
  private var encodedMessagesF: Array[Array[Byte]] = Array.empty
  private var encodedMessagesP: Array[Array[Byte]] = Array.empty

  override def run: IO[Unit] = {
    val csvFile = "benchmark_results.csv"
    val header = "Run,Flatbuffer Encode Time (ms),Flatbuffer Decode Time (ms),Flatbuffer Avg Size (bytes),Protobuf Encode Time (ms),Protobuf Decode Time (ms),Protobuf Avg Size (bytes)\n"

    for {
      _ <- IO(writeToCsv(csvFile, header, append = false)) // Write header to CSV
      _ <- (1 to TotalRuns).toList.traverse { run =>
        for {
          encodeTimeF <- IO(encodingTime)
          decodeTimeF <- IO(decodingTime)
          avgSizeF = encodedMessagesF.flatten.length / Iterations

          encodeTimeP <- IO(encodingTimeProtoBuf)
          decodeTimeP <- IO(decodingTimeProtoBuf)
          avgSizeP = encodedMessagesP.flatten.length / Iterations

          // Write each run's metrics to CSV
          row = s"$run,$encodeTimeF,$decodeTimeF,$avgSizeF,$encodeTimeP,$decodeTimeP,$avgSizeP\n"
          _ <- IO(writeToCsv(csvFile, row, append = true))
        } yield ()
      }
      _ <- IO(println(s"Benchmark completed. Results written to $csvFile"))
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

  private def writeToCsv(filePath: String, data: String, append: Boolean): Unit = {
    val writer = new BufferedWriter(new FileWriter(filePath, append))
    try {
      writer.write(data)
    } finally {
      writer.close()
    }
  }
}
