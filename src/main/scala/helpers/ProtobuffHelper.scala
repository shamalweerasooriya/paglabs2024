package helpers

import person.{Address, Person}

class ProtobuffHelper {
  def Encode(): Array[Byte] = {
    val person = Person(firstName = Constants.fname, lastName = Constants.lname, age = Constants.age, gender = Constants.gender,
      email = Constants.email, phone = Constants.phone, address = Option(Address(street = Constants.street, city = Constants.city, state = Constants.state,
        postalCode = Constants.postalCode, country = Constants.country)))
    person.toByteArray
  }

  def Read(buf: Array[Byte]): String = {
    val person = Person.parseFrom(buf)
    s"${person.firstName} is ${person.age} years old."
  }
}