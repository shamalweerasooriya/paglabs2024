package helpers

import MyNamespace.{Address, Person}
import com.google.flatbuffers.FlatBufferBuilder

import java.nio.ByteBuffer

class FlatbufferHelper {
  def Encode(): Array[Byte] = {
    val builder = new FlatBufferBuilder(1024)

    // Create a FlatBuffer object for `Person`
    val f_name = builder.createString(Constants.fname)
    val l_name = builder.createString(Constants.lname)
    val city = builder.createString(Constants.city)
    val state = builder.createString(Constants.state)
    val street = builder.createString(Constants.street)
    val country = builder.createString(Constants.country)
    val postalcode = builder.createString(Constants.postalCode)
    val gender = builder.createString(Constants.gender)
    val email = builder.createString(Constants.email)
    val phone = builder.createString(Constants.phone)

    val address = Address.createAddress(builder, street, city, state, postalcode, country)

    // Start building the `Person` table
    Person.startPerson(builder)
    Person.addFirstName(builder, f_name)
    Person.addLastName(builder, l_name)
    Person.addAge(builder, Constants.age)
    Person.addEmail(builder, email)
    Person.addGender(builder, gender)
    Person.addAddress(builder, address)

    val personOffset = Person.endPerson(builder)

    // Complete the buffer
    builder.finish(personOffset)
    builder.sizedByteArray()
  }

  def Read(payload: Array[Byte]): String = {
    // Retrieve the data
    val person = Person.getRootAsPerson(ByteBuffer.wrap(payload))
    person.firstName() + " " + person.lastName() + " is " + person.age() + " years old. Lives at " + person.address().country()
  }

}
