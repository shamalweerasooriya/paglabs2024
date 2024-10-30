package helpers

import MyNamespace.Person
import com.google.flatbuffers.FlatBufferBuilder

import java.nio.ByteBuffer

object FlatbufferHelper {
  def Encode(name: String, age: String): Array[Byte] = {
    val builder = new FlatBufferBuilder(1024)

    // Create a FlatBuffer object for `Person`
    val encodeName = builder.createString(name)
    val encodeAge = builder.createString(age)

    // Start building the `Person` table
    Person.startPerson(builder)
    Person.addName(builder, encodeName)
    Person.addAge(builder, encodeAge)
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
