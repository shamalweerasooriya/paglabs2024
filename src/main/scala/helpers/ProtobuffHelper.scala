package helpers

import person.Person

class ProtobuffHelper {
  def Encode(): Array[Byte] = {
    val person = Person(name = "John Doe", age = "30", unknownFields = _root_.scalapb.UnknownFieldSet.empty)
    person.toByteArray
  }

  def Read(buf: Array[Byte]): String = {
    val person = Person.parseFrom(buf)
    s"${person.name} is ${person.age} years old."
  }
}