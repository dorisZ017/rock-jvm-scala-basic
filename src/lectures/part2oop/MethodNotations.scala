package lectures.part2oop

object MethodNotations extends App{

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {

    def likes(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}" // define + as you want
    def unary_! : String = s"$name, what the heck?!"
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def +(nickname: String): Person = new Person(s"$name ($nickname)", favoriteMovie)
    def unary_+ : Person = new Person(name, favoriteMovie, age+1)
    def learns(course: String): String = s"$name learns $course"
    def learnScala: String = this.learns("Scala")
    def apply(watch: Int): String = s"$name watch $favoriteMovie $watch times"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "inception") // equivalent
  // infix notation = operation notation (syntactic sugar)

  // "operators" in Scala

  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2)) // ALL OPERATORS ARE METHODS

  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  println(!mary)
  println(mary.unary_!)

  // postfix notation: only for functions without parameter
  println(mary.isAlive)
  //println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent: looking for apply() of the class

  /*
   1. overload the + operator:
      mary + "the rockstar" => new person "Mary (the rockstar)"
   2. Add an age to the Person class
      add a unary + operator => new person with age + 1
      +mary => mary with age incrementer
   3. Add a "learns" method in Person class => "Mary learns Scala"
      Add a learnScala method, calls learns method with "Scala"
   4. Overload the apply method
      mary.apply(2) = "Mary watched Inception 2 times
   */

  println((mary + "the Rockstar").apply())
  println((+mary).age)
}
