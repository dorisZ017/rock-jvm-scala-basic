package lectures.part2oop

object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  person.greet()
  person.greet("Joe")

  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge)
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter)) // false

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print

}


// constructor
// class paramters are not fields
// val age: converts paramter field
class Person(name: String, val age: Int) {
  // body
  val x = 2
  println(1+3)
  // all functions in body wil be evaluated when initiating new object

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading: different parameters
  def greet(): Unit = println(s"Hi, I am $name") //this person's name

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John Doe")
}

/*
 Novel and a Writer
 Writer: first name, surname, year
  - method fullname

 Novel: Name, year of release, author
  - authorAge
  - isWrittenBy(author)
  - copy (new year of release) = new instance of Novel
 */

/*
 Counter class
 - receives an int value
 - method current count
 - method to increment/decrement => new Counter
 - overload inc/dec to receive an amount
 */
class Writer(firstName: String, surname: String, val year: Int) {

  def fullName(): String = firstName + " " + surname
}

class Novel(name: String, yearOfRelease: Int, val author: Writer) {

  def authorAge: Int = yearOfRelease - author.year
  def isWrittenBy(author: Writer): Boolean = this.author == author
  def copy(newYearOfRelease: Int): Novel = new Novel(this.name, newYearOfRelease, this.author)
}

class Counter(val count: Int = 0) {

  def inc = {
    println("incrementing")
    new Counter(count + 1) // immutability
  }
  def dec = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
  }
  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n-1)
  }

  def print = println(count)
}



