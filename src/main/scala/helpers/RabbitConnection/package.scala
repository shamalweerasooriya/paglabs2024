package helpers

import cats.effect.IO
import com.comcast.ip4s.port
import lepus.client.LepusClient
import lepus.protocol.domains.Path

package object RabbitConnection {
  def apply() = {
    LepusClient[IO](port= port"5673", username="shamalw", password="secret", vhost=Path("paglabs24"), debug = true)
  }
}
