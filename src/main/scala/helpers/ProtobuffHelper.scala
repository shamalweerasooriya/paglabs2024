package helpers

import person.Person

class ProtobuffHelper {
  def encode(): Array[Byte] = {
    val person = Person(name = "John Doe", age = "30", unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    person.toByteArray
  }

  def read(buf: Array[Byte]): Unit = {
    try {
      println(buf)
      val person = Person.parseFrom(buf)
      println(s"Name: ${person.name}, Age: ${person.age}")
    } catch {
      case e: Exception =>
        println(s"Failed to parse data. Buffer: ${buf.map("%02X".format(_)).mkString(" ")}")
        println(s"Error: ${e}")
    }
  }
}