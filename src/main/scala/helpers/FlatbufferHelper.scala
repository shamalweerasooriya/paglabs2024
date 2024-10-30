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

  def Read(payload: Array[Byte]): String = {
    // Retrieve the data
    val person = Person.getRootAsPerson(ByteBuffer.wrap(payload))
    person.name() + " is " + person.age() + " years old"
  }

}
