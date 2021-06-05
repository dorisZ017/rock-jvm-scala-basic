package lectures.part2oop

object Objects extends App {

  // SCALA DOES NOT HAVE CLASS-LEVEL FUNCTIONALITY ("static" in java)
  object Person {
    // "static" "class"-level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    def apply(mother: Person, fater: Person): Person = new Person("Bobbie")
  }

  class Person(val name: String) {
    // instance-level functionality
  }
  // COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // scala object = SINGELTON INSTANCE
  val person1 = Person
  val person2 = Person
  println(person1 == person2) // true

  val a = new Person("name")
  val b = new Person("name")
  println(a == b) // false

  val mary = new Person("Mary")
  val john = new Person("john")

  val bobbie = Person(mary, john)

  // Scala Applications = Scala object with
  // def main(args: Array[String]): Unit
  // Here App has the def main so we extend from it to run

  /*
  Scala objects
  - are in their own class
  - are the only instance
  - singleton pattern in one line
  Scala companions: class & object person
  - can access each other's private names
  - scala is more OO than Java!
   */


}
