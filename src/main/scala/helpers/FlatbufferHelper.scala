package helpers

import MyNamespace.Person
import com.google.flatbuffers.FlatBufferBuilder

import java.nio.ByteBuffer

class FlatbufferHelper {
  def Encode(): Array[Byte] = {
    val builder = new FlatBufferBuilder(1024)

    // Create a FlatBuffer object for `Person`
    val name = builder.createString("John Doe")
    val age = builder.createString("30")

    // Start building the `Person` table
    Person.startPerson(builder)
    Person.addName(builder, name)
    Person.addAge(builder, age)
    val personOffset = Person.endPerson(builder)

    // Complete the buffer
    builder.finish(personOffset)
    builder.sizedByteArray()
  }

  def Read(buf: ByteBuffer): Unit = {
    // Retrieve the data
    val person = Person.getRootAsPerson(buf)

    println(s"Name: ${person.name}")
    println(s"Age: ${person.age}")
  }

}
