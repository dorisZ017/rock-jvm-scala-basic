package lectures.part2oop

object CaseClasses extends App{
  /*
   equals, hashCodes, toString
   */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  // println(instance) = println(instance.toString)
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("jim", 34)
  println(jim == jim2)

  // 4. have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. are serializable
  // Akka framework

  // 7. have extractor patterns = can be used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  /*
  Expand MyList - use classes and case objects
  Just add case to class and object
   */

}
